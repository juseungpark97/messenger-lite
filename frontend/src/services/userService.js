// services/userService.js
import axiosInterceptor from '../lib/axiosInterceptor';

export async function fetchAllUsers() {
  const res = await axiosInterceptor.get('/api/v1/users');
  return res.data;
}