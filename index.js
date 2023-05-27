const express = require('express');
const app = express();
const port = 3000;

// Import routes
const StudentsRoutes = require('./routes/routeStudents');
const AuthRoutes = require('./routes/routeAuth');

// Parse JSON request bodies
app.use(express.json());

// Use routes
app.use('/Student', StudentsRoutes);
app.use('/Auth', AuthRoutes);

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
