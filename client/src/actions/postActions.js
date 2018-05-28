import axios from 'axios';
import {
    ADD_POST,
    GET_ERRORS,
    GET_POSTS,
    POST_LOADING,
    DELETE_POST
} from './types';

// Add post
export const addPost = postData => dispatch => {
  if (localStorage.IdKey) {
    axios
      .post(`http://localhost:8080/post/new`, postData)
      .then(res => {
        if (res.data.success) {
          dispatch({
              type: ADD_POST,
              payload: res.data
          })
        } else {
          dispatch({
            type: GET_ERRORS,
            payload: res.data
          });
        }
      })
      .catch(err => console.log(err));
  }
};

//get all posts
export const getPosts = () => dispatch => {
  dispatch(setPostLoading);
  if (localStorage.IdKey) {
    axios
      .post(`http://localhost:8080/post/all`)
      .then(res => {
        if (res.data != null) {
          dispatch({
              type: GET_POSTS,
              payload: res.data
          })
        } else {
          dispatch({
            type: GET_POSTS,
            payload: null
          });
        }
      })
      .catch(err => console.log(err));
  }
};

//delete post
export const deletePost = id => dispatch => {
  dispatch(setPostLoading);
  if (localStorage.IdKey) {
    axios
      .delete(`http://localhost:8080/post/delete/${id}`)
      .then(res => {
        if (res.data != null) {
          dispatch({
              type: DELETE_POST,
              payload: id
          })
        } else {
          dispatch({
            type: GET_ERRORS,
            payload: res.data
          });
        }
      })
      .catch(err => console.log(err));
  }
};

// Set loading state 
export const setPostLoading = () => {
    return {
        type: POST_LOADING
    }
}