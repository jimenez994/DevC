import axios from 'axios';
import {
    ADD_POST,
    GET_ERRORS,
    GET_POSTS,
    POST_LOADING,
    DELETE_POST,
    GET_POST,
    CLEAR_ERRORS

} from './types';
// Add post
export const addPost = postData => dispatch => {
  dispatch(clearErrors());  
  if (localStorage.IdKey) {
    axios
      .post(`http://107.21.178.139/post/new`, postData)
      .then(res => {
        if (res.data.success) {
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
      .post(`http://107.21.178.139/post/all`)
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
      .post(`http://107.21.178.139/post/get/${id}`)
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
      .delete(`http://107.21.178.139/post/delete/${id}`)
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
      .post(`http://107.21.178.139/post/like/${id}/${Idkey.user_id}`)
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
      .post(`http://107.21.178.139/post/unlike/${id}/${Idkey.user_id}`)
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
  dispatch(clearErrors());
  if (localStorage.IdKey) {
    axios
      .post(`http://107.21.178.139/post/comment/new/${postId}`, commentData)
      .then(res => {
        if (res.data.success) {
          dispatch(getPost(postId))
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

// delete comment
export const deleteComment = (postId,commentId) => dispatch => {
  if (localStorage.IdKey) {
    axios
      .delete(`http://107.21.178.139/post/comment/delete/${commentId}`)
      .then(res => {
        if (res.data.success) {
          dispatch(getPost(postId))
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

// clear errors
export const clearErrors = () => {
  return {
    type: CLEAR_ERRORS
  }
}