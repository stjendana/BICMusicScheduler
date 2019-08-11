import React, { Component } from 'react';
import { Button } from 'semantic-ui-react';
import "semantic-ui-css/semantic.min.css";

class Welcome extends Component {
    render(){
        return(
        <div>
            <Button color='blue' fluid> Login </Button>
            <Button color='blue' fluid> Sign Up </Button>
        </div>
        )
    }
}

export default Welcome