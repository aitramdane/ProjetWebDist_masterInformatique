FROM nginx:1.17.1-alpine
COPY /dist/hotel-ui  /usr/share/nginx/html
