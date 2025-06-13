// src/hooks/useElectron.js
export default function useElectron() {
    const isElectron = !!window.electron?.ipcRenderer;
    const ipcRenderer = window.electron?.ipcRenderer;
    return { isElectron, ipcRenderer };
  }