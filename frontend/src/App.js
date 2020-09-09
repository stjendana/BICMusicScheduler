import React, { Component } from 'react';
import Home from './Components/Home'
import Scheduler from './Components/Scheduler/Scheduler'
import Login from './Components/Login'
import Register from './Components/Register'
import { createBrowserHistory } from 'history'
import { Router, Route } from 'react-router-dom'
import { Provider } from 'react-redux'
import PrivateRoute from './PrivateRoute';
import createStore from './store'
import './App.css';
import {userContext} from './userContext';
import './assets/styles/base.scss';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap';

const store = createStore()
export const history = createBrowserHistory()

class App extends Component {
  render() {
    return (
      <Provider store = {store}>
        <Router history={history}>
            <div>
                <Route exact path='/' component={Login} />                
                <Route exact path='/login' component={Login} />
                <Route exact path='/register' component={Register} />
                <PrivateRoute exact path='/home' component={Home} />
                <PrivateRoute exact path='/scheduler' component={Scheduler} />
            </div>    
        </Router>
      </Provider>   
    )
  }
}

export default App