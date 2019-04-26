import React, { Component } from 'react';
import {debounce} from 'throttle-debounce';


class SearchBar extends Component {
  constructor(props){
    super(props);
    this.debounceHandleChange = debounce(500, this.props.onSearchTextChange);
  }

  handleChange = (e) => {
    const searchText = e.target.value;
    this.debounceHandleChange(searchText);
  }

  handlePagination = (e) => {
    e.preventDefault();
    const page = e.target.getAttribute("value");
    this.props.onPagination(page);
  }

  render() {
    return (
      <div className="">
      <div className="search-books-bar">
          <input className="col" type="text" maxLength="255" name="searchText"  placeholder="Search by title" onChange={this.handleChange}/>
      </div>
      <nav aria-label="Page navigation ">
            <ul className="pagination ">
              <li className="page-item"><button className="page-link" value="0" onClick={this.handlePagination} >1</button></li>
              <li className="page-item"><button className="page-link" value="1" onClick={this.handlePagination} >2</button></li>
              <li className="page-item"><button className="page-link" value="2" onClick={this.handlePagination} >3</button></li>
              <li className="page-item"><button className="page-link" value="3" onClick={this.handlePagination} >4</button></li>
            </ul>
          </nav>
      </div>
    )
  }
}

export default SearchBar