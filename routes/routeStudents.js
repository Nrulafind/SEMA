const express = require('express');
const router = express.Router();
const { authenticate } = require('../middleware/authMiddleware');
const { getStudents, getDetailStudents,
    createStudent, getDetailScore, updateStudent,
    deleteStudent, getScore,
    createScore, updateScore,
    deleteScore, getAttendance, getDetailAttendance,
    createAttendance, updateAttendance, deleteAttendance } = require('../handlers/handlerStudents');

//student
router.get('/', authenticate, getStudents);
router.get('/:id', authenticate, getDetailStudents);
router.post('/', authenticate, createStudent);
router.patch('/:id', authenticate, updateStudent);
router.delete('/:id', authenticate, deleteStudent);
//score
router.get('/', authenticate, getScore);
router.get('/:id', authenticate, getDetailScore);
router.post('/', authenticate, createScore);
router.patch('/:id', authenticate, updateScore);
router.delete('/:id', authenticate, deleteScore);
//attendance
router.get('/', authenticate, getAttendance);
router.get('/:id', authenticate, getDetailAttendance);
router.post('/', authenticate, createAttendance);
router.patch('/:id', authenticate, updateAttendance);
router.delete('/:id', authenticate, deleteAttendance);
module.exports = router;
