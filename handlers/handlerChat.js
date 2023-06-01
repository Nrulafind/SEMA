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


async function sendMessage(req, res) {
    try {
        // Extract the necessary data from the request body
        const { sender, receiver, message } = req.body;

        // Implement logic to send the message
        // For example, you can use a messaging service or emit events to sockets

        // Return a success message or any relevant response
        res.json({ message: 'Message sent successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

module.exports = {
    sendMessage,
};
