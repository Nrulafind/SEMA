const { admin } = require('../middleware/authMiddleware');

// Generate custom token using Firebase Admin SDK
function generateToken(uid) {
    return admin.auth().createCustomToken(uid);
}

module.exports = generateToken;