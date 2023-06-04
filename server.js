const express = require('express');
const app = express();
const cors = require('cors');
const { authenticate } = require('./middleware/authMiddleware');

app.get('/api/public', (req, res) => {
    // Perform actions for the public route
    const message = `Connect Successfully`;
    res.json({ message });
});
app.use('/api/private', authenticate); //implemnt middleware

app.get('/api/private/data', authenticate, (req, res) => {
    // Access the authenticated user's ID using req.userId
    const userId = req.userId;
    // Perform actions for the private route
    const message = `Private data accessed for user: ${userId}`;
    res.json({ message });
});

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
const ScoreRoutes = require('./routes/routeScore');

// Parse JSON request bodies
app.use(express.json());

// Use routes
app.use('/api/private/Student', StudentsRoutes);
app.use('/api/private/Chat', ChatRoutes);
app.use('/api/private/Notification', NotificationRoutes);
app.use('/api/private/ml', mlRoutes);

app.get("/", (req, res) => {
    console.log("Response success")
    res.send("Response Success!")
})

const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log('Hello world listening on port', port);
});
