import React, { Component } from 'react';
import Authenticate from './Components/Authenticate'
import Home from './Components/Home'
import Scheduler from './Components/Scheduler/Scheduler'
import Login from './Components/Login'
import { createBrowserHistory } from 'history'
import { Router, Route } from 'react-router-dom'
import './App.css';

export const history = createBrowserHistory()

class App extends Component {
  render(){

    fetch('http://localhost:8080/isTokenExpired/' + localStorage.getItem('m3-auth-token'), {
            method: 'GET',            
            headers: {
                'Content-Type': 'application/json'
              }
        }).then((response) => response.text())
        .then((responseObj) => {
          if(responseObj === 'true') {
            
          } else {
            // Token is not expired
          }
      
        })

    return (
        <Router history={history}>
            <div>
                <Route exact path='/' component={Authenticate} />
                <Route exact path='/home' component={Home} />
                <Route exact path='/login' component={Login} />
                <Route exact path='/scheduler' component={Scheduler} />
            </div>    
        </Router>   
    )
  }
}

export default App