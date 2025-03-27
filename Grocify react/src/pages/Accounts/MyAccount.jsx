import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  MDBCol,
  MDBContainer,
  MDBRow,
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardImage,
  MDBBtn,
  MDBBreadcrumb,
  MDBBreadcrumbItem,
  MDBIcon,
  MDBListGroup,
  MDBListGroupItem
} from 'mdb-react-ui-kit';

const MyAccount = () =>{
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);

  useEffect(() => {
    // Retrieve user and token from sessionStorage
    const storedUser = sessionStorage.getItem('user');
    const storedToken = sessionStorage.getItem('token');
    console.log(storedUser);
  
    if (storedUser && storedToken) {
      try {
        setUser(JSON.parse(storedUser));  // Ensure JSON parsing is only attempted if valid
        setToken(storedToken);
      } catch (error) {
        console.error("Error parsing user data:", error);
        sessionStorage.removeItem('user');  // Clear corrupted data
        sessionStorage.removeItem('token');
        navigate('/MyAccountSignIn');  // Redirect to login
      }
    } else {
      navigate('/MyAccountSignIn');  // Redirect if no user data found
    }
  }, [navigate]);
  

  if (!user) {
    navigate('/MyAccountSignIn');
    return <p>Loading user data...</p>;
  }

  return (
    <section style={{ backgroundColor: '#eee' }}>
      <MDBContainer className="py-5">
        <MDBRow>
          <MDBCol>
            <MDBBreadcrumb className="bg-light rounded-3 p-3 mb-4">
              <MDBBreadcrumbItem>
                <a href='/login'>Login</a>
              </MDBBreadcrumbItem>
              <MDBBreadcrumbItem>
                <a href="/signup">Sign Up</a>
              </MDBBreadcrumbItem>
              <MDBBreadcrumbItem active>User Profile</MDBBreadcrumbItem>
            </MDBBreadcrumb>
          </MDBCol>
        </MDBRow>

        <MDBRow>
          <MDBCol lg="4">
            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <MDBCardImage
                  src={user.profilePic || "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"}
                  alt="avatar"
                  className="rounded-circle"
                  style={{ width: '150px' }}
                  fluid
                />
                <h1>Welcome, {user.firstName} {user.lastName}!</h1>
                <p className="text-muted mb-1">Hi {user.firstName}</p>
                {/* <p className="text-muted mb-4">{user.address || "No Address Provided"}</p> */}
                <div className="d-flex justify-content-center mb-2">
                  <MDBBtn>Follow</MDBBtn>
                  <MDBBtn outline className="ms-1">Message</MDBBtn>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4 mb-lg-0">
              <MDBCardBody className="p-0">
                <MDBListGroup flush className="rounded-3">
                  <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                    <MDBIcon fas icon="envelope fa-lg text-warning" />
                    <MDBCardText>{user.emailId}</MDBCardText>
                  </MDBListGroupItem>
                  <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                    <MDBIcon fas icon="phone fa-lg text-primary" />
                    <MDBCardText>{user.mobileNo}</MDBCardText>
                  </MDBListGroupItem>
                  <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                    <MDBIcon fas icon="map-marker-alt fa-lg text-danger" />
                    <MDBCardText>{user.address}</MDBCardText>
                  </MDBListGroupItem>
                </MDBListGroup>
              </MDBCardBody>
            </MDBCard>
          </MDBCol>

          <MDBCol lg="8">
            <MDBCard className="mb-4">
              <MDBCardBody>
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Full Name</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText className="text-muted">{user.firstName} {user.lastName}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Email</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText className="text-muted">{user.emailId}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Phone</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText className="text-muted">{user.mobileNo}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Address</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText className="text-muted">{user.address}</MDBCardText>
                  </MDBCol>
                </MDBRow>
              </MDBCardBody>
            </MDBCard>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </section>
  );
}

export default MyAccount;
