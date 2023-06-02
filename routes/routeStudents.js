const express = require('express');
const router = express.Router();
const authenticate = require('../middleware/authMiddleware');
const { getStudents, createStudent, updateStudent, deleteStudent } = require('../handlers/handlerStudents');

router.get('/', authenticate, getStudents);
router.post('/', authenticate, createStudent);
router.patch('/:id', authenticate, updateStudent);
router.delete('/:id', authenticate, deleteStudent);

module.exports = router;
