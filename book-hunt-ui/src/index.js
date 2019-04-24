import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {Provider} from "react-redux";
import {BrowserRouter as Router} from "react-router-dom";
import {createStore, compose} from "redux";
import reducers from "./reducers";
import middlewares from "./middlewares"

const store =  createStore(reducers, compose(middlewares, window.devToolsExtension ? window.devToolsExtension() : f => f))

ReactDOM.render(
<Provider store={store}>
    <Router>
        <App />
    </Router>
</Provider>, document.getElementById('root'));

