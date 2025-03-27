import * as React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import AspectRatio from '@mui/joy/AspectRatio';
import Button from '@mui/joy/Button';
import Card from '@mui/joy/Card';
import CardContent from '@mui/joy/CardContent';
import CardOverflow from '@mui/joy/CardOverflow';
import Chip from '@mui/joy/Chip';
import Link from '@mui/joy/Link';
import Typography from '@mui/joy/Typography';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

export default function Home({ product }) {
  const navigate = useNavigate(); // Initialize navigate function

  if (!product) return <Typography>Product not found</Typography>;

  // Construct image URL
  const imageUrl = product.imageURL
    ? `http://localhost:9090/store/1/product/${product.id}/image`
    : "https://via.placeholder.com/150";

  // Function to handle navigation
  const handleNavigation = () => {
    navigate(`/productdetail/${product.id}`);
  };
  const addToCart = () => {
    if (product) {
      let cart = JSON.parse(localStorage.getItem("cart")) || []; // Get existing cart or empty array

      // Check if product already exists in the cart
      const existingProduct = cart.find((item) => item.id === product.id);

      if (existingProduct) {
        alert(`${product.productName} is already in the cart!`);
      } else {
        cart.push(product);
        localStorage.setItem("cart", JSON.stringify(cart)); // Save updated cart
        alert(`${product.productName} added to cart!`);
      }
    }
  };

  return (
    <div className="col-md-4 mb-4" >
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

  {/* Add to Cart Button */}
  <CardOverflow>
    <Button variant="solid" color="danger" size="lg" onClick={() => addToCart(product)}>
      Add to Cart
    </Button>
  </CardOverflow>
</Card>
</div>

  );
}
