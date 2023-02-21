# choose base image to build off of
FROM node:latest as node

# set the current working directory for all commands
WORKDIR /app

# copy these over first and run 'npm install' so the node_modules will be cached
# until the package.json / lock changes
COPY package.json .
COPY package-lock.json .
RUN npm install

# copy over all code files
COPY . .
#RUN npn run build --prod


# expose internal docker container port to external environment
EXPOSE 4200

# specify default command to run when we run the image
CMD /app/node_modules/.bin/ng serve --host 0.0.0.0 --disable-host-check

#FROM nginx:alpine
#COPY --from=node /app/dist/ui /usr/share/nginx/html
