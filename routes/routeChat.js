const express = require('express');
const router = express.Router();
const { sendMessage } = require('../handlers/handlerChat');

// Send message route
router.post('/sendMessage', sendMessage);

module.exports = router;
