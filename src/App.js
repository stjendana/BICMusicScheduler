import React, { Component } from 'react';
import Welcome from './Components/Welcome'
import Home from './Components/Home'
import { createBrowserHistory } from 'history'
import { Router, Route } from 'react-router-dom'
import './App.css';

export const history = createBrowserHistory()

class App extends Component {
  render(){
    return (
        <Router history={history}>
            <div>
                <Route exact path='/' component={Home} />
                <Route exact path='/welcome' component={Welcome} />
            </div>    
        </Router>   
    )
  }
}

export default App