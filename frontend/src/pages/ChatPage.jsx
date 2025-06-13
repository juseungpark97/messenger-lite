// pages/ChatPage.jsx
import { useParams } from 'react-router-dom';

function ChatPage() {
  const { roomId } = useParams();

  return (
    <div className="p-6">
      <h1 className="text-xl font-bold">채팅방 #{roomId}</h1>
      {/* 채팅 메시지 + 입력창 구성 */}
    </div>
  );
}

export default ChatPage;