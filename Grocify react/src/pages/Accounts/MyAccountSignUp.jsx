import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthService from "../../service/AuthService";
import { Link } from "react-router-dom";

const MyAccountSignUp = () => {
  const [formData, setFormData] = useState({
    emailId: "",
    firstName: "",
    lastName: "",
    mobileNo: "",
    address: "",
    password: "",
    confirmPassword: "",
    role: "CUSTOMER",
    metadata: {},
  });

  const [error, setError] = useState("");
  const [loadingLocation, setLoadingLocation] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleMetadataChange = (e) => {
    setFormData({
      ...formData,
      metadata: { ...formData.metadata, [e.target.name]: e.target.value },
    });
  };

  // Function to get current location
  const getLocation = () => {
  if (!navigator.geolocation) {
    alert("Geolocation is not supported by your browser.");
    return;
  }

  setLoadingLocation(true);
  navigator.geolocation.getCurrentPosition(
    async (position) => {
      const { latitude, longitude } = position.coords;
      console.log("Latitude:", latitude, "Longitude:", longitude);

      try {
        // Replace YOUR_GOOGLE_MAPS_API_KEY with your actual API key
        const response = await fetch(
          `https://maps.googleapis.com/maps/api/geocode/json?latlng=${latitude},${longitude}&key=YOUR_GOOGLE_MAPS_API_KEY`
        );
        const data = await response.json();

        if (data.status === "OK" && data.results.length > 0) {
          const formattedAddress = data.results[0].formatted_address;
          setFormData({ ...formData, address: formattedAddress });
        } else {
          alert("Could not fetch address. Please enter manually.");
        }
      } catch (error) {
        console.error("Error fetching address:", error);
        alert("Error fetching location data.");
      } finally {
        setLoadingLocation(false);
      }
    },
    (error) => {
      console.error("Geolocation error:", error);
      alert("Could not retrieve location. Please enable location services.");
      setLoadingLocation(false);
    }
  );
};

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }
    setError("");

    try {
      const { confirmPassword, ...userData } = formData;
      const response = await AuthService.signUp(userData);
      console.log("User registered successfully:", response.data);
      alert("Registration successful!");
    } catch (error) {
      console.error("Error registering user:", error);
      alert("Error during registration. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">Register</h2>
      <form onSubmit={handleSubmit} className="w-50 mx-auto border p-4 rounded shadow">
        <div className="mb-3">
          <label className="form-label">Email ID</label>
          <input type="email" className="form-control" name="emailId" value={formData.emailId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">First Name</label>
          <input type="text" className="form-control" name="firstName" value={formData.firstName} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Last Name</label>
          <input type="text" className="form-control" name="lastName" value={formData.lastName} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Mobile No</label>
          <input type="tel" className="form-control" name="mobileNo" value={formData.mobileNo} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Address</label>
          <div className="d-flex">
            <textarea className="form-control me-2" name="address" value={formData.address} onChange={handleChange} required />
            <button type="button" className="btn btn-secondary" onClick={getLocation} disabled={loadingLocation}>
              {loadingLocation ? "Locating..." : "Get Location"}
            </button>
          </div>
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Confirm Password</label>
          <input type="password" className="form-control" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange} required />
          {error && <p className="text-danger mt-1">{error}</p>}
        </div>
        <div className="mb-3">
          <label className="form-label">Role</label>
          <select className="form-control" name="role" value={formData.role} onChange={handleChange}>
            <option value="CUSTOMER">Customer</option>
            <option value="STORE_OWNER">Store Owner</option>
            <option value="DELIVERY_AGENT">Delivery Agent</option>
          </select>
        </div>
        {formData.role === "DELIVERY_AGENT" && (
          <>
            <div className="mb-3">
              <label className="form-label">Bike No</label>
              <input type="text" className="form-control" name="bikeNo" value={formData.metadata.bikeNo || ""} onChange={handleMetadataChange} required />
            </div>
            <div className="mb-3">
              <label className="form-label">License No</label>
              <input type="text" className="form-control" name="licenseNo" value={formData.metadata.licenseNo || ""} onChange={handleMetadataChange} required />
            </div>
          </>
        )}
        <button type="submit" className="btn btn-primary w-100">Register</button>
      </form>
    </div>
  );
};

export default MyAccountSignUp;
