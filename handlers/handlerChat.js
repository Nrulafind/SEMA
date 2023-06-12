// handlerChat.js
const Firestore = require('@google-cloud/firestore');
const serviceAccount = require('../service-account/service-account-key.json');

// Initialize Firestore with service account credentials
const firestore = new Firestore({
    projectId: serviceAccount.project_id,
    credentials: {
        client_email: serviceAccount.client_email,
        private_key: serviceAccount.private_key,
    },
});

const socketIO = require('socket.io');

// Import the Express app object
const app = require('../server');

// Create a Socket.io server
const io = socketIO(app);

// Handle incoming Socket.io connections
io.on('connection', (socket) => {
    console.log('New Socket.io connection');

    // Handle incoming messages from the Socket.io client
    socket.on('sendMessage', async (data) => {
        try {
            const { sender, receiver, message } = data;

            // Send the message and record it in Firestore
            const messageRef = await firestore.collection('room/chat').add({
                sender,
                receiver,
                message,
                timestamp: new Date().toISOString(),
            });

            // Emit the received message to all connected Socket.io clients
            io.emit('messageReceived', {
                id: messageRef.id,
                sender,
                receiver,
                message,
            });

        } catch (error) {
            console.error(error);
        }
    });
});

// Define the sendMessage function and export it
const sendMessage = (req, res) => {
    // Access the authenticated user's ID using req.userId
    const userId = req.userId;
    // Handle sending the message
    res.send('Message sent successfully');
};

module.exports = {
    io, // Export the io 
    sendMessage,
};
