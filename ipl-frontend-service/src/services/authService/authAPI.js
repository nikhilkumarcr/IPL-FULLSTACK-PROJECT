import axios from "axios";
import Url from "../../components/ApiUrl";



const login = (username, password) => {
    return axios
      .post(Url.authUrl + "sign-in", {
        username,
        password,
      })
      .then((response) => {
        
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  };
  
  const logout = () => {
    localStorage.removeItem("user");
  };
  
  const authAPI = {
    login,
    logout,
  };
  
  export default authAPI;