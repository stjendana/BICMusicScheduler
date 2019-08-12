import React, { Component } from 'react';
import Authenticate from './Components/Authenticate'
import Home from './Components/Home'
import Scheduler from './Components/Scheduler/Scheduler'
import { createBrowserHistory } from 'history'
import { Router, Route } from 'react-router-dom'
import './App.css';

export const history = createBrowserHistory()

class App extends Component {
  render(){
    return (
        <Router history={history}>
            <div>
                <Route exact path='/' component={Authenticate} />
                <Route exact path='/home' component={Home} />
                <Route exact path='/scheduler' component={Scheduler} />
            </div>    
        </Router>   
    )
  }
}

export default App