import React, { Component } from 'react';
import './App.css';
import {Route} from "react-router-dom";
import SearchBooks from "./components/SearchBooks";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Route exact path="/" component={SearchBooks} ></Route>
      </div>
    );
  }
}

export default App;
