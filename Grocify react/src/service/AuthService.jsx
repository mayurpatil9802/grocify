import axios from "axios";

const baseUrl = "http://localhost:8080/auth/";

class AuthService {
    signUp(user) {
        return axios.post(baseUrl + "signup", user, { 
            headers: { "Content-Type": "application/json" } 
        });
    }
    login(user) {
        return axios.post(baseUrl + "login", user, { 
            headers: { "Content-Type": "application/json" } 
        });
    }
}







export default new AuthService();