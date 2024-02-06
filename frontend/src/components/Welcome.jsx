// src/components/WelcomePage.js
import React from 'react';
import { Link } from 'react-router-dom';

const WelcomePage = () => (
    <div className="flex items-center justify-center h-screen">
        <div className="card bg-white p-4 rounded-lg shadow-md">
            <h1 className="text-3xl font-bold mb-4">Welcome to the Skincare Questionnaire!</h1>
            <p>Click the button below to start.</p>
            <Link to="/question/1">
                <button className="bg-blue-600 text-white px-4 py-2 rounded-full mt-4 hover:bg-blue-500">
                    Start Questionnaire
                </button>
            </Link>
        </div>
    </div>
);

export default WelcomePage;
