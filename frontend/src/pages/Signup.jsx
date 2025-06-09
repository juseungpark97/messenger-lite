// pages/Signup.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSignup } from '../hooks/useSignup';

function Signup() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const { handleSignup, loading, error } = useSignup();
  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await handleSignup(name, email, password);
      alert('회원가입 성공! 로그인 화면으로 이동합니다.');
      navigate('/login');
    } catch (err) {
      alert('회원가입 실패: ' + (err.response?.data?.message || err.message));
    }
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
          type="text"
          placeholder="이름"
          value={name}
          onChange={e => setName(e.target.value)}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none"
        />
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
          {loading ? '가입 중...' : '가입하기'}
        </button>
      </form>

      <div className="mt-4 text-sm text-gray-700">
        <div className="mt-4 flex justify-center gap-2 text-xs">
          <button onClick={() => navigate('/login')} className="underline">이미 계정이 있으신가요? 로그인</button>
        </div>
      </div>
    </div>
  );
}

export default Signup;