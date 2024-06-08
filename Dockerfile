# Use the official image as a parent image
# Use the official image as a parent image
FROM mysql:latest

# Set the working directory in the container
WORKDIR /docker-entrypoint-initdb.d

# Add the current directory contents into the container at /docker-entrypoint-initdb.d
ADD . /docker-entrypoint-initdb.d

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=agroges-os
ENV MYSQL_PASSWORD=password

# Expose port 3306
EXPOSE 3306