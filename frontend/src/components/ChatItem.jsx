// src/components/ChatItem.jsx
import openChatWindow from '../ipc/openChatWindow';

export default function ChatItem({ chat }) {
  return (
    <div
      onDoubleClick={() => openChatWindow(chat.id)}
      className="flex items-center px-4 py-3 hover:bg-gray-100 cursor-pointer select-none"
    >
      <img
        src={`https://i.pravatar.cc/150?img=${chat.id}`} // 또는 DiceBear 아바타 API
        alt="랜덤 프로필"
        className="w-12 h-12 rounded-full mr-3 object-cover"
      />
      <div className="flex-1">
        <div className="flex justify-between items-center">
          <span className="font-semibold">{chat.name} {chat.pinned && '📌'}</span>
          <span className="text-xs text-gray-500">{chat.time}</span>
        </div>
        <div className="text-sm text-gray-500 truncate">{chat.message}</div>
      </div>
      {chat.unread > 0 && (
        <div className="ml-2 w-5 h-5 bg-red-500 text-white text-xs rounded-full flex items-center justify-center">
          {chat.unread}
        </div>
      )}
    </div>
  );
}