import React from 'react';
import MenuCardForBuy from '../../card/MenuCardForBuy';

class MenuCardsGeneratorForBuyWithAmmount extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          error: {},
        };
        
    }

    render(){
        return(
            this.props.menus.map((me, i) => {
                return(
                    <MenuCardForBuy key={i} menu={me}/>
                )
            })
        )
    }
}

//export default MenuCardsGeneratorForBuyWithAmmount;