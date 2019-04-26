import {RECEIVE_FILTERED_BOOKS,CLEAR_FILTERED_BOOKS,PUT_ERRORS,CLEAR_ERRORS} from "./actionTypes";
import {getBooks} from "../utils/api";

export const clearFilteredBooks = () => ({
    type : CLEAR_FILTERED_BOOKS
})

export const retrieveFilteredBooks = (searchText,page) =>  async dispatch => {
    
    return getBooks(searchText,page).then(res =>{
        dispatch(clearErrMsg());
        dispatch(updateFilteredBooks(res.data));
    }).catch(error => {
        let err = error.response.data.message !== undefined ? error.response.data.message : "An error occured"
        dispatch(addError(err));
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