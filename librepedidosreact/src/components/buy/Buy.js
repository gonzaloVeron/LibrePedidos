import React from 'react';
import MenuCardsGeneratorForBuy from '../cardGenerator/menuCardsGenerator/MenuCardsGeneratorForBuy.js';
import { deliver } from '../../api/api';
import './Buy.css';

class Buy extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            menusOfOrders: [],
            error: {},
        };
        this.addElemList = this.addElemList.bind(this);
        this.changeOrAddTuple = this.changeOrAddTuple.bind(this);
        this.changeAmount = this.changeAmount.bind(this);
        this.belongs = this.belongs.bind(this);
        this.makeOrder = this.makeOrder.bind(this);
    }

    addElemList(menuId, amount) {
        this.setState({menusOfOrders: this.changeOrAddTuple(this.state.menusOfOrders, {menuId, amount})}, () => console.log(this.state.menusOfOrders));
    }

    changeOrAddTuple(arr, tuple){
        return( (this.belongs(arr, tuple)) ? this.changeAmount(arr, tuple) : this.state.menusOfOrders.concat(tuple) )
    }

    changeAmount(arr, tuple){
        return(arr.map(e => { return((e.menuId == tuple.menuId) ? {menuId:tuple.menuId, amount:tuple.amount} : e )}))
    }

    belongs(arr, tuple){
        return (arr.some(t => t.menuId == tuple.menuId));
    }

    makeOrder(){
        deliver({user: this.props.username,
                restaurantId: this.props.location.state.restaurant.code,
                menus: this.state.menusOfOrders.filter(t => t.amount > 0),
                payMethod: {dataCash: {}},
                destination: {latitude: 1, longitude: 1},
            })
            .then(promise => console.log(promise))
            .catch(e => this.setState({error: e.message}))
    }

    render(){
        console.log(this.props)
        return(
            <div className="Search">
                <div className="container">
                    <h1 className="blanco">{this.props.location.state.restaurant.name} menus:</h1>
                    <MenuCardsGeneratorForBuy addElemList={this.addElemList} menus={this.props.location.state.restaurant.menus}/>
                </div>
        
                <button type="button" class="btn btn-primary" onClick={this.makeOrder} data-toggle="modal" data-target="#exampleModalCenter">
                ENDED
                </button>

                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">PAY</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Cash</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Card</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab" aria-controls="pills-contact" aria-selected="false">Payment Market</a>
                            </li>
                        </ul>
                                <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab"></div>

                                <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">

                                    <form>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">NAME</label>
                                            <input type="text" class="form-control"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">CARD</label>
                                            <input type="number" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">CSV</label>
                                            <input type="number" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">EXPIRATION DATE</label>
                                            <input type="text" class="form-control" />
                                        </div>
                                    
                                    </form>

                                </div>

                                <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">                               
                                    <form>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">USER</label>
                                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
                                        </div>
                                    
                                    </form>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">BUY</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">CLOSE</button>
                    </div>
                    </div>
                </div>
                </div>

            </div>
            
        )   
    }

}

export default Buy;