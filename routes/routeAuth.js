const express = require('express');
const router = express.Router();
const { signIn, signUp, login } = require('../handlers/handlerAuth');

//post sign ini 

router.post('/signup', signUp);
router.post('/signin', signIn);
router.post('/login', login);

module.exports = router;