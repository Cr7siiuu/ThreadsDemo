# Étape 1 : Build Angular
FROM node:20 AS build
WORKDIR /app

# Copier les fichiers nécessaires
COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build -- --configuration=production

# Étape 2 : Serveur Nginx pour les fichiers statiques
FROM nginx:1.27-alpine

# Copier le build Angular dans le dossier HTML de Nginx
COPY --from=build /app/dist/multiThreadApp/browser /usr/share/nginx/html

# Copier un fichier nginx.conf custom si besoin
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
