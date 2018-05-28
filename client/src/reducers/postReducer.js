import { ADD_POST, GET_POSTS,POST_LOADING, DELETE_POST, GET_POST } from '../actions/types';
const initialSate = {
    posts: [],
    post: {},
    loading: false
};

export default function(state = initialSate, action) {
    switch(action.type){
        case POST_LOADING:
            return {
                ...state,
                loading: true
            };
        case GET_POSTS:
            return {
                ...state,
                posts: action.payload,
                loading: false
            };
        case ADD_POST:
            console.log(action.payload)
            console.log(state.posts)
            return {
                ...state,
                posts: [action.payload, ...state.posts]
            };
        case DELETE_POST:
            return {
                ...state,
                posts: state.posts.filter(post => post.id !== action.payload)
            }
        case GET_POST:
            return {
                ...state,
                post: action.payload,
                loading: false
            }
        default:
        return state;
    }
}