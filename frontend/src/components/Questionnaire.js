// src/components/QuestionPage.js
import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

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

const QuestionPage = ({ totalQuestions }) => {
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

    const progress = (questionNumber / totalQuestions) * 100;

    return (
        <div className="container max-w-md">
            <div className="card bg-white p-4 rounded-lg shadow-md w-full">
                <div className="w-full bg-gray-200 rounded-full h-1.5 mb-4 dark:bg-gray-700" style={{ width: '100%' }}>
                    <div
                        className="bg-blue-600 h-1.5 rounded-full dark:bg-blue-500"
                        style={{ width: `${progress}%` }}
                    ></div>
                </div>
                <h2 className="text-3xl font-bold mb-4">{questions[questionIndex]}</h2>
                <form>
                    {options[questionIndex].map((option) => (
                        <label key={option} className="block mb-2">
                            <input
                                type="radio"
                                value={option}
                                checked={selectedOption === option}
                                onChange={() => handleOptionChange(option)}
                                className="mr-2"
                            />
                            {option}
                        </label>
                    ))}
                </form>
                <button
                    onClick={handleNext}
                    className="bg-blue-600 text-white px-4 py-2 rounded-full mt-4 hover:bg-blue-500"
                >
                    Next
                </button>
            </div>
        </div>
    );
};

export default QuestionPage;
