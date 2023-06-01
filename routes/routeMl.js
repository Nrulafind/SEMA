const express = require('express');
const router = express.Router();
const { performInference } = require('../handlers/handlerMl');

// ML inference route post
router.post('/inference', performInference);

// Export the ML routes
module.exports = router;
