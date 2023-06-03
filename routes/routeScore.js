const express = require('express');
const router = express.Router();
const { authenticate } = require('../middleware/authMiddleware');
const { getScore, getDetailScore, createScore, updateScore, deleteScore } = require('../handlers/handlerScore');

router.get('/', authenticate, getScore);
router.get('/:id', authenticate, getDetailScore);
router.post('/', authenticate, createScore);
router.patch('/:id', authenticate, updateScore);
router.delete('/:id', authenticate, deleteScore);

module.exports = router;
