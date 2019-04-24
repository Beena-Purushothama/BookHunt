import {RECEIVE_FILTERED_BOOKS,CLEAR_FILTERED_BOOKS,PUT_ERRORS,CLEAR_ERRORS} from "./actionTypes";
import {getBooks} from "../utils/api";

export const clearFilteredBooks = () => ({
    type : CLEAR_FILTERED_BOOKS
})

export const retrieveFilteredBooks = (searchText) =>  async dispatch => {
    
    return getBooks(searchText).then(res =>{
        dispatch(updateFilteredBooks(res.data));
    }).catch(error => {
        dispatch(addError(error.response));
    })

}

export const clearErrMsg = () =>( {
type: CLEAR_ERRORS
})

const updateFilteredBooks = (books) => ({
    type: RECEIVE_FILTERED_BOOKS,
    books
})

const addError = (errMsg) => ({
    type:PUT_ERRORS,
    errMsg
})