// src/components/UserProfileModal.jsx
export default function UserProfileModal({ user, onClose }) {
    return (
      <div className="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center z-50">
        <div className="bg-white p-6 rounded-lg shadow-lg">
          <img src={user.profileImage} className="w-24 h-24 rounded-full mx-auto mb-4" />
          <div className="text-center">
            <h2 className="text-xl font-semibold">{user.name}</h2>
            <p className="text-sm text-gray-600">{user.statusMessage}</p>
          </div>
          <button onClick={onClose} className="mt-4 w-full bg-purple-200 py-2 rounded">닫기</button>
        </div>
      </div>
    );
  }