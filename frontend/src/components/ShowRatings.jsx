import React from 'react';
import { FaTimes } from 'react-icons/fa';

const ShowRatings = ({ selectedObject, onClose }) => {
    return (
        <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
            <div className="bg-white p-4 rounded-md h-3/6 overflow-y-auto relative">
                <div className="flex flex-col">
                    <div className="flex items-center justify-between mb-2">
                        <h2 className="text-xl font-bold">{selectedObject.name} Ratings</h2>
                        <button
                            onClick={onClose}
                            className="bg-white text-red-500 hover:text-red-800 hover:bg-white"
                        >
                            <FaTimes />
                        </button>
                    </div>
                    {selectedObject.ratings.map((rating) => (
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
    );
};

export default ShowRatings;