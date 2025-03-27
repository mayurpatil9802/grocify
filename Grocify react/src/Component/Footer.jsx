import React from "react";
import { Link } from "react-router-dom";
// import "@fortawesome/fontawesome-free/css/all.min.css";
import groceryshop from "../images/Grocerylogo.png";
import amazonpay from "../images/amazonpay.svg";
import american from "../images/american-express.svg";
import mastercard from "../images/mastercard.svg";
import paypal from "../images/paypal.svg";
import visa from "../images/visa.svg";

const Footer = () => {
  let date = new Date();
  let year = date.getFullYear();
  return (
    <div className="footer-widget">
                  <div className="footer-logo">
                    <Link to="/">
                      <img
                        src={groceryshop}
                        style={{ width: 300, padding: 20, marginLeft: "-30px" }}
                        alt="logo"
                      />
                    </Link>
                  </div>
                  <p className="mb-30">
                    We deliver more than your expectations and help you to grow
                    your business exponentially by providing customized
                    applications. So, don’t just think, get ready to convert
                    your ideas into reality.
                  </p>
                </div>
    
  );
};

export default Footer;
