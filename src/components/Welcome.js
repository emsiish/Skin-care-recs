// components/WelcomePage.js
import React from 'react';
import { Link } from 'react-router-dom';
import './styles.css'; // Import the styles

const WelcomePage = () => (
    <div className="container">
        <div className="card">
            <h1>Welcome to the Skincare Questionnaire!</h1>
            <p>Click the button below to start.</p>
            <Link to="/question/1">
                <button>Start Questionnaire</button>
            </Link>
        </div>
    </div>
);

export default WelcomePage;
