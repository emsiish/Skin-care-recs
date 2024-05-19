import React, {useEffect, useState} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import {API_BASE_URL, USERS_ENDPOINT, USER_TAGS_ENDPOINT, QUESTIONS_ENDPOINT} from '../api';
import {useAuth} from "./Auth";
import * as jose from 'jose';

const QuestionPage = ({ totalQuestions }) => {
    const { questionNumber } = useParams();
    const navigate = useNavigate();
    const questionIndex = parseInt(questionNumber, 10) - 1;
    const [questions, setQuestions] = useState([]);
    const { token, isTokenExpired } = useAuth();

    useEffect(() => {
        isTokenExpired();
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`${API_BASE_URL}${QUESTIONS_ENDPOINT}`, { headers })
            .then((res) => {
                const questionsData = res.data;
                setQuestions(questionsData);
            })
            .catch((err) => {
                console.error('Error fetching questions:', err);
            });
    }, [token, isTokenExpired]);

    const [selectedOptions, setSelectedOptions] = useState(Array(totalQuestions).fill({ name: '' }));
    const handleOptionChange = (option) => {
        const newSelectedOptions = [...selectedOptions];
        newSelectedOptions[questionIndex] = { name: option };
        setSelectedOptions(newSelectedOptions);
    };

    const handleNext = async () => {
        if (selectedOptions[questionIndex].name) {
            if (questionIndex < questions.length - 1) {
            navigate(`/question/${questionIndex + 2}`);
            } else {
                isTokenExpired();
                const decodedToken = jose.decodeJwt(token);
                if (decodedToken && decodedToken.id) {
                    const userId = decodedToken.id;
                    const headers = {Authorization: `Bearer ${token}`};
                    try {
                        await axios.put(`${API_BASE_URL}${USERS_ENDPOINT}/${userId}${USER_TAGS_ENDPOINT}`, selectedOptions, { headers });
                        navigate('/products');
                    } catch (error) {
                        console.error('Error during PUT request:', error);
                    }
                } else {
                    console.error('Unable to retrieve user ID from JWT token.');
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
                <div className="card bg-white p-4 rounded-2xl shadow-md w-full flex flex-col items-center">
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
