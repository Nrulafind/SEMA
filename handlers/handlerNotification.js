// handlerNotification.js

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
