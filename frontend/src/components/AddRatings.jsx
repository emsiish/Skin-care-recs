import React from 'react';

const AddRating = ({ id, onAddRating, onClose }) => {
    return (
        <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
            <div className="bg-white p-4 rounded-xl flex flex-col">
                <div className="flex flex-col mb-5 gap-2">
                    <input type="number" id="rating" placeholder="Rating" />
                    <input type="text" id="comment" placeholder="Comment" className="mt-2" />
                </div>
                <div className="flex flex-row justify-between">
                    <button onClick={() => onAddRating(id)} className="bg-blue-500 text-white p-2 rounded-lg mr-2">
                        Add Rating
                    </button>
                    <button onClick={onClose} className="bg-gray-500 text-white p-2 rounded-lg">
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AddRating;
