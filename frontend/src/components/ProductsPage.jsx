import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL, PRODUCTS_ENDPOINT, PRODUCT_RATINGS_ENDPOINT } from '../api';
import {useAuth} from "./Auth";
import { FaTimes } from 'react-icons/fa';

const ProductsPage = () => {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);
    const [addRating, setAddRating] = useState(null);
    const { token } = useAuth();

    //TODO: Fetch products by user and not questionnaire
    useEffect(() => {
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`${API_BASE_URL}${PRODUCTS_ENDPOINT}/getByUserTags`, { headers })
            .then((res) => {
                const productData = res.data;
                setProducts(productData);
            })
            .catch((err) => {
                console.error('Error fetching products:', err);
            });
    }, [token]);

    const handleFetchRatings = async (productId, productName) => {
        try {
            console.log('Fetching ratings for product ID:', productId);
            const headers = { Authorization: `Bearer ${token}` };
            const response = await axios.get(`${API_BASE_URL}${PRODUCTS_ENDPOINT}/${productId}${PRODUCT_RATINGS_ENDPOINT}`, { headers });
            const ratings = response.data;
            setSelectedProduct({ ...selectedProduct, ratings, name: productName });
        } catch (error) {
            console.error(`Error fetching ratings for product ID ${productId}:`, error);
        }
    };

    const handleAddRating = async (productId) => {
        try {
            console.log('Adding rating for product ID:', productId);
            const headers = { Authorization: `Bearer ${token}` };
            await axios.post(`${API_BASE_URL}${PRODUCTS_ENDPOINT}/${productId}${PRODUCT_RATINGS_ENDPOINT}`, {
                rating: document.getElementById("rating").value,
                comment: document.getElementById("comment").value,
            }, { headers });
        } catch (error) {
            console.error(`Error adding rating for product ID ${productId}:`, error);
        }
        window.location.reload();
    }

    return (
        <div className="min-h-screen">
            <div className="product-page text-center">
                <h2 className="text-3xl font-bold mb-4">Products</h2>
                <div className="flex flex-wrap justify-start">
                    {products.map((product) => (
                        <div key={product.id} className="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 xl:w-1/5 p-2">
                            <div className="h-full bg-white p-4 rounded-lg shadow-md flex flex-col justify-between">
                                <img
                                    src={product.image}
                                    alt={`Product ${product.id}`}
                                    className="mb-2 w-full h-auto"
                                />
                                <div>
                                    <h3 className="text-xl font-bold">{product.name}</h3>
                                    <p>{product.brand}</p>
                                    <p>{product.type}</p>
                                    <p>{product.price}</p>
                                    <div className="mt-2">
                                        <strong>Tags: </strong>
                                        {product.tags.map((tag) => (
                                            <span key={tag.id} className="mr-2">
                                                {tag.name}
                                            </span>
                                        ))}
                                    </div>
                                    <div className="mt-2">
                                        <strong>Rating Count: </strong>{product.count}
                                    </div>
                                    <div className="mt-2">
                                        <strong>Average Rating: </strong>{product.averageRating ?? 0}
                                    </div>
                                    <button onClick={() => handleFetchRatings(product.id, product.name)} className="mt-2 bg-blue-500 text-white p-2 rounded">
                                        Show Ratings
                                    </button>
                                    <button onClick={() => setAddRating(product.id)} className="mt-2 bg-blue-500 text-white p-2 rounded">
                                        Add Rating
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            {selectedProduct && (
                <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded-md h-3/6 overflow-y-auto relative">
                        <div className="flex flex-col">
                            <div className="flex items-center justify-between mb-2">
                                <h2 className="text-xl font-bold">{selectedProduct.name} Ratings</h2>
                                <button
                                    onClick={() => setSelectedProduct(null)}
                                    className="bg-white text-red-500 hover:text-red-800 hover:bg-white"
                                >
                                    <FaTimes /> {/* React Icons "X" icon */}
                                </button>
                            </div>
                            {/* Display each rating on the left */}
                            {selectedProduct.ratings.map((rating) => (
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

}
export default ProductsPage;
