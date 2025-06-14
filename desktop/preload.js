const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
  openUserProfile: (user) => ipcRenderer.send('open-user-profile', user),
  
});