// src/pages/Main.jsx
import Sidebar from '../components/SideMenu';
import ChatList from '../components/ChatList';

export default function Main() {
  return (
    <div className="flex h-screen font-sans">
      <Sidebar />
      <ChatList />
    </div>
  );
}