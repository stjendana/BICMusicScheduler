import React, { Component } from 'react'
import { Button } from 'semantic-ui-react'
import { history } from '../App'
import Sidebar from '../Components/SideBar';
const {REACT_APP_BE_URL} = process.env;

class Home extends Component {
    state = {
        loggedInUser: null
    }

    componentDidMount() {
        this.GetLoggedInUser().then(user => {
            console.log(user);
            this.setState({loggedInUser: user})
          });
    }

    async GetLoggedInUser() {
        let response = await fetch(`${ REACT_APP_BE_URL }/username`, {
            method: 'GET',              
            withCredentials: true,
            headers: new Headers({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('m3-auth-token'),
                'Access-Control-Allow-Origin': '*',
            })                 
        })
        let responseOk = response && response.ok;
        if (responseOk) {
            let data = await response.text();
            return data;
        } else {
            return null;
        }
    }
    
    onClick = () => {
        history.push('/scheduler')
    }
    signOut = () => {
        localStorage.removeItem('m3-auth-token')
        history.push('/')
    }
    render() {        
        return(            
            <div>
                <Sidebar></Sidebar>
            </div>
        )
    }
}

export default Home