import axios from 'axios';
import {
    ADD_POST,
    GET_ERRORS
} from './types';


// Add post
export const addPost = postData => dispatch => {
  if (localStorage.IdKey) {
    IdKey = localStorage.IdKey;
    axios
      .post(`http://localhost:8080/post/new`, postData)
      .then(res => {
        if (res.data != null) {
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