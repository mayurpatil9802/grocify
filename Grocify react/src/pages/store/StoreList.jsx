import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { MagnifyingGlass } from "react-loader-spinner";
import ScrollToTop from "../ScrollToTop";

const StoreList = () => {
  // Loading state
  const navigate = useNavigate();
  const [loaderStatus, setLoaderStatus] = useState(true);
  const [product, setProduct] = useState({
    productName: "",
    description: "",
    price: "",
    unit: "",
    availableUnit: "",
    metadata: {},
  });
  const [image, setImage] = useState(null);
  const [productId, setProductId] = useState(null);
  const storeId = JSON.parse(sessionStorage.getItem("user")); // Change this dynamically if needed
  const userRole = storeId?.role;
    useEffect(() => {
      if (userRole !== "STORE_OWNER") {
        navigate("/unauthorized");
      }
    }, [userRole, navigate]);

  useEffect(() => {
    setTimeout(() => {
      setLoaderStatus(false);
    }, 1500);
  }, []);

  // Handle input change
  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  // Handle metadata input
  const handleMetadataChange = (e) => {
    setProduct({
      ...product,
      metadata: { ...product.metadata, [e.target.name]: e.target.value },
    });
  };

  // Handle file selection
  const handleFileChange = (e) => {
    setImage(e.target.files[0]);
  };

  // Submit product
  const submitProduct = async () => {
    try {
      const response = await fetch(`http://localhost:9090/store/${storeId.user_id}/product`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product),
      });

      if (response.ok) {
        const data = await response.json();
        setProductId(data.id); // Store product ID for image upload
        alert("Product added successfully!");
      } else {
        alert("Failed to add product");
      }
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  // Upload product image
  const uploadImage = async () => {
    if (!image || !productId) {
      alert("Please select an image and add a product first.");
      return;
    }

    const formData = new FormData();
    formData.append("imageFile", image);

    try {
      const response = await fetch(`http://localhost:9090/store/${storeId.user_id}/product/${productId}/image`, {
        method: "PUT",
        body: formData,
      });

      if (response.ok) {
        alert("Image uploaded successfully!");
      } else {
        alert("Error uploading image");
      }
    } catch (error) {
      console.error("Error uploading image:", error);
    }
  };

  return (
    <div>
      {loaderStatus ? (
        <div className="loader-container">
          <MagnifyingGlass visible={true} height="100" width="100" color="#0aad0a" />
        </div>
      ) : (
        <>
          <ScrollToTop />

          <div className="container mt-4">
            <h2>Add New Product</h2>
            <form>
              <div className="mb-3">
                <label className="form-label">Product Name</label>
                <input type="text" className="form-control" name="productName" value={product.productName} onChange={handleChange} />
              </div>

              <div className="mb-3">
                <label className="form-label">Description</label>
                <textarea className="form-control" name="description" value={product.description} onChange={handleChange} />
              </div>

              <div className="mb-3">
                <label className="form-label">Price</label>
                <input type="number" className="form-control" name="price" value={product.price} onChange={handleChange} />
              </div>

              <div className="mb-3">
                <label className="form-label">Unit</label>
                <input type="text" className="form-control" name="unit" value={product.unit} onChange={handleChange} />
              </div>

              <div className="mb-3">
                <label className="form-label">Available Units</label>
                <input type="number" className="form-control" name="availableUnit" value={product.availableUnit} onChange={handleChange} />
              </div>

              {/* Metadata Inputs */}
              <div className="mb-3">
                <label className="form-label">Metadata (Key: Value)</label>
                <input type="text" className="form-control" name="brand" placeholder="Brand Name" onChange={handleMetadataChange} />
                <input type="text" className="form-control mt-2" name="expiryDate" placeholder="Expiry Date" onChange={handleMetadataChange} />
              </div>

              <button type="button" className="btn btn-primary" onClick={submitProduct}>
                Add Product
              </button>
            </form>

            {/* Image Upload Section */}
            {productId && (
              <div className="mt-4">
                <h3>Upload Product Image</h3>
                <input type="file" className="form-control mb-2" accept="image/*" onChange={handleFileChange} />
                <button type="button" className="btn btn-success" onClick={uploadImage}>
                  Upload Image
                </button>
              </div>
            )}
          </div>
        </>
      )}
    </div>
  );
};

export default StoreList;
