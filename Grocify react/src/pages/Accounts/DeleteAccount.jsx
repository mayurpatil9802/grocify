import React, { useState } from "react";
import { useNavigate } from "react-router-dom";


const DeleteAccount = () => {
  const navigate = useNavigate();
  const [message, setMessage] = useState("");
  let user = sessionStorage.getItem("user"); // Assuming user_id is stored in localStorage
  user = JSON.parse(user);
  console.log(user.user_id);

  // Step 3: Delete the user_id property
  // delete user.user_id;

  const handleDelete = async () => {
    if (!user) {
      setMessage("User ID not found!");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/users/${user.user_id}`, {
        method: "DELETE",
      });
      console.log(response.data);

      if (response.ok) {
        setMessage("Account deleted successfully!");
        sessionStorage.removeItem("user"); // Remove user ID from storage
        navigate("/Grocery-react");
      } else {
        setMessage("Failed to delete account. Please try again.");
      }
    } catch (error) {
      setMessage("Error deleting account. Check API connection.");
    }
    
  };

  return (
    <div className="container mt-5">
      <h2>Delete Account</h2>
      <p>Are you sure you want to delete your account?</p>
      <button className="btn btn-danger" onClick={handleDelete}>
        Delete My Account
      </button>
      {message && <p className="mt-3">{message}</p>}
    </div>
  );
};

export default DeleteAccount;
