// src/services/authService.js
export const login = async (loginRequest) => {
    const response = await fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginRequest),
    });
  
    if (!response.ok) {
      throw new Error('Login failed');
    }
  
    return response.json();
  };