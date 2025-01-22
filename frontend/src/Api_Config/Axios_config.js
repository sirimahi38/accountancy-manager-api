import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8666/accountant/",
});