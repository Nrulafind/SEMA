const express = require('express');
const router = express.Router();
const { authenticate } = require('../middleware/authMiddleware');
const { sendMessage } = require('../handlers/handlerChat');

// Send message route using post
router.post('/sendMessage', authenticate, sendMessage);

module.exports = router;
