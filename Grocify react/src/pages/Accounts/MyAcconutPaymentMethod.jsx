import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const MyAccountPaymentMethod = () => {
  const navigate = useNavigate();
  const user = JSON.parse(sessionStorage.getItem("user"));
  const userId = user?.user_id;

  // Load saved cards from local storage
  const [cards, setCards] = useState(() => {
    const savedCards = localStorage.getItem("savedCards");
    return savedCards ? JSON.parse(savedCards) : [];
  });

  // Load products from local storage
  const [productData, setProductData] = useState(() => {
    const storedProducts = localStorage.getItem("cart");
    return storedProducts ? JSON.parse(storedProducts) : [];
  });

  // State for new card details
  const [newCard, setNewCard] = useState({
    name: "",
    number: "",
    month: "",
    year: "",
    cvv: "",
  });

  // Update local storage when cards state changes
  useEffect(() => {
    localStorage.setItem("savedCards", JSON.stringify(cards));
  }, [cards]);

  // Handle input change for new card
  const handleInputChange = (e) => {
    setNewCard({ ...newCard, [e.target.name]: e.target.value });
  };

  // Add a new card
  const handleAddCard = (e) => {
    e.preventDefault();
    if (newCard.name && newCard.number && newCard.month && newCard.year && newCard.cvv) {
      setCards([...cards, newCard]);
      setNewCard({ name: "", number: "", month: "", year: "", cvv: "" }); // Reset form
      document.getElementById("closeModal").click(); // Close modal
    }
  };

  // Handle payment process
  const handlePayment = async (card) => {
    if (!userId) {
      alert("User not found. Please log in.");
      navigate("/MyAccountSignIn");
      return;
    }
  
    if (!productData.length) {
      alert("No products found in the cart!");
      navigate("/Grocery-react");
      return;
    }
  
    // Convert product names array into a single string (comma-separated)
    const productNames = productData.map((product) => product.productName).join(", ");
  
    // Format data as "metadata" for backend
    const metadata = {
    
      productNames: productNames // Convert array to string
    };
  
    console.log("Order Data JSON:", JSON.stringify(metadata, null, 2));
  
    try {
      const response = await fetch(`http://localhost:9090/order/${userId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ metadata }), // Send metadata with productNames as a string
      });
  
      if (!response.ok) throw new Error("Payment failed!");
  
      alert(`Payment successful for card ending in ${card.number.slice(-4)}!`);
      localStorage.removeItem("cart"); // Clear cart after payment
      navigate("/payment-success");
    } catch (error) {
      console.error("Payment error:", error);
      alert("Payment failed. Please try again.");
    }
  };
  
  
  

  return (
    <div className="container mt-5">
      <h2>Choose a Payment Method</h2>

      {/* List of stored cards */}
      <div className="mb-4">
        {cards.map((card, index) => (
          <div key={index} className="border p-3 rounded mb-2">
            <p><strong>{card.name}</strong></p>
            <p>Card Number: **** **** **** {card.number.slice(-4)}</p>
            <p>Expiry: {card.month}/{card.year}</p>
            {/* Pay Now Button for each card */}
            <button className="btn btn-success" onClick={() => handlePayment(card)}>
              Pay Now
            </button>
          </div>
        ))}
      </div>

      {/* Always Visible "Add New Card" Button */}
      <button className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCardModal">
        Add New Card
      </button>

      {/* Modal for Adding New Card */}
      <div className="modal fade" id="addCardModal" tabIndex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            
            {/* Modal Header */}
            <div className="modal-header">
              <h5 className="modal-title">Add New Payment Method</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" id="closeModal"></button>
            </div>

            {/* Modal Body */}
            <div className="modal-body">
              <form onSubmit={handleAddCard}>
                {/* Name on Card */}
                <div className="mb-3">
                  <label className="form-label">Name on Card</label>
                  <input type="text" className="form-control" name="name" value={newCard.name} onChange={handleInputChange} required />
                </div>

                {/* Card Number */}
                <div className="mb-3">
                  <label className="form-label">Card Number</label>
                  <input type="text" className="form-control" name="number" value={newCard.number} onChange={handleInputChange} placeholder="xxxx-xxxx-xxxx-xxxx" required />
                </div>

                {/* Expiry Month & Year */}
                <div className="row">
                  <div className="col-md-6 mb-3">
                    <label className="form-label">Month</label>
                    <select className="form-select" name="month" value={newCard.month} onChange={handleInputChange} required>
                      <option value="">Month</option>
                      {[...Array(12)].map((_, i) => (
                        <option key={i} value={(i + 1).toString().padStart(2, "0")}>
                          {new Date(0, i).toLocaleString("default", { month: "short" })}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="col-md-6 mb-3">
                    <label className="form-label">Year</label>
                    <select className="form-select" name="year" value={newCard.year} onChange={handleInputChange} required>
                      <option value="">Year</option>
                      {[...Array(10)].map((_, i) => (
                        <option key={i} value={new Date().getFullYear() + i}>
                          {new Date().getFullYear() + i}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>

                {/* CVV */}
                <div className="mb-3">
                  <label className="form-label">CVV</label>
                  <input type="password" className="form-control" name="cvv" value={newCard.cvv} onChange={handleInputChange} placeholder="xxx" required />
                </div>

                {/* Submit Button */}
                <button type="submit" className="btn btn-primary">Add New Card</button>
              </form>
            </div>

          </div>
        </div>
      </div>

    </div>
  );
};

export default MyAccountPaymentMethod;
