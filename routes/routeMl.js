const express = require('express');
const router = express.Router();

// Import the ML handlers
const { performInference } = require('../handlers/mlHandler');

// ML inference route (POST)
router.post('/inference', performInference);

// Export the ML routes
module.exports = router;
