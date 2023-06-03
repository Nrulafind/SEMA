const admin = require('firebase-admin');

//initialize the Firebase admin SDK with service acoouunt credential
const serviceAccount = require('../service-account/service-account-key.json');
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

//middleware for function token authentication
function authenticate(req, res, next) {
    const token = req.headers.authorization;

    if (!token) {
        return res.status(401).json({ error: 'Unauthorized' });
    }

    // Verify the token using Firebase Admin SDK
    admin.auth().verifyIdToken(token)
        .then((decodedToken) => {
            req.headers['X-User-Id'] = decodedToken.uid; // Store the userId in a custom header
            req.userId = decodedToken.uid;
            next();
        })
        .catch((error) => {
            console.error(error);
            res.status(403).json({ error: 'Invalid token' });
        });
}

module.exports = { authenticate, admin };