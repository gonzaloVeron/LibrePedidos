import React from 'react';
import './card.css';
import { getRestaurant } from '../../api/api';


class MenuCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurant: {},
            error: {},
        };
    }

    renderProducts(prods) {
      return(
        prods.map((prod, index) => {
              return(
                <div key={index}>
                    <table class="tab">
                        <tr className="tr1">
                        <td className="tdclass">{prod.name}</td>
                        </tr>
                    </table>
                </div>)
          })
      )
    }

    componentDidMount(){
        if(!this.props.hideButton){
            getRestaurant(this.props.menu.restaurantId)
                .then(result => {
                    this.setState({restaurant: result});
                })
                .catch(e => this.setState({error: e.message}))
        }
    }

    render(){
        return(
            <div key={this.props.index}>
                <div className="card">
                    <div className="card-header">
                        {this.props.menu.name}
                    </div>
                    <div>
                        <div className="card-body">
                            {this.renderProducts(this.props.menu.products)}
                            <button type="button" className={this.props.hideButton ? 'hidden' : 'btn btn-primary'} onClick={() => this.props.history.push('/buy', {menu: this.props.menu, restaurant: this.state.restaurant})}>
                                Buy - ${this.props.menu.price}
                            </button>  
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        )
    }
}

export default MenuCard;
