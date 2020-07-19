import React from 'react';
import NavBar from './navbar/NavBar';

export default class Root extends React.Component {
  render() {
    return (
      <div>
        <NavBar />
        <div className="container">
          {this.props.children}
        </div>
      </div>
    );
  }
}
