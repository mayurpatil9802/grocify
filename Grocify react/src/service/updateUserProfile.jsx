export const updateUserProfile = async (userId, userProfileUpdateRequest) => {
  const token = sessionStorage.getItem('token'); // Retrieve token from local storage  

  console.log(token); 
  const response = await fetch(`http://localhost:8080/users/${userId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(userProfileUpdateRequest),
  });

  if (!response.ok) {
    throw new Error('Failed to update profile');
  }

  return response.json();
};
