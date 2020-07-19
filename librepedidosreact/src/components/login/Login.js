import React from 'react';
import './Login.css';
import { sigIn } from '../../api/api';
import { Link } from 'react-router-dom';


class Login extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
    };
    this.changeUsername = this.changeUsername.bind(this);
    this.changePassword = this.changePassword.bind(this); 
    this.login = this.login.bind(this)
  }

  login() {
    sigIn({username: this.state.username, password: this.state.password })
      .then(user => { 
        this.props.changeUsername(user.nameUser);
        this.props.history.push('/home');
      })
      .catch(e => this.setState({ error: "Usuario y/o contraseña incorrectos" }))
  }

  renderInput(value, placeholder, inputType, onChange) {
    return (
          <input type={inputType} placeholder={placeholder} value={value} onChange={onChange} />
    )
  }

  changeUsername(event) {
    this.setState({ username: event.target.value });
  }

  changePassword(event) {
    this.setState({ password: event.target.value })
  }

  render() { 
    return  (
      <div className="container-login">
        <div className="login">
          <h1>Libre Pedidos</h1>
          <div>
              <h3>{this.state.error}</h3>
          </div>
          <form method="post">
            {this.renderInput(this.state.username, "Usuario", 'text', this.changeUsername)}
            {this.renderInput(this.state.password, "Contraseña", 'password', this.changePassword)}
            <button type="button" className="btn btn-primary btn-block btn-large" onClick={ this.login }>Iniciar sesion</button>
            <div>
              <br />
              <h2>No tienes cuenta? <Link to={'/register'}>Crea una</Link></h2>
            </div>
          </form>
        </div>
      </div>
    ) 
       
  }
}

export default Login;