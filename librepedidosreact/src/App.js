import React from 'react';
import { Switch, Route } from 'react-router';
import { BrowserRouter } from 'react-router-dom';

import Login from './components/login/Login';
import Register from './components/register/Register';
import Home from './components/home/Home'
import Search from './components/search/Search'
import Root from './components/Root';
import Restaurant from './components/restaurant/Restaurant'
import Buy from './components/buy/Buy'

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
    };
    this.changeUsername = this.changeUsername.bind(this);
  }

  changeUsername(newUsername) {
    this.setState({username: newUsername});
  }



  render() { 
    return  (
      <BrowserRouter> 
        <Switch>
          <Route exact path='/' render={(props) => <Login {...props} changeUsername={this.changeUsername} />}/>
          <Route exact path='/register' render={(props) => <Register {...props}/>} />
          <Root>
            <Route path='/search/:text' render={(props) => <Search {...props} username={this.state.username} />}/>
            <Route path='/home' render={(props) => <Home {...props} username={this.state.username} />}/>
            <Route path='/buy' render={(props) => <Buy {...props} username={this.state.username} />}/>
            <Route path='/restaurant' render={(props) => <Restaurant {...props} username={this.state.username} />}/>
          </Root>
        </Switch>
      </BrowserRouter>
      
    ); 
  }

}

export default App;
