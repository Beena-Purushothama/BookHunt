import {combineReducers} from "redux";
import ErrorsReducers from "./errorReducer";
import BooksReducer from "./booksReducer";

export default combineReducers({
    errors:ErrorsReducers,
    books: BooksReducer
})