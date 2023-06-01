// handlerNotification.js

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


async function sendNotification(req, res) {
    try {
        // Extract the necessary data from the request body
        const { token, title, body } = req.body;

        // Implement logic to send the notification
        // For example, you can use a notification service or FCM

        // Return a success message or any relevant response
        res.json({ message: 'Notification sent successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

module.exports = {
    sendNotification,
};
