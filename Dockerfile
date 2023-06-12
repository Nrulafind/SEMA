# Use an official Python runtime as the base image
FROM python:3.10.4

# Set the working directory in the container
WORKDIR /app

#PORT
ENV PORT 8080

#Upgrade pip
RUN pip install --upgrade pip

# Copy the requirements.txt file to the container
COPY requirements.txt .

# Install project dependencies
RUN pip install -r requirements.txt

# Copy the Flask application code to the container
COPY . .

# Expose the port that the Flask application listens on
EXPOSE 8080

# Set the command to run the Flask application
CMD ["python3", "app.py" ]
