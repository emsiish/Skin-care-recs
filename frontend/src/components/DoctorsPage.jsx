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

    const renderStarIcons = (rating) => {
        const fullStars = Math.floor(rating);
        const starArr = [];

        for (let i = 1; i <= fullStars; i++) {
            starArr.push(1);
        }

        if (rating < 5) {
            const partialStar = rating - fullStars;
            starArr.push(partialStar);
            const emptyStars = 5 - starArr.length;
            for (let i = 1; i <= emptyStars; i++) {
                starArr.push(0);
            }
        }

        return (
            <div className="flex items-center">
                {starArr.map((val, i) => (
                    <div key={i} className="w-8 h-6 flex items-center justify-center text-2xl" style={{ marginRight: '-0.3em' }}>
                    <span
                        className={`bg-gradient-to-r from-yellow-400 to-gray-400 bg-clip-text text-transparent`}
                        style={{
                            backgroundImage: `linear-gradient(90deg, #FFEA00 ${val * 100}%, #bbbac0 ${val * 100}%)`,
                        }}
                    >
                        &#9733;
                    </span>
                    </div>
                ))}
                <span className="ml-2 text-l">({rating})</span>
            </div>
        );
    };

    return (
        <div className="min-h-screen">
            <div className="doctor-page text-center">
                <h2 className="text-3xl font-bold">Doctors</h2>
                <div className="mt-4 flex flex-wrap justify-center">
                    {doctors.map((doctor) => (
                        <div key={doctor.id} className="w-full sm:w-1/2 md:w-1/3 lg:w-1/3 xl:w-1/4 p-2">
                            <div className="bg-white p-2 rounded-2xl shadow-md flex flex-col justify-between">
                                <img
                                    src={doctor.image}
                                    alt={`Doctor ${doctor.id}`}
                                    className="mb-2 w-3/5 object-cover mx-auto rounded-xl"
                                />
                                <div>
                                    <h3 className="font-bold text-xl mb-2">{doctor.name}</h3>
                                    <p className="text-gray-700 text-base">{doctor.hospital}</p>
                                    <p className="text-gray-700 text-base">{doctor.phoneNumber}</p>
                                    <p className="text-gray-700 text-base">{doctor.email}</p>
                                    <div className="mt-2">
                                        <strong>Rating Count: </strong>{doctor.count}
                                    </div>
                                    <div className="flex items-center flex-col">
                                        <strong>Average Rating: </strong>
                                        {renderStarIcons(doctor.averageRating ?? 0)}
                                    </div>
                                    <div className="mt-2 flex justify-center flex-col">
                                        <div className="mb-1">
                                            <button onClick={() => handleFetchRatings(doctor.id, doctor.name)} className="bg-blue-500 text-white p-2 rounded-lg">
                                                Show Ratings
                                            </button>
                                        </div>
                                        <div>
                                            <button onClick={() => setAddRating(doctor.id)} className="bg-blue-500 text-white p-2 rounded-lg">
                                                Add Rating
                                            </button>
                                        </div>
                                    </div>
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