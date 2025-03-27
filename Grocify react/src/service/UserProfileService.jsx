import axios from "axios";

const baseUrl = "http://localhost:9090/users/";

class UserProfileService {
    updateProfile(user) {
        return axios.put(baseUrl + `${user.id}`, user, { 
            headers: { "Content-Type": "application/json" } 
        });
    }
    deleteProfile(id) {
        return axios.post(baseUrl + id, { 
            headers: { "Content-Type": "application/json" } 
        });
    }
}







export default new UserProfileService();