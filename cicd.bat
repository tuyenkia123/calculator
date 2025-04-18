@echo off
echo 🧹 Stopping old container...
docker stop calculator
docker rm calculator

echo 🔨 Building image...
docker-compose build

echo 🚀 Running container...
docker-compose up -d

echo ✅ App is running at http://localhost:8080