import axios from 'axios';

import { GET_PROFILE,GET_PROFILES, PROFILE_LOADING, GET_ERRORS, CLEAR_CURRENT_PROFILE, SET_CURRENT_USER } from './types';
var IdKey = null;
// Get current profile
export const getCurrentProfile = () => dispatch => {
    dispatch(setProfileLoading());
    if(localStorage.IdKey){
        // key is the user id
        IdKey = JSON.parse(localStorage.IdKey);
        axios.get(`http://localhost:8080/p/portfolio/${IdKey.user_id}`)
            .then( res => {
                if(res.data != null){
                    dispatch({
                    type: GET_PROFILE,
                    payload: res.data
                    });
                }else if(res.data == null){
                    dispatch({
                        type: GET_PROFILE,
                        payload: {}
                    });
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: null
                    }); 
                }
            })
            .catch(err => console.log(err))
    }
}
// Get profile by handle
export const getProfileByHandle = (handle) => dispatch => {
    dispatch(setProfileLoading());
    axios.get(`http://localhost:8080/p/by/Handle/${handle}`)
        .then( res => {
            if(res.data != null){
                dispatch({
                type: GET_PROFILE,
                payload: res.data
                });
            }else if(res.data == null){
                dispatch({
                    type: GET_PROFILE,
                    payload: null
                });
            }else{
                dispatch({
                    type: GET_ERRORS,
                    payload: null
                }); 
            }
        })
        .catch(err => console.log(err))
}
// Create Profile 
export const createProfile = (profileData, history) => dispatch => {
    if(localStorage.IdKey){
        IdKey = JSON.parse(localStorage.IdKey);
        axios
            .post(`http://localhost:8080/p/new/${IdKey.user_id}`, profileData)
            .then(res => {
                if(res.data.success){
                    history.push("/dashboard");
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: res.data
                    }); 
                }
            })
            .catch(err => console.log(err))
    }
}
// add experience
export const addExperience = (expData, history) => dispatch => {
    if(localStorage.IdKey){
        IdKey = JSON.parse(localStorage.IdKey);
        axios
            .post(`http://localhost:8080/exp/new/${IdKey.user_id}`, expData)
            .then(res => {
                if(res.data.success){
                    history.push("/dashboard");
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: res.data
                    })
                }
            })
            .catch(err => console.log(err));
    }
}
// add education
export const addEducation = (eduData, history) => dispatch => {
    if(localStorage.IdKey){
        IdKey = JSON.parse(localStorage.IdKey);
        axios
            .post(`http://localhost:8080/edu/new/${IdKey.user_id}`, eduData)
            .then(res => {
                if(res.data.success){
                    history.push("/dashboard");
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: res.data
                    })
                }
            })
            .catch(err => console.log(err));
    }
}
// delete an experience 
export const deleteExperience = (id) => dispatch => {
    if(localStorage.IdKey){
        axios
            .delete(`http://localhost:8080/exp/delete/${id}`)
            .then(res => {
                if(res.data != null){
                    dispatch({
                        type: GET_PROFILE,
                        payload: res.data
                    })
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: res.data
                    })
                }
            })
            .catch(err => console.log(err));
    }
}
// Get all profiles
export const getProfiles = () => dispatch => {
    dispatch(setProfileLoading());
    axios
        .get("http://localhost:8080/p/all")
        .then(res => {
            if(res.data != null){
                dispatch({
                    type: GET_PROFILES,
                    payload: res.data
                })
            }else{
                dispatch({
                    type: GET_PROFILES,
                    payload: null
                })
            }
        })
        .catch(err => console.log(err));
}

// delete an Education
export const deleteEducation= (id) => dispatch => {
    if(localStorage.IdKey){
        axios
            .delete(`http://localhost:8080/edu/delete/${id}`)
            .then(res => {
                if(res.data != null){
                    dispatch({
                        type: GET_PROFILE,
                        payload: res.data
                    })
                }else{
                    dispatch({
                        type: GET_ERRORS,
                        payload: res.data
                    })
                }
            })
            .catch(err => console.log(err));
    }
}
// delete account & profile
export const deleteAccount = () => dispatch => {
    if(window.confirm('Are you sure? This can NOT be undone!')){
        if(localStorage.IdKey){
            IdKey = JSON.parse(localStorage.IdKey);
            axios
                .delete(`http://localhost:8080/p/delete/${IdKey.user_id}`)
                .then(res => {
                    if(res.data.success){
                        dispatch({
                            type: SET_CURRENT_USER,
                            payload: {}
                        })
                        localStorage.removeItem("IdKey");
                    }else{
                        dispatch({
                            type: GET_ERRORS,
                            payload: res.data
                        })
                    }
                })
                .catch(err => console.log(err))
        }
    }
}



// Profile loading 
export const setProfileLoading = () => {
    return{
        type: PROFILE_LOADING
    }
}
// Profile clear 
export const clearCurrentProfile = () => {
    return{
        type: CLEAR_CURRENT_PROFILE
    }
}