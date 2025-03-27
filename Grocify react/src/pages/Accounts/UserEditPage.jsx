import React, { useState, useEffect } from "react";
import { useNavigate,Link } from "react-router-dom";
import { updateUserProfile } from "../../service/updateUserProfile";

function UserEditPage() {
  const navigate = useNavigate();
  
  // Load user data from sessionStorage
  const storedUser = sessionStorage.getItem("user");

  const [user, setUser] = useState(() => {
    try {
      return storedUser ? JSON.parse(storedUser) : {}; // Parse only if data exists
    } catch (error) {
      console.error("Error parsing user data from sessionStorage:", error);
      return {}; // Return empty object if parsing fails
    }
  });
  

  useEffect(() => {
    if (!storedUser) {
      navigate("/MyAccountSignIn"); // Redirect to login if no user data
    }
  }, [navigate, storedUser]);

  // Handle form input changes
  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  // Handle form submission (simulate API call)
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      // Simulate API update (replace with actual API call)
      console.log("Updating user data:", user);

      const response = await updateUserProfile(user.user_id,user);
      
      
      // Save updated user data to sessionStorage
      sessionStorage.setItem("user", JSON.stringify(response));

      // ✅ Update local state to reflect changes
      setUser(response.user);
      



      alert("Profile updated successfully!");
      navigate("/profile"); // Redirect to profile page
    } catch (error) {
      console.error("Error updating profile:", error);
      alert("Failed to update profile. Please try again.");
    }
  };

  return (
    <div>
      <div className="col-lg-3 col-md-4 col-12 border-end  d-none d-md-block">
                      <div className="pt-10 pe-lg-10">
                        {/* nav */}
                        <ul className="nav flex-column nav-pills nav-pills-dark">
                          {/* nav item */}
                          <li className="nav-item">
                            <Link
                              className="nav-link active"
                              aria-current="page"
                              to="/MyAccountOrder"
                            >
                              <i className="fas fa-shopping-bag me-2" />
                              Your Orders
                            </Link>
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
                            <Link className="nav-link" to="/MyAccountSetting">
                              <i className="fas fa-cog me-2" />
                              Settings
                            </Link>
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
                            <Link className="nav-link" to="/MyAccountAddress">
                              <i className="fas fa-map-marker-alt me-2" />
                              Address
                            </Link>
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
                            <Link className="nav-link" to="/MyAcconutPaymentMethod">
                              <i className="fas fa-credit-card me-2" />
                              Payment Method
                            </Link>
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
                            <Link className="nav-link" to="/MyAcconutDelete">
                              <i className="fas fa-bell me-2" />
                              Acconut Delete
                            </Link>
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
                            <hr />
                          </li>
                          {/* nav item */}
                          <li className="nav-item">
  <Link
    className="nav-link"
    to="/MyAccountSignIn" // Redirect to login after logout
    onClick={() => {
      sessionStorage.removeItem("user"); // Clear session
      localStorage.removeItem("cart"); // (Optional) Clear cart
    }}
  >
    <i className="fas fa-sign-out-alt me-2" />
    Log out
  </Link>
</li>
                        </ul>
                      </div>
                    </div>
                    {/* </div> */}
    
    <div className="container mt-5">
      <h2>Edit Profile</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">First Name</label>
          <input
            type="text"
            name="firstName"
            className="form-control"
            value={user.firstName || ""}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Last Name</label>
          <input
            type="text"
            name="lastName"
            className="form-control"
            value={user.lastName || ""}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Email</label>
          <input
            type="email"
            name="emailId"
            className="form-control"
            value={user.emailId || ""}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Phone</label>
          <input
            type="text"
            name="mobileNo"
            className="form-control"
            value={user.mobileNo || ""}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Address</label>
          <input
            type="text"
            name="address"
            className="form-control"
            value={user.address || ""}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary">
          Save Changes
        </button>
        <button type="button" className="btn btn-secondary ms-2" onClick={() => navigate("/profile")}>
          Cancel
        </button>
      </form>
    </div>
    </div>
  );
}

export default UserEditPage;
