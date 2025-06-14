import { useState } from 'react';
import { useUsers } from '../hooks/useUsers';

export default function UserList({ onUserDoubleClick }) {
  const [selectedUser, setSelectedUser] = useState(null);
  const { users, error } = useUsers();

  const openProfile = (e, user) => {
    e.stopPropagation();
    setSelectedUser(user);
  };

  const closeProfile = () => setSelectedUser(null);

  if (error) {
    return <div className="p-4 text-red-500">유저 정보를 불러오는 데 실패했습니다.</div>;
  }

  return (
    <div className="w-96 bg-white overflow-y-auto border-r border-gray-200 relative">
      <div className="p-4 border-b font-bold text-lg">유저 목록</div>
      {users.map(user => (
        <div
          key={user.id}
          onDoubleClick={() => onUserDoubleClick(user)}
          className="flex items-center px-4 py-3 hover:bg-gray-100 transition-colors cursor-default select-none"
        >
          <img
            src={user.profileImageUrl}
            alt="프사"
            className="w-10 h-10 rounded-full mr-3 hover:scale-105 transition"
            onClick={(e) => openProfile(e, user)}
          />
          <div className="flex-1">
            <div className="font-semibold">{user.name}</div>
            <div className="text-sm text-gray-500 truncate">{user.statusMessage}</div>
          </div>
        </div>
      ))}

      {selectedUser && (
        <div className="absolute top-20 left-1/2 -translate-x-1/2 w-80 bg-white border shadow-xl p-4 rounded z-50">
          <div className="flex justify-between items-center mb-2">
            <h2 className="font-bold text-lg">프로필 정보</h2>
            <button onClick={closeProfile}>✖️</button>
          </div>
          <div className="flex flex-col items-center">
            <img src={selectedUser.profileImageUrl} alt="프사" className="w-24 h-24 rounded-full mb-2" />
            <div className="font-semibold text-xl">{selectedUser.name}</div>
            <div className="text-sm text-gray-500 mt-1">{selectedUser.statusMessage}</div>
          </div>
        </div>
      )}
    </div>
  );
}