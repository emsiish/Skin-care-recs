import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL, DOCTORS_ENDPOINT, DOCTOR_RATINGS_ENDPOINT } from '../api';
import { useAuth } from "./Auth";
import ShowRatings from './ShowRatings';
import AddRating from './AddRatings';

const DoctorsPage = () => {
    const [doctors, setDoctors] = useState([]);
    const [selectedDoctor, setSelectedDoctor] = useState(null);
    const [addRating, setAddRating] = useState(null);
    const { token, isTokenExpired } = useAuth();

    useEffect(() => {
        isTokenExpired();
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`${API_BASE_URL}${DOCTORS_ENDPOINT}`, { headers })
            .then(response => {
                setDoctors(response.data);
            })
            .catch(error => {
                console.error('Error fetching doctors:', error);
            });
    }, [token, isTokenExpired]);

    const handleFetchRatings = async (doctorId, doctorName) => {
        try {
            isTokenExpired();
            const headers = { Authorization: `Bearer ${token}` };
            const response = await axios.get(`${API_BASE_URL}${DOCTORS_ENDPOINT}/${doctorId}${DOCTOR_RATINGS_ENDPOINT}`, { headers });
            const ratings = response.data;
            setSelectedDoctor({ ...selectedDoctor, ratings, name: doctorName });
        } catch (error) {
            console.error(`Error fetching ratings for doctor ID ${doctorId}:`, error);
        }
    };

    const handleAddRating = async (doctorId) => {
        try {
            isTokenExpired();
            const headers = { Authorization: `Bearer ${token}` };
            await axios.post(`${API_BASE_URL}${DOCTORS_ENDPOINT}/${doctorId}${DOCTOR_RATINGS_ENDPOINT}`, {
                rating: document.getElementById("rating").value,
                comment: document.getElementById("comment").value,
            }, { headers });
        } catch (error) {
            console.error(`Error adding rating for doctor ID ${doctorId}:`, error);
        }
        window.location.reload();
    };

    return (
        <div className="min-h-screen">
            <div className="doctor-page text-center">
                <h2 className="text-3xl font-bold mb-4">Doctors</h2>
                <div className="flex flex-wrap justify-center">
                    {doctors.map((doctor) => (
                        <div key={doctor.id} className="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 xl:w-1/5 p-2">
                            <div className="bg-white p-4 rounded-lg shadow-md flex flex-col justify-between">
                                <img
                                    src={doctor.image}
                                    alt={`Doctor ${doctor.id}`}
                                    className="mb-2 w-4/5 object-cover mx-auto"
                                />
                                <div>
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
                        </div>
                    ))}
                </div>
            </div>
            {selectedDoctor && (
                <ShowRatings
                    selectedObject={selectedDoctor}
                    onClose={() => setSelectedDoctor(null)}
                />
            )}
            {addRating && (
                <AddRating
                    id={addRating}
                    onAddRating={handleAddRating}
                    onClose={() => setAddRating(null)}
                />
            )}
        </div>
    );

};

export default DoctorsPage;
