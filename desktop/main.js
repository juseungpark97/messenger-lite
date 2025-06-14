const { app, BrowserWindow, ipcMain } = require('electron')
const path = require('path')

function createWindow() {
  const win = new BrowserWindow({
    width: 400,
    height: 700,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
  })

  // 개발 서버용 (Vite)
  win.loadURL('http://localhost:5173')

  // 배포할 때 (vite build 이후)
  // win.loadFile(path.join(__dirname, '../frontend/dist/index.html'))
}

app.whenReady().then(createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit()
})

ipcMain.on('open-user-profile', (event, user) => {
  const profileWindow = new BrowserWindow({
    width: 400,
    height: 500,
    title: `${user.name}의 프로필`,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
  });

  profileWindow.loadURL(`file://${__dirname}/profile.html?userId=${user.id}`);
});