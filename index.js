const express = require('express');
const app = express();
const port = 3000;
const cors = require('cors');
const authenticate = require('./middleware/authMiddleware');

app.use('/api/private', authenticate); //implemnt middleware

app.get('/api/private/data', (req, res) => {
    // Access the authenticated user's ID using req.userId
    const userId = req.userId;
    // Perform actions for the private route
    const message = `Private data accessed for user: ${userId}`;
    res.json({ message });
});

// Import required dependencies
const tf = require('@tensorflow/tfjs-node');
const fs = require('fs');

// Load the TensorFlow Lite model
const modelFile = './models/model.tflite';
const model = fs.readFileSync(modelFile);
const tfliteModel = new Uint8Array(model);

// Initialize the TensorFlow Lite interpreter
const interpreter = new tf.Interpreter(tfliteModel);


//add cors for the cross origin policy
app.use(cors());

// Import the io object from handlerChat.js
const { io } = require('./handlers/handlerChat');

// Set the io object in the app for access in routes for the chat 
app.set('socketio', io);

// Import routes
const StudentsRoutes = require('./routes/routeStudents');
const ChatRoutes = require('./routes/routeChat');
const NotificationRoutes = require('./routes/routeNotification');
const mlRoutes = require('./routes/routeMl');

// Parse JSON request bodies
app.use(express.json());

// Use routes
app.use('/api/private/Student', StudentsRoutes);
app.use('/api/private/Chat', ChatRoutes);
app.use('/api/private/Notification', NotificationRoutes);
app.use('/api/private/ml', mlRoutes);

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
