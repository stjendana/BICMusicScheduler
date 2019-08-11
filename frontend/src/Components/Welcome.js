import React, { Component } from 'react';
import { Button } from 'semantic-ui-react';
import "semantic-ui-css/semantic.min.css";

class Welcome extends Component {
    render(){
        return(
        <div>
            <h1> Welcome to BIC Music Scheduler! </h1>
            <Button> Login </Button>
            <Button> Sign Up </Button>
        </div>
        )
    }
}

export default Welcome