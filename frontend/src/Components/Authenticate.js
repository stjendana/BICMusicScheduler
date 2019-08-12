import React, { Component } from 'react';
import { Button } from 'semantic-ui-react';
import { history } from '../App'
import "semantic-ui-css/semantic.min.css";

class Authenticate extends Component {
    onClick = () =>{
        history.push('/home')
    }
    render(){
        return(
        <div>
            <Button color='blue' fluid onClick={this.onClick}> Login </Button>
            <Button color='blue' fluid> Sign Up </Button>
        </div>
        )
    }
}

export default Authenticate