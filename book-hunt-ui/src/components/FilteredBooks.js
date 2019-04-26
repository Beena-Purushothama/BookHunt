import React, { Component } from 'react';
import { clearFilteredBooks, retrieveFilteredBooks,clearErrMsg } from "../actions/books";
import {connect} from "react-redux";
import Book from "./Book";
import Pagination from "./Pagination";


class FilteredBooks extends Component {
  state ={
    page : 0
  }

  onPagination = (value) => {
    this.setState({page:value});
  }

  componentDidUpdate =(prevProps, prevState) => {
      const {searchText,dispatch} = this.props;
      const {page} = this.state;

      if(prevProps.searchText !== searchText || prevState.page !== page) {
        if(searchText === ''){
            //clear out filtered books from redux store
            dispatch(clearFilteredBooks());
            dispatch(clearErrMsg());
        }else{
            //dispatch action to thunk and finally update reducer
            dispatch(retrieveFilteredBooks(searchText, page))
        }
      }
  }

  render() {
    const {books,errors} = this.props;
    return (
      <div className="container main-body">
      {(errors) && 
        <div className=" invalid-feedback ">{errors} </div>
      }
      <ul className="row book-list">
        { //Display book
          (typeof books !== undefined) && (books.length > 0) &&
          (books.map((id) => { 
                  return (<li className="col" key={id}>
                      <Book id={id}/>
                    </li>
                  )
          }))
        }
      </ul>
      {(typeof books !== undefined) && (books.length > 0) &&<Pagination onPagination={this.onPagination}></Pagination>}
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
