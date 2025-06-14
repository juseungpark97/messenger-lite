import axios from 'axios';

const axiosInterceptor = axios.create({
  baseURL: 'http://localhost:8080',
});

axiosInterceptor.interceptors.request.use(config => {
  const token = localStorage.getItem('accessToken');
  if (
    token &&
    config.url !== '/api/v1/auth/login' &&
    config.url !== '/api/v1/auth/signup'
  ) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default axiosInterceptor;