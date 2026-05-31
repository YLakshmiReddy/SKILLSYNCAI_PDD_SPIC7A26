@echo off
echo Starting SkillSyncAI Web Application...
cd webapp
start cmd /c "npm run dev"

echo Waiting for the Next.js server to start...
timeout /t 5 /nobreak > NUL

echo Setting up USB port forwarding (adb reverse)...
adb reverse tcp:3000 tcp:3000

echo Opening the web application on your connected mobile device...
adb shell am start -a android.intent.action.VIEW -d "http://localhost:3000"

echo Done! The app should now be open on your mobile browser.
