import { useState } from 'react';
import Sidebar from '../components/SideMenu';
import ChatList from '../components/ChatList';
import UserList from '../components/UserList';
import UserProfileModal from '../components/UserProfileModal';

export default function Main() {
  const [tab, setTab] = useState('chat'); // 'chat' or 'user'
  const [selectedUser, setSelectedUser] = useState(null);

  return (
    <div className="flex h-screen font-sans">
      <Sidebar onTabChange={setTab} />
      {tab === 'chat' && <ChatList />}
      {tab === 'user' && <UserList onUserClick={setSelectedUser} />}
      {selectedUser && <UserProfileModal user={selectedUser} onClose={() => setSelectedUser(null)} />}
    </div>
  );
}