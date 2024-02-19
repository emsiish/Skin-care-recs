import React, {useEffect, useState} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import {API_BASE_URL, USERS_ENDPOINT, USER_TAGS_ENDPOINT} from '../api';
import {useAuth} from "./Auth";
import * as jose from 'jose';

/*
const questions = [
    'What is your skin type?',
    'Which skincare concerns do you have?',
    'What is your preferred skincare routine?',
];

const options = [
    ['Normal', 'Oily', 'Dry', 'Combination'],
    ['Acne', 'Dryness', 'Wrinkles', 'None'],
    ['Morning', 'Evening', 'Both', 'None'],
];
*/

const QuestionPage = ({ totalQuestions }) => {
    const { questionNumber } = useParams();
    const navigate = useNavigate();
    const questionIndex = parseInt(questionNumber, 10) - 1;
    const [questions, setQuestions] = useState([]);
    const { token } = useAuth();

    useEffect(() => {
        console.log('Fetching questions...');
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`http://127.0.0.1:8080/api/v1/questions`, { headers })
            .then((res) => {
                const questionsData = res.data;
                console.log(questionsData);
                setQuestions(questionsData);
            })
            .catch((err) => {
                console.error('Error fetching questions:', err);
            });
    }, [token]);

    const [selectedOptions, setSelectedOptions] = useState(Array(totalQuestions).fill({ name: '' }));
    const handleOptionChange = (option) => {
        const newSelectedOptions = [...selectedOptions];
        newSelectedOptions[questionIndex] = { name: option };
        setSelectedOptions(newSelectedOptions);
    };

    const handleNext = () => {
        if (selectedOptions[questionIndex].name) {
            if (questionIndex < questions.length - 1) {
            navigate(`/question/${questionIndex + 2}`);
            } else {
                // Retrieve the token from local storage
                if (token) {
                    // Decode the token to access the payload
                    const decodedToken = jose.decodeJwt(token);

                    // Check if the decodedToken contains the user ID
                    if (decodedToken && decodedToken.id) {
                        const userId = decodedToken.id;

                        const headers = {Authorization: `Bearer ${token}`};

                        // Include the user ID in the URL
                        axios.put(`${API_BASE_URL}${USERS_ENDPOINT}/${userId}${USER_TAGS_ENDPOINT}`, selectedOptions, {headers})
                            .catch((error) => {
                                console.error('Error during PUT request:', error);
                            });

                        navigate('/products');
                    } else {
                        console.error('Unable to retrieve user ID from JWT token.');
                    }
                } else {
                    console.error('JWT token not found in local storage.');
                }
            }
        } else {
            alert('Please select an option');
        }
    };
    const progress = (questionNumber / totalQuestions) * 100;

    return (
        <div className="flex items-center justify-center h-screen">
            <div className="container max-w-md">
                <div className="card bg-white p-4 rounded-lg shadow-md w-full flex flex-col items-center">
                    <div className="w-full bg-gray-200 rounded-full h-1.5 mb-4 dark:bg-gray-700" style={{ width: '100%' }}>
                        <div className="bg-blue-600 h-1.5 rounded-full dark:bg-blue-500" style={{ width: `${progress}%` }}></div>
                    </div>
                    {questions.length > 0 ? (
                        <>
                            <h2 className="text-3xl font-bold mb-4">{questions[questionIndex].question}</h2>
                            <form className="flex flex-col items-start">
                                {questions[questionIndex].tags.map((tag) => (
                                    <label key={tag.id} className="block mb-2">
                                        <input
                                            type="radio"
                                            value={tag.name}
                                            checked={selectedOptions[questionIndex].name === tag.name}
                                            onChange={() => handleOptionChange(tag.name)}
                                            className="mr-2"
                                            required
                                        />
                                        {tag.name}
                                    </label>
                                ))}
                            </form>
                            <button
                                onClick={handleNext}
                                className="bg-blue-600 text-white px-4 py-2 rounded-full mt-4 hover:bg-blue-500"
                            >
                                Next
                            </button>
                        </>
                    ) : (
                        <p>Loading questions...</p>
                    )}
                </div>
            </div>
        </div>
    );

};

export default QuestionPage;
