# frontend/Dockerfile
FROM node:18 AS build
WORKDIR /app
COPY . .
RUN npm ci || npm i
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
# Optional: single-page-app fallback
RUN printf "try_files \$uri /index.html;\n" > /etc/nginx/conf.d/default.conf
EXPOSE 80
