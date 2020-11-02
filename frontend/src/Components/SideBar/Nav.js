import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';


class Nav extends Component {

  state = {};

  render() {
    let { location } = this.props;
    return (
      <ul className="nav">
        <li className={location.pathname === '/' ? 'active' : null}>
          <Link to="/scheduler">
            <i className="pe-7s-date"></i>
            <p>Scheduler</p>
          </Link>
        </li>

        <li className={location.pathname === '/' ? 'active' : null}>
          <Link to="/scheduler">
            <i className="pe-7s-config"></i>
            <p>Manage</p>
          </Link>
        </li>

        <li className={location.pathname === '/' ? 'active' : null}>
          <Link to="/scheduler">
            <i className="pe-7s-power"></i>
            <p>Sign Out</p>
          </Link>
        </li>
      </ul>
    );
  }

  isPathActive(path) {
    return this.props.location.pathname.startsWith(path);
  }
}

export default withRouter(Nav);