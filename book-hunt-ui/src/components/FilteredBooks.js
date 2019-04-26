import React, { Component } from 'react';
import { clearFilteredBooks, retrieveFilteredBooks } from "../actions/books";
import {connect} from "react-redux";
import Book from "./Book";

class FilteredBooks extends Component {
  componentDidMount =() => {
    console.log("componentDidMount :",this.props.searchText);

  }
  componentDidUpdate =(prevProps) => {

      const {searchText,page,dispatch} = this.props;
      console.log("componentDidUpdate :",searchText);

      if(prevProps.searchText !== searchText || prevProps.page !== page) {
        if(searchText === ''){
            //clear out filtered books from redux store
            dispatch(clearFilteredBooks());
        }else{
            //dispatch action to thunk and finally update reducer
            dispatch(retrieveFilteredBooks(searchText, page))
        }
      }
  }
  render() {
    const {books,errors} = this.props;
    return (
      <div className="container">
      {(errors) && 
       <div className=" invalid-feedback ">{errors} </div>
      }
        <ul className="row book-list">
            { //Display book
              (typeof books !== undefined) && (books.length > 0) &&
              (books.map((id) => { 
                console.log(id);
                return (<li className="col" key={id}>
                        <Book id={id}/>
                        </li>
                      )
             }))
            }
            
        </ul>
      </div>
    )
  }
}

const mapStateToProps = ({books,errors},{searchText,page}) => {
  const booksKey = Object.keys(books);
  return ({
    books : booksKey,
  searchText,
  page,
  errors
})}

export default connect(mapStateToProps)(FilteredBooks);
