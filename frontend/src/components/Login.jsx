import React, { useState } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import axios from 'axios';
import { API_BASE_URL, LOGIN_ENDPOINT } from '../api';
import { useAuth } from './Auth';

const Login = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useAuth();

    const handleLogin = async () => {
        try {
            const response = await axios.post(`${API_BASE_URL}${LOGIN_ENDPOINT}`, {
                email: email,
                password: password,
            });

            const token = response.data.token;

            login(token);
            navigate('/');
            console.log('Login successful');
        } catch (error) {
            console.error('Login failed', error);
        }
    };

    return (
        <div className="flex items-center justify-center h-screen">
            <div className="card bg-white p-4 rounded-2xl shadow-md">
                <h1 className="text-3xl font-bold mb-4">Login</h1>
                <form>
                    <label className="block mb-2">Email:</label>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="w-full px-3 py-2 border rounded-lg"
                    />
                    <label className="block mt-2 mb-2">Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full px-3 py-2 border rounded-lg"
                    />
                    <button
                        type="button"
                        onClick={handleLogin}
                        className="bg-blue-600 text-white px-4 py-2 rounded-full mt-4 hover:bg-blue-500"
                    >
                        Login
                    </button>
                </form>
                <p className="mt-2">
                    Don't have an account? <Link to="/register" className="text-blue-600">Register</Link>
                </p>
            </div>
        </div>
    );
};

export default Login;
