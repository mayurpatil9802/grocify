import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { login } from "../../service/login";

function MyAccountSignIn() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  
  const navigate = useNavigate();

  // Handle form input changes
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Handle login form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await login(formData);
      console.log("Full response:", response);

      // Ensure response contains user and token
      if (!response?.user || !response?.token) {
        throw new Error("Invalid login response. Please check credentials.");
      }

      // Store user data and token in sessionStorage
      sessionStorage.setItem("user", JSON.stringify(response.user));
      sessionStorage.setItem("token", response.token);

      // Navigate to dashboard after successful login
      navigate("/Grocery-react", { state: { user: response.user, token: response.token } });
    } catch (error) {
      console.error("Error logging in:", error);
      alert("Invalid username or password. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Sign In</h2>

      {/* Login Form */}
      <form onSubmit={handleSubmit} className="w-50 mx-auto">
        <div className="mb-3">
          <label className="form-label">Username</label>
          <input 
            type="text" 
            name="username" 
            className="form-control"
            value={formData.username} 
            onChange={handleChange} 
            required 
            placeholder="Enter your username"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Password</label>
          <input 
            type="password" 
            name="password" 
            className="form-control"
            value={formData.password} 
            onChange={handleChange} 
            required 
            placeholder="Enter your password"
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">Login</button>
      </form>

      {/* Sidebar Navigation */}
      <div className="row mt-5">
        <div className="col-lg-3 col-md-4 col-12 border-end d-none d-md-block">
          <h4>Account Settings</h4>
          <ul className="nav flex-column">
            <li className="nav-item">
              <Link className="nav-link active" to="/MyAccountOrder">
                <i className="fas fa-shopping-bag me-2" /> Your Orders
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/MyAccountSetting">
                <i className="fas fa-cog me-2" /> Settings
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/MyAccountAddress">
                <i className="fas fa-map-marker-alt me-2" /> Address
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/MyAccountPaymentMethod">
                <i className="fas fa-credit-card me-2" /> Payment Method
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/MyAcconutDelete">
                <i className="fas fa-bell me-2" /> Account delete
              </Link>
            </li>
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
    </div>
  );
}

export default MyAccountSignIn;
