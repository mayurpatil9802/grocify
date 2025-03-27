import React, { useState } from "react";
import forgetpassword from "../../images/fp-g.svg";
import { Link } from "react-router-dom";
import ScrollToTop from "../ScrollToTop";

const MyAccountForgetPassword = () => {
  const [email, setEmail] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [userId, setUserId] = useState(null);

  // Handle Email Submission (Fake API Call for User ID)
  const handleEmailSubmit = (e) => {
    e.preventDefault();
    
    // Retrieve user data from sessionStorage
    const storedUser = JSON.parse(sessionStorage.getItem("user"));

    if (!storedUser || !storedUser.user_id) {
        alert("User not found in session. Please log in.");
        return;
    }

    // Extract user ID from session
    setUserId(storedUser.user_id);
    setShowModal(true); // Show modal for password reset
};

  // Handle Password Reset Submission
  const handleResetPassword = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    // Retrieve token from sessionStorage
    const token = sessionStorage.getItem("token");
    console.log("Retrieved Token:", token);

    if (!token) {
        alert("User is not authenticated. Please log in.");
        return;
    }

    // Debugging: Log request data before sending
    console.log("Sending request to:", `http://localhost:8080/users/password/${userId}`);
    console.log("Request Headers:", {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    });
    console.log("Request Body:", JSON.stringify({ password }));

    try {
        const response = await fetch(`http://localhost:8080/users/password/${userId}`, {
            method: "PUT",
            headers: { 
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ password }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Failed to reset password!");
        }

        alert("Password reset successfully!");
        setShowModal(false);
    } catch (error) {
        console.error("Error resetting password:", error);
        alert("Error: " + error.message);
    }
};

  
  
  

  return (
    <div>
      <ScrollToTop />
      
      {/* Forgot Password Section */}
      <section className="my-lg-14 my-8">
        <div className="container">
          <div className="row justify-content-center align-items-center">
            <div className="col-12 col-md-6 col-lg-4 order-lg-1 order-2">
              <img src={forgetpassword} alt="freshcart" className="img-fluid" />
            </div>
            <div className="col-12 col-md-6 offset-lg-1 col-lg-4 order-lg-2 order-1 d-flex align-items-center">
              <div>
                <div className="mb-lg-9 mb-5">
                  <h1 className="mb-2 h2 fw-bold">Forgot your password?</h1>
                  <p>Enter your email, and we'll send a reset link.</p>
                </div>
                
                {/* Email Submission Form */}
                <form onSubmit={handleEmailSubmit}>
                  <div className="row g-3">
                    <div className="col-12">
                      <input
                        type="email"
                        className="form-control"
                        placeholder="Email"
                        required
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                      />
                      <span className="navbar-text">
                        Already have an account? <Link to="/MyAccountSignIn">Sign in</Link>
                      </span>
                    </div>

                    <div className="col-12 d-grid gap-2">
                      <button type="submit" className="btn btn-primary">Submit</button>
                      <Link to="/MyAccountSignUp" className="btn btn-light">Back</Link>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Modal for Reset Password */}
      {showModal && (
        <div className="modal show d-block" tabIndex="-1" style={{ background: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              
              {/* Modal Header */}
              <div className="modal-header">
                <h5 className="modal-title">Reset Password</h5>
                <button type="button" className="btn-close" onClick={() => setShowModal(false)}></button>
              </div>

              {/* Modal Body */}
              <div className="modal-body">
                <form onSubmit={handleResetPassword}>
                  {/* New Password */}
                  <div className="mb-3">
                    <label className="form-label">New Password</label>
                    <input
                      type="password"
                      className="form-control"
                      required
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>

                  {/* Confirm Password */}
                  <div className="mb-3">
                    <label className="form-label">Confirm Password</label>
                    <input
                      type="password"
                      className="form-control"
                      required
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                  </div>

                  {/* Submit Button */}
                  <div className="text-end">
                    <button type="submit" className="btn btn-success">Update Password</button>
                  </div>
                </form>
              </div>

            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default MyAccountForgetPassword;
