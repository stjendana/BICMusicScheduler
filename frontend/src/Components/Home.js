import React, { Component } from 'react'
import { Button } from 'semantic-ui-react'
import { history } from '../App'


class Home extends Component {
    onClick = () => {
        history.push('/scheduler')
    }
    signOut = () => {
        localStorage.removeItem('m3-auth-token')
        history.push('/')
    }
    render(){
        return(
            <div>
                <Button color='blue' fluid onClick={this.onClick}> Schedule </Button>
                <Button color='blue' fluid> Manage </Button>
                <Button color='blue' fluid onClick={this.signOut}> Sign out </Button>
            </div>
        )
    }
}

export default Home