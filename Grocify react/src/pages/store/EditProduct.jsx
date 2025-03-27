import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { TextField, Button, Box, Typography, IconButton, Modal } from "@mui/material";
import { Add, Delete, Upload } from "@mui/icons-material";

const EditProduct = () => {
  const { productId } = useParams();
  const navigate = useNavigate();
  const storeId = JSON.parse(sessionStorage.getItem("user"));

  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [image, setImage] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    if (!storeId || !productId) return;

    const fetchProduct = async () => {
      try {
        const response = await fetch(`http://localhost:9090/store/${storeId.user_id}/product/${productId}`);
        const data = await response.json();

        setProduct((prev) => prev ?? { ...data, metadata: data.metadata || {} });
        setLoading(false);
      } catch (error) {
        console.error("Error fetching product:", error);
        setLoading(false);
      }
    };

    if (!product) fetchProduct();
  }, [storeId, productId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({ ...prev, [name]: value }));
  };

  // Handle Metadata Changes
  const handleMetadataChange = (key, value) => {
    setProduct((prev) => ({
      ...prev,
      metadata: { ...prev.metadata, [key]: value },
    }));
  };

  // Add New Metadata Field
  const addMetadataField = () => {
    setProduct((prev) => ({
      ...prev,
      metadata: { ...prev.metadata, "": "" },
    }));
  };

  // Remove Metadata Field
  const removeMetadataField = (key) => {
    const updatedMetadata = { ...product.metadata };
    delete updatedMetadata[key];

    setProduct((prev) => ({
      ...prev,
      metadata: updatedMetadata,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!product) return;

    try {
      const response = await fetch(`http://localhost:9090/store/${storeId.user_id}/product/${productId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product),
      });

      if (response.ok) {
        alert("Product updated successfully!");
        navigate(`/SingleShop`);
      } else {
        console.error("Error updating product");
      }
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setImage(file);
    }
  };

  const uploadImage = async () => {
    if (!image) return alert("Please select an image");

    const formData = new FormData();
    formData.append("imageFile", image);

    try {
      const response = await fetch(`http://localhost:9090/store/${storeId.user_id}/product/${productId}/image`, {
        method: "PUT",
        body: formData,
      });

      if (response.ok) {
        alert("Image uploaded successfully!");
        setModalOpen(false);
      } else {
        console.error("Error uploading image");
      }
    } catch (error) {
      console.error("Error uploading image:", error);
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <Box sx={{ maxWidth: 500, mx: "auto", mt: 5 }}>
      <Typography variant="h5" gutterBottom>Edit Product</Typography>
      <form onSubmit={handleSubmit}>
        <TextField fullWidth label="Product Name" name="productName" value={product.productName || ""} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Description" name="description" value={product.description || ""} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Price" name="price" type="number" value={product.price || 0} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Unit" name="unit" value={product.unit || ""} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Available Units" name="availableUnit" type="number" value={product.availableUnit || 0} onChange={handleChange} margin="normal" />

        {/* Metadata Section */}
        <Typography variant="h6" sx={{ mt: 3 }}>Metadata</Typography>
        {Object.entries(product.metadata || {}).map(([key, value], index) => (
          <Box key={index} sx={{ display: "flex", alignItems: "center", gap: 1, mb: 1 }}>
            <TextField label="Key" value={key} onChange={(e) => handleMetadataChange(e.target.value, value)} />
            <TextField label="Value" value={value} onChange={(e) => handleMetadataChange(key, e.target.value)} />
            <IconButton color="error" onClick={() => removeMetadataField(key)}>
              <Delete />
            </IconButton>
          </Box>
        ))}
        <Button startIcon={<Add />} onClick={addMetadataField} sx={{ mt: 1 }}>Add Metadata Field</Button>

        {/* Add Image Button */}
        <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 3 }} onClick={() => setModalOpen(true)}>
          Add Image
        </Button>

        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 3 }}>
          Update Product
        </Button>
      </form>

      {/* Image Upload Modal */}
      <Modal open={modalOpen} onClose={() => setModalOpen(false)}>
        <Box sx={{ position: "absolute", top: "50%", left: "50%", transform: "translate(-50%, -50%)", width: 400, bgcolor: "background.paper", boxShadow: 24, p: 4 }}>
          <Typography variant="h6" gutterBottom>Upload Product Image</Typography>
          <input type="file" accept="image/*" onChange={handleImageChange} />
          {image && <Typography variant="body2">Selected: {image.name}</Typography>}

          <Button startIcon={<Upload />} variant="contained" color="primary" fullWidth sx={{ mt: 2 }} onClick={uploadImage}>
            Upload
          </Button>
          <Button variant="outlined" color="error" fullWidth sx={{ mt: 2 }} onClick={() => setModalOpen(false)}>
            Cancel
          </Button>
        </Box>
      </Modal>
    </Box>
  );
};

export default EditProduct;
