import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL, DOCTORS_ENDPOINT, DOCTOR_RATINGS_ENDPOINT } from '../api';
import {useAuth} from "./Auth";
import {FaTimes} from "react-icons/fa";

const DoctorsPage = () => {
    const [doctors, setDoctors] = useState([]);
    const [selectedDoctor, setSelectedDoctor] = useState(null);
    const [addRating, setAddRating] = useState(null);
    const { token } = useAuth();

    useEffect(() => {
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`${API_BASE_URL}${DOCTORS_ENDPOINT}`, { headers })
            .then(response => {
                setDoctors(response.data);
            })
            .catch(error => {
                console.error('Error fetching doctors:', error);
            });
    }, []);

    const handleFetchRatings = async (doctorId, doctorName) => {
        try {
            console.log('Fetching ratings for doctor ID:', doctorId);
            const headers = { Authorization: `Bearer ${token}` };
            const response = await axios.get(`${API_BASE_URL}${DOCTORS_ENDPOINT}/${doctorId}${DOCTOR_RATINGS_ENDPOINT}`, {headers});
            const ratings = response.data;
            setSelectedDoctor({ ...selectedDoctor, ratings, name: doctorName });
        } catch (error) {
            console.error(`Error fetching ratings for doctor ID ${doctorId}:`, error);
        }
    };
    const handleAddRating = async (doctorId) => {
        try {
            console.log('Adding rating for doctor ID:', doctorId);
            const headers = { Authorization: `Bearer ${token}` };
            await axios.post(`${API_BASE_URL}${DOCTORS_ENDPOINT}/${doctorId}${DOCTOR_RATINGS_ENDPOINT}`, {
                rating: document.getElementById("rating").value,
                comment: document.getElementById("comment").value,
            }, { headers });
        } catch (error) {
            console.error(`Error adding rating for product ID ${doctorId}:`, error);
        }
        window.location.reload();
    }

    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <div className="product-page text-center">
                <h2 className="text-3xl font-bold mb-4">Doctors</h2>
                <div className="flex flex-wrap justify-center">
                    {doctors.map((doctor) => (
                        <div key={doctor.id} className="card max-w-sm rounded overflow-hidden shadow-lg m-4">
                            <div className="px-6 py-4">
                                <h3 className="font-bold text-xl mb-2">{doctor.name}</h3>
                                <p className="text-gray-700 text-base">{doctor.hospital}</p>
                                <p className="text-gray-700 text-base">{doctor.phoneNumber}</p>
                                <p className="text-gray-700 text-base">{doctor.email}</p>
                                <div className="mt-2">
                                    <strong>Rating Count: </strong>{doctor.count}
                                </div>
                                <div className="mt-2">
                                    <strong>Average Rating: </strong>{doctor.averageRating ?? 0}
                                </div>
                                <button onClick={() => handleFetchRatings(doctor.id, doctor.name)} className="mt-2 mr-2 bg-blue-500 text-white p-2 rounded">
                                    Show Ratings
                                </button>
                                <button onClick={() => setAddRating(doctor.id)} className="mt-2 bg-blue-500 text-white p-2 rounded">
                                    Add Rating
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            {selectedDoctor && (
                <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded-md h-3/6 overflow-y-auto relative">
                        <div className="flex flex-col">
                            <div className="flex items-center justify-between mb-2">
                                <h2 className="text-xl font-bold">{selectedDoctor.name} Ratings</h2>
                                <button
                                    onClick={() => setSelectedDoctor(null)}
                                    className="bg-white text-red-500 hover:text-red-800 hover:bg-white"
                                >
                                    <FaTimes /> {/* React Icons "X" icon */}
                                </button>
                            </div>
                            {/* Display each rating on the left */}
                            {selectedDoctor.ratings.map((rating) => (
                                <div key={rating.id} className="items-start">
                                    <div className="mr-4">
                                        <p><strong>Rating: </strong>{rating.rating}</p>
                                        <p><strong>Comment: </strong>{rating.comment}</p>
                                        <p><strong>By: </strong>{rating.user.name}</p>
                                    </div>
                                    <hr className="my-2 border-t border-gray-300" />
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
            {addRating && (
                <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded-md flex flex-col">
                        <div className="flex flex-col mb-5 gap-2">
                            <input type="number" id="rating" placeholder="Rating" />
                            <input type="text" id="comment" placeholder="Comment" className="mt-2" />
                        </div>
                        <div className="flex flex-row justify-between">
                            <button onClick={() => handleAddRating(addRating)} className="bg-blue-500 text-white p-2 rounded mr-2">
                                Add Rating
                            </button>
                            <button onClick={() => setAddRating(null)} className="bg-gray-500 text-white p-2 rounded">
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default DoctorsPage;
