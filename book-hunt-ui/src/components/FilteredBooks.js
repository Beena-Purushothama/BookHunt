import React, { Component } from 'react';
import { clearFilteredBooks, retrieveFilteredBooks } from "../actions/books";
import {connect} from "react-redux";
import Book from "./Book";

class FilteredBooks extends Component {
  componentDidMount =() => {
    console.log("componentDidMount :",this.props.searchText);

  }
  componentDidUpdate =(prevProps) => {

      const {searchText,dispatch} = this.props;
      console.log("componentDidUpdate :",searchText);

      if(prevProps.searchText !== searchText) {
        if(searchText === ''){
            //to-do: clear out filtered books from redux store
            dispatch(clearFilteredBooks());
        }else{
            //to-do: dispatch action to thunk and finally update reducer
            dispatch(retrieveFilteredBooks(searchText))
        }
      }
  }
  render() {
    const {books} = this.props;
    console.log("render :",this.props.searchText);

    return (
      <div>
      In here
        <ul>
            { //to-do for every books received display a book
             // (typeof books !== undefined) && (books.length > 0) &&
             // (books.map((book) => { 
              //  return <li key={book.id}>
              //    <Book id={book.id}/>
               // </li>

            //  }))
            }
            
        </ul>
      </div>
    )
  }
}

const mapStateToProps = ({},{searchText}) => {
  console.log("searchText---",searchText)
  return ({

  searchText
})}

export default connect(mapStateToProps)(FilteredBooks);
