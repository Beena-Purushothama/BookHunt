import React, { Component } from 'react';
import SearchBar from "./SearchBar";
import FilteredBooks from "./FilteredBooks";

class SearchBooks extends Component {
    state = {
        searchText : "",
        page : 0
    }

    onSearchTextChange = (value) => {
        console.log("SearchBooks :",value);
        this.setState({searchText :value});
    }

    onPagination = (value) => {
        console.log("page :",value);
        this.setState({page:value});
    }

    render() {
        console.log("in searchbooks render ");
        return (
        <div className="">
        <SearchBar onSearchTextChange={this.onSearchTextChange} onPagination={this.onPagination}></SearchBar>
        <FilteredBooks searchText={this.state.searchText} page={this.state.page}></FilteredBooks>
        </div>
        )
    }
}

export default SearchBooks;
