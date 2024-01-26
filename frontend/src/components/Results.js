// src/components/ResultsPage.js
import React from 'react';
import { Link } from 'react-router-dom';

const ResultsPage = () => (
    <div className="container">
        <div className="card bg-white p-4 rounded-lg shadow-md">
            <h1 className="text-3xl font-bold mb-4">
                Thank you for completing the Skincare Questionnaire!
            </h1>
            <p className="mb-4">Here are your results:</p>
            {/* Display the results or perform any other actions */}
            <Link to="/products">
                <button className="bg-blue-600 text-white px-4 py-2 rounded-full hover:bg-blue-500">
                    See Products
                </button>
            </Link>
        </div>
    </div>
);

export default ResultsPage;
