import React, { useState, useEffect } from "react";

const OrderList = () => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  const userId = user?.user_id;

  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!userId) {
      alert("User not found. Please log in.");
      setLoading(false);
      return;
    }

    fetch(`http://localhost:9090/order/${userId}/orderdetail`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch orders");
        }
        return response.json();
      })
      .then((data) => {
        setOrders(data);
      })
      .catch((error) => {
        console.error("Error fetching order details:", error);
        alert("Error fetching order details");
      })
      .finally(() => {
        setLoading(false);
      });
  }, [userId]);

  // Function to format date (YYYY-MM-DD)
  const formatDate = (dateString) => {
    return dateString ? dateString : "N/A"; // Since it's already in YYYY-MM-DD format
  };

  // Extracts product names from metadata
  const extractProductNames = (metadata) => {
    if (!metadata || !metadata.productNames) return "No Products";
    return metadata.productNames; // Already a comma-separated string
  };

  return (

    <div className="container mt-5">
      <h2>My Orders</h2>

      {loading ? (
        <p>Loading orders...</p>
      ) : orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Product Name(s)</th>
              <th>Order Taken Date</th>
              <th>Order Delivered Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order, index) => (
              <tr key={index}>
                <td>{order.orderId}</td>
                <td>{extractProductNames(order.metadata)}</td>
                <td>{formatDate(order.orderTakeTime)}</td>
                <td>{formatDate(order.orderDeliveredTime)}</td>
                <td>{order.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default OrderList;
