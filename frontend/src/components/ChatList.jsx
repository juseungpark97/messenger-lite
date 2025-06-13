// src/components/ChatList.jsx
import ChatItem from './ChatItem';

const dummyChats = [
  { id: 1, name: 'user1', message: 'test', time: '오후 7:25', unread: 1, pinned: true },
  { id: 2, name: 'user2', message: 'ㅋㅋㅋ', time: '오후 7:08', unread: 0 },
  { id: 3, name: 'user3', message: 'ㅇㅇ', time: '오후 6:42', unread: 0 },
  { id: 4, name: 'user4', message: '이모티콘을 보냈습니다.', time: '어제', unread: 0 },
];

export default function ChatList() {
  return (
    <div className="w-96 bg-white overflow-y-auto border-r border-gray-200">
      <div className="p-4 border-b font-bold text-lg">채팅</div>
      {dummyChats.map(chat => (
        <ChatItem key={chat.id} chat={chat} />
      ))}
    </div>
  );
}