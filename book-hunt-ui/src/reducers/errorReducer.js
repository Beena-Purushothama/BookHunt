import {PUT_ERRORS,CLEAR_ERRORS} from "../actions/actionTypes";

const initialState ="";

export default function(state=initialState, action){
    switch(action.type) {
        case PUT_ERRORS : return action.errMsg;
        case CLEAR_ERRORS : return initialState;
        default : return state;
    }

}