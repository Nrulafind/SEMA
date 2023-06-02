const jwt = require('jsonwebtoken');

//middleware to verify the JWT token
function authenticateToken(req, res, next) {
    //get the token from the request 
    const token = req.header('Authorization')?.split('')[1];

    if (!token) {
        return res.status(401).json({ message: 'Acces denied. Token Missing' });
    }

    try {
        //verify the token and extract the payload
        const payload = jwt.verify(token, '');

    }

}