import axios from 'axios';

export { solAxios };

let solAxios = createAdminInstance();
function createAdminInstance() {
  /* Create axios instance */
  const solAxios = axios.create();
  solAxios.defaults.baseUrl = "http://localhost:8080"
  solAxios.defaults.withCredentials = true;
  solAxios.defaults.crossDomain = true;
  return solAxios;
}
