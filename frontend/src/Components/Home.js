import React, { Component } from 'react'
import { Button } from 'semantic-ui-react'
import { history } from '../App'


class Home extends Component {
    onClick = () => {
        history.push('/scheduler')
    }
    render(){
        return(
            <div>
                <Button color='blue' fluid onClick={this.onClick}> Schedule </Button>
                <Button color='blue' fluid> Manage </Button>
            </div>
        )
    }
}

export default Home