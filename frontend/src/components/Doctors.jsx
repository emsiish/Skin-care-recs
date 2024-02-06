// src/components/DoctorsList.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Doctors = () => {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        // Fetch the list of doctors when the component mounts
        axios.get('http://localhost:8080/api/v1/doctors')
            .then(response => {
                setDoctors(response.data);
            })
            .catch(error => {
                console.error('Error fetching doctors:', error);
            });
    }, []);

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
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Doctors;
