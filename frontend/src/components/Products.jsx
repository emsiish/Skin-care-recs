import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';

const Products = () => {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);
    const location = useLocation();
    const selectedOptions = location.state ? location.state.selectedOptions : [];
    const [addRating, setAddRating] = useState(null);

    useEffect(() => {
        axios.post('http://localhost:8080/api/v1/products/getByTags', selectedOptions)
            .then((res) => {
                const productData = res.data;
                setProducts(productData);
            })
            .catch((err) => {
                console.error('Error fetching products:', err);
            });
    }, [selectedOptions]);

    const handleFetchRatings = async (productId, productName) => {
        try {
            console.log('Fetching ratings for product ID:', productId);
            const response = await axios.get(`http://localhost:8080/api/v1/products/${productId}/ratings`);
            const ratings = response.data;
            setSelectedProduct({ ...selectedProduct, ratings, name: productName });
        } catch (error) {
            console.error(`Error fetching ratings for product ID ${productId}:`, error);
        }
    };

    const handleAddRating = async (productId) => {
        try {
            console.log('Adding rating for product ID:', productId);
            await axios.post(`http://localhost:8080/api/v1/products/${productId}/ratings`, {
                rating: document.getElementById("rating").value,
                comment: document.getElementById("comment").value,
                user: {
                    "email": "emiliyausheva730@gmail.com"
                }
            });
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
                    <div className="bg-white p-4 rounded-md">
                        <h2 className="text-xl font-bold mb-2">{selectedProduct.name} Ratings</h2>
                        {/* Display each rating */}
                        {selectedProduct.ratings.map((rating) => (
                            <div key={rating.id}>
                                <p><strong>Rating: </strong>{rating.rating}</p>
                                <p><strong>Comment: </strong>{rating.comment}</p>
                                <p><strong>By: </strong>{rating.user.name}</p>
                                <hr className="my-2" />
                            </div>
                        ))}
                        <button onClick={() => setSelectedProduct(null)} className="mt-4 bg-gray-500 text-white p-2 rounded">
                            Close
                        </button>
                    </div>
                </div>
            )}
            {addRating && (
                <div className="fixed inset-0 bg-gray-700 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded-md">
                        <input type="number" id="rating" placeholder="Rating" />
                        <input type="text" id="comment" placeholder="Comment" />
                        <button onClick={() => handleAddRating(addRating)} className="mt-2 bg-blue-500 text-white p-2 rounded">
                            Add Rating
                        </button>
                        <button onClick={() => setAddRating(null)} className="mt-2 bg-gray-500 text-white p-2 rounded">
                            Close
                        </button>
                    </div>
                </div>
            )}
        </div>
    );

}
export default Products;
