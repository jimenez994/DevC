import React, { Component } from 'react';
// Switch is require to use private route
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './store';
// layout
import Navbar from "./conponents/layout/Navbar";
import Footer from "./conponents/layout/Footer";
import Landing from "./conponents/layout/Landing";
// auth 
import Register from "./conponents/auth/Register";
import Login from "./conponents/auth/Login";
import './App.css';
// private route checker
import PrivateRoute from "./conponents/common/PrivateRoute";

import setAuthToken from './utils/setAuthToken';
import { setCurrentUser } from './actions/authActions';

import Dashboard from './conponents/dashboard/Dashboard';
import CreateProfile from "./conponents/create-profile/CreateProfile";
import EditProfile from "./conponents/edit-profile/EditProfile";
import AddExperience from "./conponents/add-credentials/AddExperience";
import AddEducation from "./conponents/add-credentials/AddEducation";

// This to check if user is in session
// Chaeck for tokem
if(localStorage.IdKey){
  // Set auth token header auth
  setAuthToken(localStorage.IdKey);
  // Get user info
  // Set user and isAithenticated
  store.dispatch(setCurrentUser(localStorage.IdKey))

  // // Theck fro expired token
  // const currentTime = Date.now()/ 1000;
  // if(decoded.exp< currentTime){
  //   store.dispatch(loginUser());
  //   // todo: clear current profile
  //   // redirect to login
  //   window.location.href = '/login';
  // }
}

class App extends Component {
  render() {
    return <Provider store={store}>
        <Router>
          <div className="App">
            <Navbar />
            <Route exact path="/" component={Landing} />
            <div className="container">
              <Route exact path="/register" component={Register} />
              <Route exact path="/login" component={Login} />
              <Switch>
                <PrivateRoute exact path="/dashboard" component={Dashboard} />
              </Switch>
              <Switch>
                <PrivateRoute exact path="/create-profile" component={CreateProfile} />
              </Switch>
              <Switch>
                <PrivateRoute exact path="/edit-profile" component={EditProfile} />
              </Switch>
              <Switch>
                <PrivateRoute exact path="/add-experience" component={AddExperience} />
              </Switch>
              <Switch>
                <PrivateRoute exact path="/add-education" component={AddEducation} />
              </Switch>
            </div>
            <Footer />
          </div>
        </Router>
      </Provider>;
  }
}

export default App;
