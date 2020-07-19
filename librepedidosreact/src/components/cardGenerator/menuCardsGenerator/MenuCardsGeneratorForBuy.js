import React from 'react';
import MenuCardForBuy from '../../card/MenuCardForBuy';

class MenuCardsGeneratorForBuy extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          error: {},
        };
    }

    addElem(menuId, amount){
        this.props.addElemList(menuId, amount);
    }

    render(){
        return(
            this.props.menus.map((me, i) => {
                return(
                    <MenuCardForBuy key={i} menu={me} addElemList={this.props.addElemList}/>
                )
            })
        )
    }
}

export default MenuCardsGeneratorForBuy;