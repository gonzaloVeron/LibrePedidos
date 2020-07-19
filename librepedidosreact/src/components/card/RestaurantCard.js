import React from 'react';
import './card.css';

class RestaurantCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          error: {},
        };
    }

    renderMenu(menus) {
      return(
          menus.map((menu, index) => {
              return(
                <div key={index}>
                    <table class="tab">
                        <tr className="tr1">
                        <td className="tdclass">{menu.code}</td>
                        <td className="tdclass">{menu.name}</td>
                        <td className="tdclass">Precio: ${menu.price}</td>
                        </tr>
                    </table>
                </div>)
          })
      )
    }

    render(){
        return(
            <div key={this.props.index}>
                <div className="card">
                    <div className="card-header">
                        {this.props.restaurant.name}
                    </div>

                    <div>
                        <div className="card-body">
                            {this.props.restaurant.description}
                            <table>
                                <tr>
                                    <td> <button type="button" className="btn btn-primary" onClick={() => this.props.history.push('/restaurant', {restaurant: this.props.restaurant})}>
                                        Go to the restaurant
                                        </button>
                                    </td>
                                </tr>
                            </table>            
                        </div>
                    </div>
                    <br/>
                </div>
            </div>
        )
    }


}

export default RestaurantCard;
