import React, { Component } from 'react';
import { Button, Form, Segment, Grid, Header } from 'semantic-ui-react';
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
          <div className='login-form'>
            <style>
              {`
              body > div,
              body > div > div,
              body > div > div > div.login-form {
                height: 100%;
              }
            `}
            </style>
            <Grid
              textAlign='center'
              style={{height: '100%'}}
              verticalAlign='middle'
            >
              <Grid.Column style={{maxWidth: 450}}>
                <Header as='h2' color='blue' textAlign='center'>
                  Log-in to your account
                </Header>
                <Form size='large'>
                  <Segment stacked>
                    <Form.Input
                      required
                      fluid
                      icon='user'
                      iconPosition='left'
                      placeholder='username'
                      onChange = {this.onUserNameChange}
                    />
                    <Form.Input
                      required
                      fluid
                      icon='lock'
                      iconPosition='left'
                      placeholder='Password'
                      type='password'
                      onChange = {this.onPasswordChange}
                    />
                    <Button color='blue' fluid size='large' onClick={this.AuthenticateUser}> Login </Button>
                  </Segment>
                </Form>
              </Grid.Column>
            </Grid>
          </div>
        )
    }
}

export default Login