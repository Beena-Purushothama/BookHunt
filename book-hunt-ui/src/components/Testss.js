import React, { Component } from 'react';
import {connect} from "react-redux";


 class Testss extends Component {
   componentDidUpdate =() => {
     console.log(" in update");
   }
  render() {
    return (
      <div>
        {console.log("in test", this.props.searchText)}
      </div>
    )
  }
}


const mapStateToProps = ({books},{searchText}) => {
  console.log("searchText---",searchText)
  return ({
  books,
  searchText
})}

export default connect(mapStateToProps)(Testss);