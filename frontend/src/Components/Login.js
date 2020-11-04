import React, { Component } from 'react';
import { Button, Form, Segment, Grid, Header } from 'semantic-ui-react';
import { history } from '../App'
import "semantic-ui-css/semantic.min.css";
import { Message } from 'semantic-ui-react'
const {REACT_APP_BE_URL} = process.env;

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            loginError: false
        };    
    } 

    AuthenticateUser = (event) => {
      async function Authenticate(state) {
        let response = await fetch(`${ REACT_APP_BE_URL }/authenticate`, {
            method: 'POST',
            body: JSON.stringify(state),
            headers: {
                'Content-Type': 'application/json'
              }
        })
        let responseOk = response && response.ok;
        if (responseOk) {
          let data = await response.text();
          return data;
        } else {
          return null;
        }
      }      

      Authenticate(this.state).then(res => {
        if (res) {
          localStorage.setItem('m3-auth-token', JSON.parse(res).token)          
          history.push('/home')
        } else {
          this.setState({loginError: true})
        } 
      });
    }   

    RegisterUser = () =>{
        history.push('/register')
    }

    onUserNameChange = (event) => {
      this.setState({loginError: false, username: event.target.value})
    }

    onPasswordChange = (event) => {
      this.setState({loginError: false, password: event.target.value})
    }    

    render(){
        return(
          <div className='login-form' style={{ backgroundImage: `url(${"/homepage_background.jpg"})`, backgroundSize: 'cover'}}>
            <style>
              {`
              body > div,
              body > div > div,
              body > div > div > div.login-form {
                height: 100%;
              }
              .ui.button.login-button {
                margin-bottom: 10px;
              }
            `}
            </style>
            <Grid
              textAlign='center'
              style={{height: '100%'}}
              verticalAlign='middle'
            >
              <Grid.Column style={{maxWidth: 450, background: 'white', borderRadius: '5px'}}>
                <Header as='h2' color='blue' textAlign='center'>
                  Log In to your account
                </Header>
                <Form size='large' error={this.state.loginError}>
                  <Segment stacked>

                    {this.state.loginError ? <Message error header="Login Error" content="The email or password provided is incorrect. Please try again" /> : null}

                    <Form.Input
                      required
                      fluid
                      icon='user'
                      iconPosition='left'
                      placeholder='Username'
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
                    <Button className='login-button' color='blue' fluid size='large' onClick={this.AuthenticateUser}> Login </Button>
                    <Button color='teal' fluid size='large' onClick={this.RegisterUser}> Sign Up </Button>
                  </Segment>
                </Form>
              </Grid.Column>
            </Grid>
          </div>
        )
    }
}

export default Login