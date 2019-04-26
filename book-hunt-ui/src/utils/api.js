import axios from "axios";
const host="http://localhost:8080";

export const getBooks = async (searchText,page) =>{
    return await axios.get(`${host}/books/search?page=${page}&q=${searchText}`);
}
 
