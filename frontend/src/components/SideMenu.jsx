export default function Sidebar({ onTabChange }) {
  return (
    <div className="w-16 bg-gray-100 flex flex-col items-center py-4 gap-6 text-xl">
      <div className="w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center text-white text-xl font-bold">🙂</div>
      <div
        onClick={() => onTabChange('user')}
        className="w-10 h-10 bg-black rounded-full text-white flex items-center justify-center text-lg cursor-pointer"
      >
        👤
      </div>
      <div
        onClick={() => onTabChange('chat')}
        className="relative w-10 h-10 flex items-center justify-center hover:scale-105 transition cursor-pointer"
      >
        <div className="absolute -top-1 -right-1 w-5 h-5 bg-red-500 text-white text-xs rounded-full flex items-center justify-center">60</div>
        💬
      </div>
      <div className="w-10 h-10 text-2xl">⋯</div>
    </div>
  );
}