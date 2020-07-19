import React from 'react';
import OrderCardsGenerator from '../cardGenerator/OrderCardsGenerator.js';
import './home.css';
import { getUserWithOrders } from '../../api/api.js';

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {
                address: '',
                coords: {},
                email: '',
                name: '',
                finishedOrders: [],
                pendingOrders: [],
            },
            error: '',
        };
    }

    setContent(user) {
        getUserWithOrders(user)
            .then(userPromise =>{
                this.setState({user: userPromise})
            }) 
            .catch(e => {this.setState({ error: e.message })});
    }

    componentDidMount() {
        this.setContent(this.props.username);
    }

    render(){
        return (
            <div className="Home">
                <div className="container">
                    <h1 className="blanco">Pending Orders</h1>
                    <OrderCardsGenerator orders={this.state.user.pendingOrders} username={this.props.username}/>
                </div>
                <div className="container">
                    <h1 className="blanco">History</h1>
                    <OrderCardsGenerator orders={this.state.user.finishedOrders} username={this.props.username}/>
                </div>
            </div>
        )
    }
}

export default Home;