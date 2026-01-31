FROM coderun-modern-lms-backend

# ===== NORMAL BUILD & RUN =====
# docker compose down -v
# docker compose build --no-cache backend
# docker compose up -d
# docker compose up -d --no-deps backend
# docker build -t coderun-modern-lms-sandbox -f docker/Dockerfile.sandbox .

# ===== JIKA KONFLIK =====
# docker system prune -f
# docker compose down --remove-orphans
# docker rm -f coderun-modern-lms-mysql
# docker volume rm backend_coderun-modern-lms-mysql

# ===== MIGRATE & SEEDER DATABASE =====
# docker exec -it coderun-modern-lms-backend npm run migrate
# docker exec -it coderun-modern-lms-backend npm run seeder

# ===== MASUK MYSQL =====
# docker exec -it coderun-modern-lms-mysql mysql -u root -p
