import React, { Component } from 'react';
import { Button } from 'semantic-ui-react';
import { history } from '../App'
import "semantic-ui-css/semantic.min.css";

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        };    
    }

    AuthenticateUser = (event) => {
        event.preventDefault()       
       console.log(JSON.stringify(this.state))

       fetch('http://localhost:8080/authenticate', {
            method: 'POST',
            body: JSON.stringify(this.state),
            headers: {
                'Content-Type': 'application/json'
              }
        }).then((response) => response.text())
        .then((responseObj) => {
          this.token = JSON.parse(responseObj).token
          localStorage.setItem('m3-auth-token', this.token)
          history.push('/home')
        })

    }


    onUserNameChange = (event) => {
        this.state.username = event.target.value;
    }

    onPasswordChange = (event) => {
        this.state.password = event.target.value;
    }
    

    render(){
        return(
        <form onSubmit={this.AuthenticateUser}>
            <label>Email:
                <input type="text" onChange={this.onUserNameChange} />
            </label>
            <label>Password:
                <input type="password" onChange={this.onPasswordChange} />
            </label>
            <input type="submit" value="Login" />
        </form>  
        )
    }
}

export default Login