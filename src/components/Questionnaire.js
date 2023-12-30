// components/QuestionPage.js
import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './styles.css';

const questions = [
    'What is your skin type?',
    'Which skincare concerns do you have?',
    'What is your preferred skincare routine?',
    // Add more questions as needed
];

const options = [
    ['Normal', 'Oily', 'Dry', 'Combination'],
    ['Acne', 'Dryness', 'Wrinkles', 'None'],
    ['Morning routine', 'Evening routine', 'Both', 'None'],
    // Add more options as needed
];

const QuestionPage = () => {
    const { questionNumber } = useParams();
    const navigate = useNavigate();
    const questionIndex = parseInt(questionNumber, 10) - 1;

    const [selectedOption, setSelectedOption] = useState('');

    const handleOptionChange = (option) => {
        setSelectedOption(option);
    };

    const handleNext = () => {
        // Save the selected option or perform any necessary actions
        // For now, let's just log the selected option
        console.log(`Question ${questionIndex + 1}: ${selectedOption}`);

        // Navigate to the next question or the results page if it's the last question
        if (questionIndex < questions.length - 1) {
            navigate(`/question/${questionIndex + 2}`);
        } else {
            // Navigate to the results page or any other page you want
            navigate('/results');
        }
    };

    return (
        <div>
            <h2>{questions[questionIndex]}</h2>
            <form>
                {options[questionIndex].map((option) => (
                    <label key={option}>
                        <input
                            type="radio"
                            value={option}
                            checked={selectedOption === option}
                            onChange={() => handleOptionChange(option)}
                        />
                        {option}
                    </label>
                ))}
            </form>
            <button onClick={handleNext}>Next</button>
        </div>
    );
};

export default QuestionPage;
