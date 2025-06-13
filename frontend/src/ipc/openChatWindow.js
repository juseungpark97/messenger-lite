// src/ipc/openChatWindow.js
import useElectron from '../hooks/useElectron';

export default function openChatWindow(roomId) {
  const { ipcRenderer } = useElectron();
  if (ipcRenderer) {
    ipcRenderer.send('open-chat-window', roomId);
  } else {
    alert('Electron 환경이 아님!');
  }
}