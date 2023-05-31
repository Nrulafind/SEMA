const express = require('express');
const router = express.Router();
const { sendNotification } = require('../handlers/handlerNotification');

// Send notification route
router.post('/sendNotification', sendNotification);

module.exports = router;
