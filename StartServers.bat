@echo off
title SkillSync AI - Servers
color 0A

echo ==========================================
echo   SkillSync AI - Starting All Servers
echo ==========================================
echo.

echo [1/2] Starting Backend (FastAPI on port 8000)...
start "SkillSync Backend" cmd /k "cd /d %~dp0backend && .venv\Scripts\activate && uvicorn main:app --reload --port 8000 --host 0.0.0.0"

timeout /t 3 /nobreak >nul

echo [2/2] Starting Frontend (Next.js on port 3000)...
start "SkillSync Frontend" cmd /k "cd /d %~dp0webapp && npm run dev"

echo.
echo ==========================================
echo   Both servers are starting!
echo   Backend:  http://localhost:8000
echo   Frontend: http://localhost:3000
echo ==========================================
echo.
pause
