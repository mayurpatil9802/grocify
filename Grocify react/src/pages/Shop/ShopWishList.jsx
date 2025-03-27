import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { AiOutlineCloseCircle } from "react-icons/ai";

const ShopWishlist = () => {
  const [wishlist, setWishlist] = useState([]);

  // Load wishlist from localStorage on component mount
  useEffect(() => {
    const storedWishlist = JSON.parse(localStorage.getItem("wishlist")) || [];
    setWishlist(storedWishlist);
  }, []);

  // Remove item from wishlist
  const removeFromWishlist = (productId) => {
    const updatedWishlist = wishlist.filter((product) => product.id !== productId);
    setWishlist(updatedWishlist);
    localStorage.setItem("wishlist", JSON.stringify(updatedWishlist));
  };

  // Clear all wishlist items
  const clearWishlist = () => {
    setWishlist([]);
    localStorage.removeItem("wishlist");
  };

  return (
    <div className="container mx-auto p-6 max-w-3xl">
      <h1 className="text-3xl font-bold mb-4">My Wishlist</h1>

      {wishlist.length === 0 ? (
        <p className="text-gray-500">Your wishlist is empty.</p>
      ) : (
        <>
          <div className="grid gap-6">
            {wishlist.map((product) => (
              <div key={product.id} className="p-4 border rounded-lg shadow-md flex items-center">
                {/* Product Image */}
                <img
                  src={`http://localhost:9090/store/${product.storeId}/product/${product.id}/image`}
                  alt={product.productName}
                  className="w-24 h-24 object-cover rounded-lg"
                />

                {/* Product Details */}
                <div className="ml-4 flex-1">
                  <Link to={`/product/${product.id}`} className="text-lg font-semibold text-blue-600 hover:underline">
                    {product.productName}
                  </Link>
                  <p className="text-sm text-gray-500">{product.description}</p>
                  <p className="text-red-600 font-semibold">Price: ${product.price}</p>
                  <p className="text-green-600">Stock: {product.availableUnit} {product.unit}</p>
                </div>

                {/* Remove Button */}
                <button
                  onClick={() => removeFromWishlist(product.id)}
                  className="text-red-500 hover:text-red-700 text-xl"
                >
                  <AiOutlineCloseCircle />
                </button>
              </div>
            ))}
          </div>

          {/* Clear Wishlist Button */}
          <button
            onClick={clearWishlist}
            className="mt-6 px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700"
          >
            Clear Wishlist
          </button>
        </>
      )}
    </div>
  );
};

export default ShopWishlist;
