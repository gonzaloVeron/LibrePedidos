import React from 'react';
import './card.css';

class MenuCardForBuy extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            amount: 0,
            error: {},
        };
        this.increaseAmount = this.increaseAmount.bind(this)
        this.decreaseAmount = this.decreaseAmount.bind(this)
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

    increaseAmount(){
        this.setState({ amount: this.state.amount + 1}, () => this.props.addElemList(this.props.menu.code, this.state.amount))
    }

    decreaseAmount(){
        this.setState({ amount: Math.max(0, this.state.amount - 1)}, () => this.props.addElemList(this.props.menu.code, this.state.amount))
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
                            
                            <button type="button" class="btn btn-primary" onClick={this.decreaseAmount}>-</button>
                            - {this.state.amount} -
                            <button type="button" class="btn btn-primary" onClick={this.increaseAmount}>+</button>
                                
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        )
    }
}

export default MenuCardForBuy;
