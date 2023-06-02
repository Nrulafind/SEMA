const express = require('express');
const router = express.Router();
const authenticate = require('../middleware/authMiddleware');
const { performInference } = require('../handlers/handlerMl');

// ML inference route post
router.post('/inference', authenticate, performInference);

// Export the ML routes
module.exports = router;
