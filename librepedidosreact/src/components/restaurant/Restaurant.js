import React from 'react';
import MenuCardsGenerator from '../cardGenerator/menuCardsGenerator/MenuCardsGenerator.js';

class Restaurant extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: {},
        };
        this.goBuy = this.goBuy.bind(this)
    }

    goBuy(){
        this.props.history.push('/buy', {menu: this.props.menu, restaurant: this.props.location.state.restaurant})
    }

    render(){
        console.log(this.props)
        return(
            <div className="Search">
                <div className="container">
                    <h1 className="blanco">{this.props.location.state.restaurant.name}</h1>
                    <MenuCardsGenerator menus={this.props.location.state.restaurant.menus} restaurant={this.props.location.state.restaurant} history={this.props.history} hideButton={true}/>
                </div>
                <button className="negro" onClick={this.goBuy}>  Realizar pedido. </button>
            </div>
        )   
    }

}

export default Restaurant;