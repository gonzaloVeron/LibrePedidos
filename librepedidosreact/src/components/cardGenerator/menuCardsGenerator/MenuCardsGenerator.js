import React from 'react';
import MenuCard from '../../card/MenuCard'

class MenuCardsGenerator extends React.Component {

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
                <MenuCard key={i} menu={me} history={this.props.history} hideButton={this.props.hideButton}/>
            )
        })
        )
    }

}

export default MenuCardsGenerator;