import React, { Component } from 'react';
import SearchBar from "./SearchBar";
import FilteredBooks from "./FilteredBooks";

class SearchBooks extends Component {
    state = {
        searchText : "",
    }

    onSearchTextChange = (value) => {
        console.log("SearchBooks :",value);
        this.setState({searchText :value});
    }

    render() {
        console.log("in searchbooks render ");
        return (
        <div className="">
        <SearchBar onSearchTextChange={this.onSearchTextChange} ></SearchBar>
        <FilteredBooks searchText={this.state.searchText} page={this.state.page}></FilteredBooks>
        </div>
        )
    }
}

export default SearchBooks;
