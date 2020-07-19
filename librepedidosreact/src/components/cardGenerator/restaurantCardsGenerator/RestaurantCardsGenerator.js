import React from 'react';
import RestaurantCard from '../../card/RestaurantCard'

class RestaurantCardsGenerator extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          error: {},
        };
    }

    render(){
        return(
        this.props.restaurants.map((res, i) => {
            return(
                <RestaurantCard key={i} restaurant={res} history={this.props.history}/>
            )
        })
        )
    }

}

export default RestaurantCardsGenerator;