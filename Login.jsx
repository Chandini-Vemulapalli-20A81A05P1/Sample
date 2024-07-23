import express from 'express';
import session from 'express-session';
import cookieParser from 'cookie-parser';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import connection from './DBConnection.js'; // Importing database connection

const app = express();
const PORT = 3000;
const JWT_SECRET = 'jdhgkjdhskvdskhvhkdgsvhdahkgv'; // Replace with a secure key

app.use(express.json());
app.use(cookieParser());





// Middleware to check for authentication
const authenticateToken = (req, res, next) => {
const token = req.cookies.authToken;

if (!token) return res.status(401).json({ message: 'Access denied' });

jwt.verify(token, JWT_SECRET, (err, user) => {
if (err) return res.status(403).json({ message: 'Invalid token' });
req.user = user; // Attach user info to request object
next();
});
};

// Logout route
app.post('/logout', (req, res) => {
res.clearCookie('authToken'); // Clear the authentication token cookie
res.status(200).json({ message: 'Logout successful' });
});

// Start the server
app.listen(PORT, () => {
console.log(Server is running on http://localhost:${PORT});
});
