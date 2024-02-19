import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { API_BASE_URL, REGISTER_ENDPOINT } from '../api';
import { useAuth } from './Auth';

const Register = () => {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useAuth(); // Use the useAuth hook to get the login function

    const handleRegister = async () => {
        try {
            const response = await axios.post(`${API_BASE_URL}${REGISTER_ENDPOINT}`, {
                name: name,
                email: email,
                password: password,
            });

            const token = response.data.token;

            login(token);
            navigate('/');
            console.log('Registration successful');
        } catch (error) {
            console.error('Registration failed', error);
        }
    };

    return (
        <div className="flex items-center justify-center h-screen">
            <div className="card bg-white p-4 rounded-lg shadow-md">
                <h1 className="text-3xl font-bold mb-4">Register</h1>
                <form>
                    <label className="block mb-2">Name:</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="w-full px-3 py-2 border rounded"
                    />
                    <label className="block mt-2 mb-2">Email:</label>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="w-full px-3 py-2 border rounded"
                    />
                    <label className="block mt-2 mb-2">Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full px-3 py-2 border rounded"
                    />
                    <button
                        type="button"
                        onClick={handleRegister}
                        className="bg-blue-600 text-white px-4 py-2 rounded-full mt-4 hover:bg-blue-500"
                    >
                        Register
                    </button>
                </form>
                <p className="mt-2">
                    Already have an account? <Link to="/login" className="text-blue-600">Login</Link>
                </p>
            </div>
        </div>
    );
};

export default Register;
