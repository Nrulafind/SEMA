const express = require('express');
const router = express.Router();
const { authenticate } = require('../middleware/authMiddleware');
const { getStudents, getDetailStudents,
    createStudent, updateStudent,
    deleteStudent, getScore,
    createScore, updateScore,
    deleteScore } = require('../handlers/handlerStudents');

//student
router.get('/', authenticate, getStudents);
router.get('/:id', authenticate, getDetailStudents);
router.post('/', authenticate, createStudent);
router.patch('/:id', authenticate, updateStudent);
router.delete('/:id', authenticate, deleteStudent);
//score
router.get('/', authenticate, getScore);
router.get('/:id', authenticate,);
router.post('/', authenticate, createScore);
router.patch('/:id', authenticate, updateScore);
router.delete('/:id', authenticate, deleteScore);

module.exports = router;
