import { useState } from 'react';
import axios from 'axios';

function Signup() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/v1/auth/signup', {
        email,
        password,
        name,
      });
      alert('회원가입 성공!');
      console.log(res.data);
    } catch (err) {
      alert('회원가입 실패: ' + (err.response?.data?.message || err.message));
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={e => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
        </div>
        <div>
          <label>Name:</label>
          <input type="text" value={name} onChange={e => setName(e.target.value)} required />
        </div>
        <button type="submit">가입하기</button>
      </form>
    </div>
  );
}

export default Signup;