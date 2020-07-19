import React from 'react';
import './card.css';
import { Link } from 'react-router-dom';
import { voteOrder } from '../../api/api';


class OrderCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            rated: props.order.rated,
            error: {},
        };
        this.vote = this.vote.bind(this);
    }

    renderMenu(menus) {
      return(
          menus.map((menu, index) => {
              return(
                <div key={index}>
                    <table  className="text-center">
                        <thead>
                            <tr>
                                <th scope="col">Codigo</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <tr>
                                <td>{menu.code}</td>
                                <td>{menu.name}</td>
                                <td >$  {menu.price}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>)
          })
      )
    }

    vote(score) {
        voteOrder(this.props.order.code)({user: this.props.username, rating: score})
            .then( () => this.setState({rated: true}) )
            .catch(e => this.setState({ error: e.message }))
    }

    renderVote() {
        return (
            <div className="modal fade" id="exampleModalCenter" tabIndex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLongTitle">Puntuar</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <div className="star-rating">
                                <Link data-dismiss="modal" onClick={() => this.vote(1)}>&#9733;</Link>
                                <Link data-dismiss="modal" onClick={() => this.vote(2)}>&#9733;</Link>
                                <Link data-dismiss="modal" onClick={() => this.vote(3)}>&#9733;</Link>
                                <Link data-dismiss="modal" onClick={() => this.vote(4)}>&#9733;</Link>
                                <Link data-dismiss="modal" onClick={() => this.vote(5)}>&#9733;</Link>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            
        )
    }

    render(){
        console.log("ORDER: ")
        console.log(this.props)
        return(
            <div key={this.props.index}>              

                <div className="card text-center ">
                    <div className="card-header">
                        {this.props.order.restaurant.name}
                    </div>
                    <div className="card-body cardStyle">                            
                        {this.renderMenu(this.props.order.menus)}
                    </div>
                    <div className="card-footer text-muted">
                        Total: $ {this.props.order.menus.map(menu => menu.price).reduce(function(acc, val) { return acc + val; }, 0)}

                    <button type="button" className={this.state.rated ? 'hidden' : 'btn btn-primary float-right'} data-toggle="modal" data-target="#exampleModalCenter">
                        Puntuar
                    </button>

                    </div>
                </div>

                {this.renderVote()}

                <br/>
            </div>
        )
    }
}

export default OrderCard;
