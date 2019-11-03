import React, { Component } from 'react';
import Home from './Components/Home'
import Scheduler from './Components/Scheduler/Scheduler'
import Login from './Components/Login'
import Register from './Components/Register'
import { createBrowserHistory } from 'history'
import { Router, Route } from 'react-router-dom'
import './App.css';
import PrivateRoute from './PrivateRoute';

export const history = createBrowserHistory()

class App extends Component {
  render() {
    return (
        <Router history={history}>
            <div>
                <Route exact path='/' component={Login} />                
                <Route exact path='/login' component={Login} />
                <Route exact path='/register' component={Register} />
                <PrivateRoute exact path='/home' component={Home} />
                <PrivateRoute exact path='/scheduler' component={Scheduler} />
            </div>    
        </Router>   
    )
  }
}

export default App