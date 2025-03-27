import React, { useEffect, useState} from 'react';
import { MDBContainer, MDBRow } from 'mdb-react-ui-kit';
import { getAllProducts } from '../service/getAllProducts'; // Adjust the import path as needed
import Home from './Home';

function ProductGrid() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        console.log("this is home");
        const data = await getAllProducts();
        console.log(data);
        setProducts(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  if (loading) {
    return <div>Loading products...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <MDBContainer>
      <MDBRow>
        {products.map((product, index) => (
          <Home key={index} product={product} />
        ))}
      </MDBRow>
    </MDBContainer>
  );
}

export default ProductGrid;