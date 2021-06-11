import axios from 'axios';


let solAxios = createSolInstance();
function createSolInstance() {
  /* Create axios instance */
  const solAxios = axios.create();
  // solAxios.defaults.baseURL = "http://localhost:8080"
  solAxios.defaults.baseURL = "http://hansolo.iptime.org:8080"
  solAxios.defaults.withCredentials = true;
  solAxios.defaults.crossDomain = true;
  return solAxios;
}

let smartThingsAxios = createSmartThingsInstance();

function createSmartThingsInstance() {
  /* Create axios instance */
  const smartThingsAxios = axios.create();
  smartThingsAxios.defaults.baseURL = "https://api.smartthings.com"
  smartThingsAxios.defaults.withCredentials = true;
  smartThingsAxios.defaults.crossDomain = true;
  return smartThingsAxios;
}

export { solAxios, smartThingsAxios };
