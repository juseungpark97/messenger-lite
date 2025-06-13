// pages/Login.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useLogin } from '../hooks/useLogin';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { handleLogin, loading, error } = useLogin();
  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await handleLogin(email, password);
      // WebSocket 연결
      const socket = new WebSocket(`ws://localhost:8080/ws/chat?token=${data.accessToken}`);
      socket.onopen = () => {
        console.log("WebSocket 연결 성공");
      };
      socket.onmessage = (event) => {
        console.log("수신된 메시지:", event.data);
      };
      alert('로그인 성공!');
      navigate('/main');
    } catch (err) {
      alert('로그인 실패: ' + (err.response?.data?.message || err.message));
    }
  };

  const goToSignup = () => {
    navigate('/signup');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-purple-200">
      <div className="mb-8">
        <div className="w-24 h-24 bg-black rounded-full flex items-center justify-center">
          <span className="text-purple-200 text-2xl font-bold">LINK</span>
        </div>
      </div>

      <form onSubmit={onSubmit} className="bg-white p-6 rounded shadow-md w-80 flex flex-col gap-4">
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={e => setEmail(e.target.value)}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none"
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none"
        />
        <button
          type="submit"
          className="bg-purple-200 text-black py-2 rounded font-bold hover:bg-purple-100"
          disabled={loading}
        >
          {loading ? '로그인 중...' : '로그인'}
        </button>
      </form>

      <div className="mt-4 text-sm text-gray-700">
        <button className="mt-2 bg-white p-2 rounded shadow-md w-80 flex justify-center items-center">
          QR 코드 로그인
        </button>
        <div className="mt-4 flex justify-center gap-2 text-xs">
          <button onClick={goToSignup} className="underline">회원가입</button>
          <span>|</span>
          <a href="#" className="underline">비밀번호 재설정</a>
        </div>
      </div>
    </div>
  );
}

export default Login;