import React, { Component } from 'react';
import {debounce} from 'throttle-debounce';


class SearchBar extends Component {
  constructor(props){
    super(props);
    this.debounceHandleChange = debounce(500, this.props.onSearchTextChange);
  }

  handleChange = (e) => {
    const searchText = e.target.value;
    console.log("SearchBar :",searchText);
    this.debounceHandleChange(searchText);
  }

  render() {
    return (
      <div>
          <input type="text" placeholder="Search by title" onChange={this.handleChange}/>
      </div>
    )
  }
}

export default SearchBar