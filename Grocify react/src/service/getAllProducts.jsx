export const getAllProducts = async () => {  
  try {  
    const token = sessionStorage.getItem('token'); // Retrieve token from local storage  

    console.log(token); 
    const response = await fetch('http://localhost:9090/store/1/product', {  
      method: 'GET',  
      headers: {  
        'Content-Type': 'application/json',  
        'Authorization': `Bearer ${token}`, // Include the JWT token  
        
      },  
    });  
    console.log(response); 

    if (!response.ok) {  
      throw new Error('Failed to fetch products');  
    }  

    return response.json(); // Parse and return the JSON data  
  } catch (error) {  
    console.error('Error fetching products:', error);  
    throw error;  
  }  
};  

// Usage
getAllProducts()
  .then(products => console.log(products))
  .catch(error => console.error(error));
