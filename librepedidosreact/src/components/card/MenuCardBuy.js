import React from 'react';
import './Card.css';

class MenuCardBuy extends React.Component {
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
                            <table>
                                <tr>
                                    <td> <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                                        Buy - ${this.props.menu.price}
                                        </button>
                                    </td>
                                </tr>
                            </table>            
                        </div>


                        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">RANKING</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                ESTRELLAS PUNTUACION
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        )
    }
}

export default MenuCardBuy;
