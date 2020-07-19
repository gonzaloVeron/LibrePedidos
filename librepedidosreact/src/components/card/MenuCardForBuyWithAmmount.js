import React from 'react';
import './card.css';

class MenuCardForBuyWithAmmount extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
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
                            <button type="button" className="btn btn-primary" onClick={}>
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

//export default MenuCardForBuyWithAmmount;
