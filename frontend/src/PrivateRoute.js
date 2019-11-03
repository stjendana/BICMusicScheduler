import { Route } from 'react-router-dom'
import React from 'react';
import { Redirect } from 'react-router';

const PrivateRoute = ({ component: Component, ...rest }) => {

    // Add your own authentication on the below line.
    const isLoggedIn = localStorage.getItem('m3-auth-token') !== null && localStorage.getItem('m3-auth-token') !== 'undefined'

    return (
      <Route
        {...rest}
        render={props =>
          isLoggedIn ? (
            <Component {...props} />
          ) : (
            <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
          )
        }
      />
    )
  }
  
  export default PrivateRoute