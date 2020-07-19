import React from 'react';
import OrderCard from '../card/OrderCard'

class OrderCardsGenerator extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          error: {},
        };
    }

    render(){
        return(
        this.props.orders.map((ord, i) => {
            return(
                <OrderCard key={i} order={ord} username={this.props.username}></OrderCard>
            )
        })
        )
    }

}

export default OrderCardsGenerator;