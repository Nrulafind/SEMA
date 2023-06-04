const Firestore = require('@google-cloud/firestore');
const serviceAccount = require('../service-account/service-account-key.json');


// Initialize Firestore with service account credentials
const firestore = new Firestore({
    projectId: serviceAccount.project_id,
    credentials: {
        client_email: serviceAccount.client_email,
        private_key: serviceAccount.private_key,
    },
});

//function for student

async function getStudents(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        //fetch all students from Firestore or any other data source
        const studentsSnapshot = await firestore
            .collection('student')
            .where('userId', '==', userId)
            .get();

        if (studentsSnapshot.empty) {
            return res.status(404).json({ error: 'No students found' });
        }

        const students = [];

        studentsSnapshot.forEach((studentDoc) => {
            const student = {
                id: studentDoc.id,
                ...studentDoc.data(),
            };
            students.push(student);
        });

        // Return the fetched students as a response
        res.json(students);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function getDetailStudents(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        //fetch all students from Firestore or any other data source
        const studentsSnapshot = await firestore
            .collection('student')
            .where('userId', '==', userId)
            .get();

        if (studentsSnapshot.empty) {
            return res.status(404).json({ error: 'No students found' });
        }

        const students = [];

        studentsSnapshot.forEach((studentDoc) => {
            const student = {
                id: studentDoc.id,
                ...studentDoc.data(),
            };
            students.push(student);
        });

        // Return the fetched students as a response
        res.json(students);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function createStudent(req, res) {
    try {
        const userId = req.headers['X-User-Id'];// Retrieve the user ID from the custom header
        // Extract the data from the request body
        const { ID, nama } = req.body;

        // Check ID or nama  
        if (!ID || !nama || ID.trim() === '' || nama.trim() === '') {
            res.status(400).json({ error: 'Invalid student data' });
            return; // Return here to stop further execution
        }

        // create a new student in Firestore or any other data source
        // Check ID field has a valid value
        if (typeof ID !== 'string' || ID.trim() === '') {
            res.status(400).json({ error: 'Invalid student ID' });
            return; // Return here to stop further execution
        }

        const studentRef = await firestore.collection('student').add({ ID, nama });

        // Return the ID of the created student as a response
        res.json({ id: studentRef.id });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}


async function updateStudent(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        const { id } = req.params;
        const updatedData = req.body;

        // update a specific student in Firestore or any other data source
        const studentRef = firestore.collection('student').doc(id);
        const studentDoc = await studentRef.get();

        if (!studentDoc.exists) {
            return res.status(404).json({ error: 'Student not found' });
        }

        // Check the request is a PUT or PATCH
        if (req.method === 'PUT') {
            await studentRef.set(updatedData);
        } else if (req.method === 'PATCH') {
            await studentRef.update(updatedData);
        }

        // Return a success message 
        res.json({ message: 'Student updated successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function deleteStudent(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        const { id } = req.params;
        // delete a specific student from Firestore or any other data source
        const studentRef = firestore.collection('student').doc(id);
        const studentDoc = await studentRef.get();

        if (!studentDoc.exists) {
            return res.status(404).json({ error: 'Student not found' });
        }

        await studentRef.delete();

        // Return a success message or any relevant response
        res.json({ message: 'Student deleted successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

//function For Score

async function getScore(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        //fetch all students from Firestore or any other data source
        const scoreSnapshot = await firestore
            .collection('score')
            .where('userId', '==', userId)
            .get();

        if (scoreSnapshot.empty) {
            return res.status(404).json({ error: 'No Score found' });
        }

        const score = [];

        scoreSnapshot.forEach((scoreDoc) => {
            const score = {
                id: scoreDoc.id,
                ...scoreDoc.data(),
            };
            score.push(score);
        });

        // Return the fetched students as a response
        res.json(score);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function createScore(req, res) {
    try {
        const userId = req.headers['X-User-Id'];// Retrieve the user ID from the custom header
        // Extract the data from the request body
        const { ID, nama, score } = req.body;

        // Check ID or nama  
        if (!ID || !nama || ID.trim() === '' || nama.trim() === '') {
            res.status(400).json({ error: 'Invalid student data' });
            return; // Return here to stop further execution
        }

        // create a new student in Firestore or any other data source
        // Check ID field has a valid value
        if (typeof ID !== 'string' || ID.trim() === '') {
            res.status(400).json({ error: 'Invalid student ID' });
            return; // Return here to stop further execution
        }

        const scoreRef = await firestore.collection('score').where('userId', '==', userId).add({ ID, nama, score });

        // Return the ID of the created student as a response
        res.json({ id: scoreRef.id });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}


async function updateScore(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        const { id } = req.params;
        const updatedData = req.body;

        // update a specific student in Firestore or any other data source
        const scoreRef = firestore.collection('score').doc(id).where('userId', '==', userId);
        const scoreDoc = await scoreRef.get();

        if (!scoreDoc.exists) {
            return res.status(404).json({ error: 'score not found' });
        }

        // Check the request is a PUT or PATCH
        if (req.method === 'PUT') {
            await scoreRef.set(updatedData);
        } else if (req.method === 'PATCH') {
            await scoreRef.update(updatedData);
        }

        // Return a success message 
        res.json({ message: 'score updated successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}

async function deleteScore(req, res) {
    try {
        const userId = req.headers['X-User-Id']; // Retrieve the user ID from the custom header
        const { id } = req.params;
        // delete a specific student from Firestore or any other data source
        const scoreRef = firestore.collection('score').doc(id).where('userId', '==', userId);
        const scoreDoc = await scoreRef.get();

        if (!scoreDoc.exists) {
            return res.status(404).json({ error: 'score not found' });
        }

        await scoreRef.delete();

        // Return a success message or any relevant response
        res.json({ message: 'Score deleted successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Something went wrong' });
    }
}


module.exports = {
    //student
    getStudents,
    getDetailStudents,
    createStudent,
    updateStudent,
    deleteStudent,
    //score
    getScore,
    createScore,
    updateScore,
    deleteScore,
};
