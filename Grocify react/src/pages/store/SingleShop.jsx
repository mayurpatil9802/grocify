import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import { Link } from "react-router-dom";
import { Card, CardOverflow, CardContent, Button, Typography, AspectRatio } from "@mui/joy";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import ScrollToTop from "../ScrollToTop";

const SingleShop = () => {
  const navigate = useNavigate();
  const [loaderStatus, setLoaderStatus] = useState(true);
  const [products, setProducts] = useState([]);
  
  // Get user details from sessionStorage
  const user = JSON.parse(sessionStorage.getItem("user"));
  const userRole = user?.role;
  const storeId = user?.user_id; // Assuming store ID is stored in sessionStorage
  const handleNavigation = () => {
    navigate(`/productdetail/${user.id}`);
  };

  // Redirect if not a store owner
  useEffect(() => {
    if (userRole !== "STORE_OWNER") {
      navigate("/unauthorized");
    }
  }, [userRole, navigate]);

  // Fetch products when component loads
  useEffect(() => {
    setTimeout(() => {
      setLoaderStatus(false);
      fetchProducts();
    }, 1500);
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await fetch(`http://localhost:9090/store/${user.user_id}/product/abc`);
      const data = await response.json();
      console.log("Fetched Products:", data);

      if (Array.isArray(data)) {
        setProducts(data);
      } else {
        setProducts([]); // Ensure products is always an array
      }
    } catch (error) {
      console.error("Error fetching products:", error);
      setProducts([]); // Handle error case
    }
  };

  const handleEdit = (productId) => {
    console.log("Edit Product:", productId);
    navigate(`/edit-product/${productId}`); // Navigate to edit page
  };

  const handleDelete = async (productId) => {
    console.log(productId);
    const confirmDelete = window.confirm("Are you sure you want to delete this product?");
    if (!confirmDelete) return;

    try {
      const response = await fetch(`http://localhost:9090/store/${user.user_id}/product/${productId}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("Product deleted successfully");
        setProducts(products.filter((product) => product.id !== productId));
      } else {
        alert("Failed to delete product");
      }
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div>
      {loaderStatus ? (
        <div className="loader-container">
          <p>Loading...</p>
        </div>
      ) : (
        <>
          <ScrollToTop />
          <section className="mb-lg-14 mb-8 mt-8">
            <div className="container">
              <div className="row">
                {/* Sidebar */}
                <div className="col-12 col-lg-3 col-md-4 mb-4 mb-md-0">
                  
                  <hr />
                  <ul className="nav flex-column nav-links">
                    <li className="nav-item"><Link to="#" className="nav-link">Produce</Link></li>
                    <li className="nav-item"><Link to="#" className="nav-link">Dairy &amp; Eggs</Link></li>
                    <li className="nav-item"><Link to="#" className="nav-link">Beverages</Link></li>
                  </ul>
                </div>

                {/* Products Grid */}
                <div className="col-12 col-lg-9 col-md-8">
                  <div className="row">
                    {products.length === 0 ? (
                      <p>No products available for this store.</p>
                    ) : (
                      products.map((product) => {
                        const imageUrl = product.imageURL
                          ? `http://localhost:9090/store/${storeId}/product/${product.id}/image`
                          : "https://via.placeholder.com/150";

                        return (
                          <div className="col-md-4 mb-4" key={product.id}>
                            <Card sx={{ width: 320, maxWidth: "100%", boxShadow: "lg", position: "relative" }}>
  {/* Product ID in Corner */}
  <Typography
    sx={{
      position: "absolute",
      top: 8,
      right: 8,
      backgroundColor: "#ff6b6b",
      color: "white",
      fontSize: "12px",
      padding: "4px 8px",
      borderRadius: "4px",
    }}
  >
    ID: {product.id}
  </Typography>

  {/* Product Image */}
  <CardOverflow>
    <AspectRatio sx={{ minWidth: 200 }}>
      <img src={imageUrl} alt={product.name} loading="lazy" />
    </AspectRatio>
  </CardOverflow>

  <CardContent>
    {/* Product Category */}
    <Typography level="body-xs">{product.id || "No Category"}</Typography>

    {/* Product Name with Navigation */}
    <Link
      component="button"
      onClick={handleNavigation}
      color="neutral"
      textColor="text.primary"
      overlay
      endDecorator={<ArrowOutwardIcon />}
      sx={{ fontWeight: "md" }}
    >
      {product.name}
    </Link>

    {/* Product Description */}
    <Typography level="body-sm" sx={{ mt: 1 }}>
      {product.description || "No description available"}
    </Typography>

    {/* Product Price */}
    <Typography level="title-lg" sx={{ mt: 1, fontWeight: "xl" }}>
      {product.price} Rs
    </Typography>

    {/* Available Units */}
    <Typography level="body-sm" sx={{ mt: 1, color: "green" }}>
      Available: <b>{product.availableUnit || 0}</b> in stock
    </Typography>
  </CardContent>
                              <CardOverflow sx={{ display: "flex", justifyContent: "space-between", p: 2 }}>
                                <Button variant="solid" color="primary" size="sm" onClick={() => handleEdit(product.id)}>
                                  Edit
                                </Button>
                                <Button variant="solid" color="danger" size="sm" onClick={() => handleDelete(product.id)}>
                                  Delete
                                </Button>
                              </CardOverflow>
                            </Card>
                          </div>
                        );
                      })
                    )}
                  </div>
                </div>
              </div>
            </div>
          </section>
        </>
      )}
    </div>
  );
};

export default SingleShop;
