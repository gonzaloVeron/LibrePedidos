import React from 'react';
import { search } from '../../api/api.js';
import RestaurantCardsGenerator from '../cardGenerator/restaurantCardsGenerator/RestaurantCardsGenerator.js';
import MenuCardsGenerator from '../cardGenerator/menuCardsGenerator/MenuCardsGenerator.js';
import NavBar from '../navbar/NavBar';

class Search extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: props.username,
            restaurants:[],
            menus:[],
            error: {},
        };
    }

    setContent(text) {
        search(text)
            .then(result => {
                this.setState({restaurants: result.restaurants})
                this.setState({menus: result.menus})
            }) 
            .catch(e => {this.setState({ error: e.message })});
    }

    componentDidMount() {
        this.setContent(this.props.match.params.text);
    }

    componentWillReceiveProps(nextProps) {
        this.setContent(nextProps.match.params.text);
      }

    render() {
        console.log(this.state)
        return(
            <div className="Search">
                <div className="container">
                    <h1 className="blanco">Restaurants with: {this.props.match.params.text}</h1>
                    <RestaurantCardsGenerator restaurants={this.state.restaurants} history={this.props.history}/>/>
                </div>
                <div className="container">
                    <h1 className="blanco">Menus with: {this.props.match.params.text}</h1>
                    <MenuCardsGenerator menus={this.state.menus} history={this.props.history} hideButton={false}/>
                </div>
            </div>
        )   
    }

}

export default Search;