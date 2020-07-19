import React from 'react';
import { Link, withRouter } from 'react-router-dom';
import './NavBar.css';

class NavBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            search: '',
        };
        this.changeSearch = this.changeSearch.bind(this);
        this.doSearch = this.doSearch.bind(this);
        this.goHome = this.goHome.bind(this);
        this.logOut = this.logOut.bind(this);
      }

    changeSearch(event) {
        this.setState({ search: event.target.value });
    }

    doSearch() {
        this.props.history.push(`/search/${this.state.search}`)
    }

    goHome() {
        this.props.history.push('/home')
    }

    logOut() {
        this.props.history.push('/')
    }

    //to={`/search/${this.state.search}`} params={{ username: this.props.username }}
    render() {
        return (
            <nav className="navbar navbar-light bg-light">
                <Link to={"/home"} className="navbar-brand ">Libre Pedidos</Link>
                <form className="form-inline searchDiv">
                    <input className="form-control  mr-sm-1" type="search" placeholder="Buscar comida/restaurante" onChange={this.changeSearch} />
                    <div>
                        <Link onClick={this.doSearch}>
                            <button className="btn searchButton" type="submit">&#x1F50D;</button>
                        </Link>
                    </div>
                </form>

                <div>
                    <Link onClick={this.goHome} className="buttonNavBar">Mis ordenes</Link>
                    <Link onClick={this.logOut} className="buttonNavBar">Cerrar sesion</Link>
                </div>
            </nav>
        )
    }

}

export default withRouter(NavBar);
