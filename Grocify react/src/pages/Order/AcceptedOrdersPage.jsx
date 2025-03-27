import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const AcceptedOrdersPage = () => {
  const navigate = useNavigate();
  const [acceptedOrders, setAcceptedOrders] = useState([]);
  const [userAddresses, setUserAddresses] = useState({});
  const user = JSON.parse(sessionStorage.getItem("user"));
  const userRole = user?.role;
  const deliveryId = user?.user_id;

  // Restrict access to delivery agents only
  useEffect(() => {
    if (userRole !== "DELIVERY_AGENT") {
      navigate("/unauthorized");
    }
  }, [userRole, navigate]);

  // Fetch accepted orders
  useEffect(() => {
    const fetchAcceptedOrders = async () => {
      try {
        const response = await fetch(`http://localhost:9090/order/${user?.user_id}/${deliveryId}`);
        if (!response.ok) throw new Error("Failed to fetch accepted orders");
        const data = await response.json();
        setAcceptedOrders(data);
      } catch (error) {
        console.error("Error fetching accepted orders:", error);
      }
    };

    if (deliveryId) {
      fetchAcceptedOrders();
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

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Accepted Orders</h1>
      {acceptedOrders.length === 0 ? (
        <p>No accepted orders available.</p>
      ) : (
        <table className="w-full border-collapse border border-gray-200">
          <thead>
            <tr className="bg-gray-100">
              <th className="border p-2">Order ID</th>
              <th className="border p-2">Customer ID</th>
              <th className="border p-2">Customer Address</th>
              <th className="border p-2">Delivery Status</th>
            </tr>
          </thead>
          <tbody>
            {acceptedOrders.map((order) => {
              // Fetch address if not already fetched
              if (!userAddresses[order.userId]) {
                fetchUserAddress(order.userId);
              }

              return (
                <tr key={order.orderId} className="border">
                  <td className="border p-2 text-center">{order.orderId}</td>
                  <td className="border p-2 text-center">{order.userId}</td>
                  <td className="border p-2 text-center">
                    {userAddresses[order.userId] || "Fetching..."}
                  </td>
                  <td className="border p-2 text-center text-green-600">Accepted</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AcceptedOrdersPage;
