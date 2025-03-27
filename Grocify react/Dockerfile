# Step 1: Build React App
FROM node:18-alpine AS build
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy source code and build the project
COPY . .
RUN npm run build

# Step 2: Serve with Nginx
FROM nginx:stable-alpine
WORKDIR /usr/share/nginx/html

# Remove default Nginx static files
RUN rm -rf ./*

# Copy built React files from previous step
COPY --from=build /app/build .

# Copy custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

