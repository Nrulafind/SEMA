const express = require('express');
const router = express.Router();
const authenticate = require('../middleware/authMiddleware');
const { sendNotification } = require('../handlers/handlerNotification');

// Send notification route
router.post('/sendNotification', authenticate, sendNotification);

module.exports = router;
