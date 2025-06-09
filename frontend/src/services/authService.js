import axios from 'axios';

export async function login(email, password) {
  const res = await axios.post('http://localhost:8080/api/v1/auth/login', {
    email,
    password,
  });
  return res.data; // { accessToken, refreshToken }
}

export async function signup(name, email, password) {
  const res = await axios.post('http://localhost:8080/api/v1/auth/signup', {
    name,
    email,
    password,
  });
  return res.data;
}