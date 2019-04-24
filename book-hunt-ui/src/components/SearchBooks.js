import React, { Component } from 'react';
import SearchBar from "./SearchBar";
import FilteredBooks from "./FilteredBooks";
import Testss from "./Testss";
import {connect} from "react-redux";


class SearchBooks extends Component {
    state = {
        searchText : ""
    }

    onSearchTextChange = (value) => {
        console.log("SearchBooks :",value);
        this.setState({searchText :value});
    }
    render() {
        console.log("in searchbooks render ");
        return (
        <div>
        <SearchBar onSearchTextChange={this.onSearchTextChange}></SearchBar>
        <FilteredBooks searchText={this.searchText}></FilteredBooks>
        <Testss searchText={this.searchText}></Testss>
        </div>
        )
    }
}

export default SearchBooks;
