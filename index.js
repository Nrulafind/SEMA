const express = require('express');
const app = express();
const port = 3000;

// Import required dependencies
const tf = require('@tensorflow/tfjs-node');
const fs = require('fs');

// Load the TensorFlow Lite model
const modelFile = './models/model.tflite';
const model = fs.readFileSync(modelFile);
const tfliteModel = new Uint8Array(model);

// Initialize the TensorFlow Lite interpreter
const interpreter = new tf.Interpreter(tfliteModel);

// Import the io object from handlerChat.js
const { io } = require('./handlers/handlerChat');

// Set the io object in the app for access in routes for the chat 
app.set('socketio', io);

// Import routes
const StudentsRoutes = require('./routes/routeStudents');
const AuthRoutes = require('./routes/routeAuth');
const ChatRoutes = require('./routes/routeChat');
const NotificationRoutes = require('./routes/routeNotification');
const mlRoutes = require('./routes/routeMl');

// Parse JSON request bodies
app.use(express.json());

// Use routes
app.use('/Student', StudentsRoutes);
app.use('/Auth', AuthRoutes);
app.use('/Chat', ChatRoutes);
app.use('/Notification', NotificationRoutes);
app.use('/ml', mlRoutes);

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
