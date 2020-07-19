import React from 'react';
import '../login/Login.css';
import { register } from '../../api/api';
import { Link } from 'react-router-dom';

class Register extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      email: '',
      password: '',
      address: '',
      coord: {
          latitude: 1,
          longitude: 1,
      },
      error: '',
    };
    this.changeUsername = this.changeUsername.bind(this);
    this.changeEmail = this.changeEmail.bind(this);
    this.changePassword = this.changePassword.bind(this); 
    this.changeAddress = this.changeAddress.bind(this);
    this.register = this.register.bind(this);
  }

  register() {
    register({ username: this.state.username, email: this.state.email, password: this.state.password, address: this.state.address, coord: this.state.coord})
      .then(() => this.props.history.push('/'))
      .catch(e => this.setState({ error: e.message }))
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
    this.setState({ password: event.target.value });
  }

  changeEmail(event) {
    this.setState({ email: event.target.value });
  }

  changeAddress(event) {
    this.setState({ address: event.target.value });
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
            {this.renderInput(this.state.email, "Email", 'text', this.changeEmail)}
            {this.renderInput(this.state.password, "Contraseña", 'password', this.changePassword)}
            {this.renderInput(this.state.dir, "Direccion", 'text', this.changeAddress)}
            <button type="button" className="btn btn-primary btn-block btn-large" onClick={ this.register }>Registrarse</button>
            <div>
              <br />  
              <h2>Ya tienes cuenta? <Link to={'/'}>Iniciar sesion</Link></h2>
            </div>
          </form>
        </div>
      </div>
    )    
  }

}

export default Register;