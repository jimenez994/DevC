import axios from 'axios';
import {
    ADD_POST,
    GET_ERRORS,
    GET_POSTS,
    POST_LOADING,
    DELETE_POST,
    GET_POST

} from './types';
// Add post
export const addPost = postData => dispatch => {
  if (localStorage.IdKey) {
    axios
      .post(`http://localhost:8080/post/new`, postData)
      .then(res => {
        if (res.data.success) {
            // changin from null to []
            res.data.likes = []
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
//get post
export const getPost = (id) => dispatch => {
  dispatch(setPostLoading);
  if (localStorage.IdKey) {
    axios
      .post(`http://localhost:8080/post/get/${id}`)
      .then(res => {
        if (res.data != null) {
          dispatch({
              type: GET_POST,
              payload: res.data
          })
        } else {
          dispatch({
            type: GET_POST,
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
//add Like
export const addLike = id => dispatch => {
  dispatch(setPostLoading);
  if (localStorage.IdKey) {
      let Idkey = JSON.parse(localStorage.IdKey);
    axios
      .post(`http://localhost:8080/post/like/${id}/${Idkey.user_id}`)
      .then(res => {
        if (res.data != null) {
          dispatch(getPosts())
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
//remove Like
export const removeLike = id => dispatch => {
  dispatch(setPostLoading);
  if (localStorage.IdKey) {
      let Idkey = JSON.parse(localStorage.IdKey);
    axios
      .post(`http://localhost:8080/post/unlike/${id}/${Idkey.user_id}`)
      .then(res => {
        if (res.data != null) {
          dispatch(getPosts());
        } else {
          dispatch({ type: GET_ERRORS, payload: res.data });
        }
      })
      .catch(err => console.log(err));
  }
};

// Add comment
export const addComment = (postId, commentData) => dispatch => {
  if (localStorage.IdKey) {
    axios
      .post(`http://localhost:8080/post/new/comment/${postId}`, commentData)
      .then(res => {
        if (res.data.success) {
          dispatch({
              type: GET_POST,
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

// Set loading state 
export const setPostLoading = () => {
    return {
        type: POST_LOADING
    }
}