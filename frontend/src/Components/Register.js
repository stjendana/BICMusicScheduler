import React, { Component } from 'react';
import { Button, Form, Segment, Grid, Header } from 'semantic-ui-react';
import { history } from '../App'
import "semantic-ui-css/semantic.min.css";

class Register extends Component {
    constructor(props) {
        super(props);  
        this.state = {
            firstName: '',
            lastName: '',
            username: '',
            password: '',
            city: ''
        };
    }

    options = [
        { key: 'Vancouver', text: 'Vancouver', value: 'Vancouver' },
        { key: 'Calgary', text: 'Calgary', value: 'Calgary' },
        { key: 'Toronto', text: 'Toronto', value: 'Toronto' }
    ]

    onFirstNameChange = (event) => {
        this.state.firstName = event.target.value;
    }

    onLastNameChange = (event) => {
        this.state.lastName = event.target.value;
    }

    onUserNameChange = (event) => {
        this.state.username = event.target.value;
    }

    onCityChange = (event, {value}) => {
        this.state.city = value;
    }

    onPasswordChange = (event) => {
        this.state.password = event.target.value;
    }

    Submit = () => {
        fetch('http://localhost:8080/register', {
            method: 'POST',
            body: JSON.stringify(this.state),
            headers: {
                'Content-Type': 'application/json'
              }
        }).then((response) => response.text())
        .then((responseObj) => {
          
          history.push('/home')
        })
    }

   
    render() {
        return(
          <div className='register-form' style={{ backgroundImage: `url(${"/homepage_background.jpg"})`, backgroundSize: 'cover'}}>
            <style>
              {`
              body > div,
              body > div > div,
              body > div > div > div.register-form {
                height: 100%;
              }
            `}
            </style>
            <Grid
              textAlign='center'
              style={{height: '100%'}}
              verticalAlign='middle'
            >
              <Grid.Column style={{maxWidth: 450, background: 'white'}}>
                <Header as='h2' color='blue' textAlign='center'>
                  Sign Up
                </Header>
                <Form size='large'>
                  <Segment stacked>
                  <Form.Input
                      required
                      fluid
                      icon='user'
                      iconPosition='left'
                      placeholder='First Name'
                      onChange={this.onFirstNameChange}
                    />
                    <Form.Input
                      required
                      fluid
                      icon='user'
                      iconPosition='left'
                      placeholder='Last Name'
                      onChange={this.onLastNameChange}
                    />
                    <Form.Input
                      required
                      fluid
                      icon='mail'
                      iconPosition='left'
                      placeholder='Email Address (Username)'
                      onChange={this.onUserNameChange}
                    />
                    <Form.Input
                      required
                      fluid
                      icon='lock'
                      iconPosition='left'
                      placeholder='Password'
                      type='password'
                      onChange={this.onPasswordChange}
                    />
                    <Form.Select
                        required
                        fluid
           
                        options={this.options}
                        placeholder='City'
                        onChange={this.onCityChange}
                    />
                    <Button type='submit' color='blue' fluid size='large' onClick={this.Submit}> Sign Up </Button>
                  </Segment>
                </Form>
              </Grid.Column>
            </Grid>
          </div>
        )
    }
}

export default Register