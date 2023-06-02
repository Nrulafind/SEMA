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
        // Extract the data from the request body
        const { token, title, body } = req.body;

        // Save the notification data to Firestore
        const notificationData = {
            token,
            title,
            body,
            timestamp: new Date(),
        };

        const notificationRef = await firestore.collection('notifications').add(notificationData);

        //send the notification
        const notificationService = new NotificationService();
        notificationService.sendNotification(token, title, body);

        // Return a success message 
        res.json({ message: 'Notification sent successfully', notificationId: notificationRef.id });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

module.exports = {
    sendNotification,
};
