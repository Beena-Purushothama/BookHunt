import React from 'react'

const handlePagination = (e,props) => {
    e.preventDefault();
    const page = e.target.getAttribute("value");
    props.onPagination(page);
}

export default function pagination(props) {
  return (
    <div>
       <nav aria-label="Page navigation ">
            <ul className="pagination ">
              <li className="page-item"><button className="page-link" value="0" onClick={(e)=>handlePagination(e,props)} >1</button></li>
              <li className="page-item"><button className="page-link" value="1" onClick={(e)=>handlePagination(e,props)} >2</button></li>
              <li className="page-item"><button className="page-link" value="2" onClick={(e)=>handlePagination(e,props)} >3</button></li>
              <li className="page-item"><button className="page-link" value="3" onClick={(e)=>handlePagination(e,props)} >4</button></li>
            </ul>
      </nav>
    </div>
  )
}


