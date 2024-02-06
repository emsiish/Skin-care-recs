import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Doctors = () => {
    const [doctors, setDoctors] = useState([]);
    const [selectedDoctor, setSelectedDoctor] = useState(null);

    useEffect(() => {
        // Fetch the list of doctors when the component mounts
        axios.get('http://localhost:8080/api/v1/doctors') // Adjust the endpoint based on your backend
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
            const response = await axios.get(`http://localhost:8080/api/v1/doctors/${doctorId}/ratings`); // Adjust the endpoint
            const ratings = response.data;
            setSelectedDoctor({ ...selectedDoctor, ratings, name: doctorName });
        } catch (error) {
            console.error(`Error fetching ratings for doctor ID ${doctorId}:`, error);
        }
    };

    useEffect(() => {
        // Log selectedDoctor after it has been updated
        console.log('Selected Doctor:', selectedDoctor);
    }, [selectedDoctor]); //

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
                                <p className="text-gray-700 text-base">{doctor.telephone}</p>
                                <p className="text-gray-700 text-base">{doctor.email}</p>
                                <div className="mt-2">
                                    <strong>Rating Count: </strong>{doctor.count}
                                </div>
                                <div className="mt-2">
                                    <strong>Average Rating: </strong>{doctor.averageRating ?? 0}
                                </div>
                                <button onClick={() => handleFetchRatings(doctor.id, doctor.name)} className="mt-2 bg-blue-500 text-white p-2 rounded">
                                    Show Ratings
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            {selectedDoctor && (
                <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded-md">
                        <h2 className="text-xl font-bold mb-2">{selectedDoctor.name} Ratings</h2>
                        {/* Display each rating */}
                        {selectedDoctor.ratings.map((rating) => (
                            <div key={rating.id}>
                                <p><strong>Rating: </strong>{rating.rating}</p>
                                <p><strong>Comment: </strong>{rating.comment}</p>
                                <p><strong>By: </strong>{rating.user.name}</p>
                                <hr className="my-2" />
                            </div>
                        ))}
                        <button onClick={() => setSelectedDoctor(null)} className="mt-4 bg-gray-500 text-white p-2 rounded">
                            Close
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Doctors;
