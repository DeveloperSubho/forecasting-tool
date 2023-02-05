let backendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost") {
  backendHost = "http://" + hostname + ":8081";
} else {
  backendHost = "https://" + hostname;
}

export const API_ROOT = `${backendHost}`;
