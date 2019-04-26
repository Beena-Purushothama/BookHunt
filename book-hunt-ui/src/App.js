import React, { Component, Fragment } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import {Route} from "react-router-dom";
import SearchBooks from "./components/SearchBooks";
import Header from "./components/Header";
import Footer from "./components/Footer";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header/>
        <Route exact path="/" component={SearchBooks} ></Route>
        <Footer/>
      </div>
    );
  }
}

export default App;
