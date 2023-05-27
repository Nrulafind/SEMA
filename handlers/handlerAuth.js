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

async function signUp(req, res) {
    try {
        const { email, password } = req.body;

        // Implement logic to create a new user document in your Firestore collection
        // For example:
        const userRef = firestore.collection('users').doc();
        await userRef.set({ email, password });

        // Return the newly created user's document ID or any relevant response
        res.json({ userId: userRef.id });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function signIn(req, res) {
    try {
        const { email, password } = req.body;

        // Implement logic to check if a user document with the provided email and password exists in your Firestore collection
        // For example:
        const userQuery = firestore.collection('users').where('email', '==', email).where('password', '==', password);
        const userSnapshot = await userQuery.get();

        if (userSnapshot.empty) {
            return res.status(404).json({ error: 'User not found' });
        }

        // Return a success message or any relevant response
        res.json({ message: 'User signed in successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

function login(req, res) {
    try {
        // Check if the user is authenticated by verifying the presence of a user document in your Firestore collection
        // For example:
        const userRef = firestore.collection('users').doc(req.userId);
        userRef.get()
            .then((doc) => {
                if (doc.exists) {
                    // User is authenticated
                    res.json({ authenticated: true, userId: doc.id });
                } else {
                    // User is not authenticated
                    res.json({ authenticated: false });
                }
            })
            .catch((error) => {
                console.error(error);
                res.status(500).json({ error: 'Something went wrong' });
            });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

module.exports = {
    signUp,
    signIn,
    login,
};