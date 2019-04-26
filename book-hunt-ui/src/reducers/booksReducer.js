import {RECEIVE_FILTERED_BOOKS,CLEAR_FILTERED_BOOKS} from "../actions/actionTypes";

const initialState = {}

export default function(state=initialState, action){
    switch(action.type) {
        case RECEIVE_FILTERED_BOOKS : 
            return action.books
        case CLEAR_FILTERED_BOOKS : 
            return initialState;
        default : return state;
    }

}
