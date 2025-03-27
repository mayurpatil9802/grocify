import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const OrdersPage = () => {
  const navigate = useNavigate();
  const [orders, setOrders] = useState([]);
  const [userAddresses, setUserAddresses] = useState({}); // Store user addresses by user ID
  const user = JSON.parse(sessionStorage.getItem("user"));
  const userRole = user?.role;
  const deliveryId = user?.user_id; // Assuming delivery person's ID is stored here

  // Restrict access to delivery agents only
  useEffect(() => {
    if (userRole !== "DELIVERY_AGENT") {
      navigate("/unauthorized");
    }
  }, [userRole, navigate]);

  // Fetch orders assigned to the delivery agent
  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await fetch(`http://localhost:9090/order/${deliveryId}`);
        if (!response.ok) throw new Error("Failed to fetch orders");
        const data = await response.json();
        setOrders(data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };

    if (deliveryId) {
      fetchOrders();
    }
  }, [deliveryId]);

  // Fetch user address by user ID
  const fetchUserAddress = async (userId) => {
    if (userAddresses[userId]) return; // Avoid redundant API calls

    try {
      const response = await fetch(`http://localhost:8080/users/${userId}`);
      if (!response.ok) throw new Error("Failed to fetch user address");
      const data = await response.json();

      setUserAddresses((prev) => ({
        ...prev,
        [userId]: data.address, // Assuming response contains `address`
      }));
    } catch (error) {
      console.error(`Error fetching address for user ${userId}:`, error);
    }
  };

  // Accept order
  const handleAccept = async (userId, orderId) => {
    try {
      const response = await fetch(`http://localhost:9090/order/${userId}/orderId/${orderId}/accept/${deliveryId}`, {
        method: "GET",
      });
      if (!response.ok) throw new Error("Failed to accept order");

      alert(`Order ${orderId} accepted successfully!`);
      setOrders((prevOrders) => prevOrders.filter((order) => order.orderId !== orderId)); // Remove accepted order from UI
    } catch (error) {
      console.error("Error accepting order:", error);
    }
  };

  // Cancel order
  const handleCancel = async (userId, orderId) => {
    try {
      const response = await fetch(`http://localhost:9090/order/${userId}/orderId/${orderId}/cancel`, {
        method: "GET",
      });
      if (!response.ok) throw new Error("Failed to cancel order");

      alert(`Order ${orderId} canceled successfully!`);
      setOrders((prevOrders) => prevOrders.filter((order) => order.orderId !== orderId)); // Remove canceled order from UI
    } catch (error) {
      console.error("Error canceling order:", error);
    }
  };

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Orders</h1>
      {orders.length === 0 ? (
        <p>No orders available.</p>
      ) : (
        <table className="w-full border-collapse border border-gray-200">
          <thead>
            <tr className="bg-gray-100">
              <th className="border p-2">Order ID</th>
              <th className="border p-2">User Address</th>
              <th className="border p-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => {
              // Fetch address if not already fetched
              if (!userAddresses[order.userId]) {
                fetchUserAddress(order.userId);
              }

              return (
                <tr key={order.orderId} className="border">
                  <td className="border p-2 text-center">{order.orderId}</td>
                  <td className="border p-2 text-center">
                    {userAddresses[order.userId] || "Fetching..."}
                  </td>
                  <td className="border p-2 flex justify-center space-x-2">
                    <button
                      className="bg-green-500 text-white px-4 py-1 rounded"
                      onClick={() => handleAccept(order.userId, order.orderId)}
                    >
                      Accept
                    </button>
                    <button
                      className="bg-red-500 text-white px-4 py-1 rounded"
                      onClick={() => handleCancel(order.userId, order.orderId)}
                    >
                      Cancel
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default OrdersPage;
