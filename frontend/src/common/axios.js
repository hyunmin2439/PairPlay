import axios from "axios";

const BASE_URL = "https://k6e205.p.ssafy.io:8088/api/v1/";
const DEFAULT_ACCEPT_TYPE = "application/json";

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers["Content-Type"] = DEFAULT_ACCEPT_TYPE;

export default { axios };
