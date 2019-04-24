import React, { Component } from 'react'

export default class Book extends Component {
  render() {
    return (
      <div>
        <div className="book-cover" style={{ width: 128, height: 193, backgroundImage: `url()` }}></div>
        <div className="book-title">{}</div>
        <div className="book-authors">{}</div>
      </div>
    )
  }
}
