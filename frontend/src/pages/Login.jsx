import { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()

  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      const res = await axios.post('http://localhost:8080/api/v1/auth/login', {
        email,
        password
      })

      const { accessToken, refreshToken } = res.data
      localStorage.setItem('accessToken', accessToken)
      localStorage.setItem('refreshToken', refreshToken)
    // WebSocket 연결
      const socket = new WebSocket(`ws://localhost:8080/ws/chat?token=${accessToken}`)
      socket.onopen = () => {
        console.log("WebSocket 연결 성공")
      }
      socket.onmessage = (event) => {
        console.log("수신된 메시지:", event.data)
      }



      alert('로그인 성공!')
      navigate('/chat')
    } catch (err) {
      alert('로그인 실패: ' + (err.response?.data?.message || err.message))
    }
  }

  return (
    <div style={{ padding: '2rem' }}>
      <h2>로그인</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={e => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  )
}

export default Login