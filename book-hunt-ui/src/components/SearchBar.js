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

 

  render() {
    return (
      <div className="">
      <div className="search-books-bar">
          <input className="col" type="text" maxLength="255" name="searchText"  placeholder="Search by title" onChange={this.handleChange}/>
      </div>
      </div>
    )
  }
}

export default SearchBar