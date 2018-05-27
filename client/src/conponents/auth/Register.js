import React, { Component } from "react";
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
// import classnames from 'classnames';
import { connect } from 'react-redux';
import { registerUser } from '../../actions/authActions';
// input
import TextFieldGroup from '../common/TextFieldGroup';
class Register extends Component {
    constructor(){
        super();
        this.state ={
            name: '',
            email: '',
            password: '', 
            errors: {}
        }
        // this binds the from with on chenge in order to use this keyword onChange
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);        
    }
    componentDidMount(){
        if(this.props.auth.isAuthenticated){
        this.props.history.push('/dashboard')
        }
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors: nextProps.errors})
        }
    }

    onChange(e){
        // based on the name of the filds it will update the state name:name, email:eamil, password:password
        this.setState({[e.target.name]:e.target.value});
    }

    onSubmit(e){
        // this takes out the default dehaviour
        e.preventDefault();
        const newUser = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password
        }
        this.props.registerUser(newUser, this.props.history);
    }

    render() {
        // this is the same as const errors = this.state.errors
        const { errors } = this.state;
        return (
            <div className="register">
                <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                    <h1 className="display-4 text-center">Sign Up</h1>
                    <p className="lead text-center">Create your DevConnector account</p>
                    <form onSubmit={this.onSubmit}>

                        <TextFieldGroup 
                            placeholder="Name" name="name" type='text' 
                            value={this.state.name} onChange={this.onChange} error={errors.name}
                        />

                        <TextFieldGroup 
                            placeholder="Email Address" name="email" type='email' 
                            info="testing info"
                            value={this.state.email} onChange={this.onChange} error={errors.email}
                        />

                        <TextFieldGroup 
                            placeholder="Password" name="password" type='password' 
                            value={this.state.password} onChange={this.onChange} error={errors.password}
                        />

                        <input type="submit" className="btn btn-info btn-block mt-4" />
                    </form>
                    </div>
                </div>
                </div>
            </div>
        );
    }
}
Register.protoTypes = { 
    registerUser: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    // this comes from index.js in reducer where where auth : auth matches  
    auth: state.auth,
    errors: state.errors
})

export default connect(mapStateToProps, { registerUser })(withRouter(Register));