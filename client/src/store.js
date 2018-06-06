import { createStore, applyMiddleware,compose } from "redux";
import thunk from 'redux-thunk';

// this is going to index reducer even tho is nit especified to there
import rootReducer from './reducers';

// prelodedSate
const  initialState = {}

// to anhance the store with third-party capabilities as middleware
const middleware=[thunk];
// this is part of reddux

// Store takes in a Reucer, prelodedSate, enhancer
const store = createStore(
  rootReducer,
  initialState,
  compose(
      applyMiddleware(...middleware),
      window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
    )
);

export default store;