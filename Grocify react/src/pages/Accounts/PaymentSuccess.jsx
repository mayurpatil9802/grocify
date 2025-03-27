import React from "react";
import { useNavigate } from "react-router-dom";

const PaymentSuccess = () => {
  const navigate = useNavigate();

  return (
    <div className="d-flex flex-column align-items-center justify-content-center vh-100 bg-success text-white">
      <h1>🎉 Payment Successful!</h1>
      <p>Thank you for your purchase.</p>
      <button className="btn btn-light mt-3" onClick={() => navigate("/Grocery-react")}>
        Go Back to Home
      </button>
    </div>
  );
};

export default PaymentSuccess;
