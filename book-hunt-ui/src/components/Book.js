import React, { Component } from 'react'
import { connect } from 'react-redux';
import Image from "react-bootstrap/Image";

class Book extends Component {
  render() {
    const {book} = this.props;
    return (
      <div className="card-body text-center">
        <div className="book-cover" >
          <Image src={book.imageLinks === "" ? require("../utils/defaultImage.jpg") :book.imageLinks} />
        </div>
        <div className="book-title">{book.title}</div>
        <div className="book-authors">{book.authors}</div>
      </div>
    )
  }
}

const mapStateToProps = ({books},{id}) =>{
  const book = books[id];
  return {
    book
  }
}
export default connect(mapStateToProps)(Book);
