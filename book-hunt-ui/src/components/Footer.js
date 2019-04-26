import React from 'react'

export default function Footer() {
    const host="https://www.northwesternmutual.com";
  return (
    <div className="footer ">
      <div className="container "> 
      <div className="col ">
        <ul> 
        <li> 
            <a href="#" title="Who We Are - Mutual Company Information"> About Us </a> 
            <a href="#" title="Newsroom" rel="noopener"> Newsroom </a> 
            <a href="#" title="Careers"> Careers </a>
            <a href="#" title="Security and Privacy"> Security Privacy </a> 
        </li> 
        </ul> 
        </div>
        <div className="col ">
        <ul> 
        <li> 
            <a href="#" title="Business Services - Financial Planning for Businesses"> Business Services </a>
            <a href="#" title="Contact Us"> Contact Us </a>
            <a href="#" title="Legal Information"> Legal Notice </a> 
        </li>
        </ul> 
        </div>
        <div className="col ">
        <ul> 
        <li> 
            <a href="#" title="Spanish Site" rel="noopener" hrefLang="es"> Español </a> 
            <a href="#" title="Sitemap"> Sitemap </a>
            <a href="#" title="Online Privacy Statement"> Online Privacy </a> 
        </li>
       </ul> 
       </div>
       </div>
    </div>
  )
}
