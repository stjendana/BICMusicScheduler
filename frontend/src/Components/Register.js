import React, { Component } from 'react';
import { Button, Form, Segment, Grid, Header } from 'semantic-ui-react';
import { history } from '../App'
import "semantic-ui-css/semantic.min.css";
const {REACT_APP_BE_URL} = process.env;

class Register extends Component {
    constructor(props) {
        super(props);  
        this.state = {
            firstName: '',
            lastName: '',
            username: '',
            password: '',
            city: '',
            ministry: '',
            ministryOptions: []
        };        

        async function Call() {
          let response = await fetch(`${ REACT_APP_BE_URL }/ministries`, {method: 'GET'})
          let responseOk = response && response.ok;
          if (responseOk) {
            let data = await response.text();
            return data;
          } else {
            return null;
          }
        }
        Call().then(res => {
          if (res) {
            this.setState({ministryOptions: JSON.parse(res).map(obj => ({
              'key': obj.name,
              'text': obj.name,
              'value': obj.id
            }))})
          } 
        });
    }


    options = [
        { key: 'Vancouver', text: 'Vancouver', value: 'Vancouver' },
        { key: 'Calgary', text: 'Calgary', value: 'Calgary' },
        { key: 'Toronto', text: 'Toronto', value: 'Toronto' }
    ]

    onFirstNameChange = (event) => {
      this.setState({firstName: event.target.value})
    }

    onLastNameChange = (event) => {
      this.setState({lastName: event.target.value})
    }

    onUserNameChange = (event) => {
      this.setState({username: event.target.value})
    }

    onCityChange = (event, { value }) => {
      this.setState({city: value})
    }

    onMinistryChange = (event, { value }) => {
      this.setState({ministry: value.join(',')})
    }

    onPasswordChange = (event) => {
      this.setState({password: event.target.value})
    
    }

    handleSubmit = () => {
      this.props.onChange(this.state)
    }

    Submit = () => {
        fetch(`${ REACT_APP_BE_URL }/register`, {
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
              <Grid.Column style={{maxWidth: 450, background: 'white', borderRadius: '5px'}}>
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
                    <Form.Select 
                        required
                        fluid           
                        multiple
                        options={this.state.ministryOptions}
                        placeholder='Ministry'
                        onChange={this.onMinistryChange}
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