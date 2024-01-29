import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Products = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products from the backend when the component mounts
        axios.get('http://localhost:8080/api/v1/products')
            .then((res) => {
                // Extract data from the 'data' property and convert it to an array
                const productData = res.data;
                setProducts(productData);
            }).catch((err) => {
            console.error('Error fetching products:', err);
        });
    }, []);

    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <div className="product-page text-center">
                <h2 className="text-3xl font-bold mb-4">Products</h2>
                <div className="flex flex-wrap justify-center">
                    {products.map((product) => (
                        <div key={product.id} className="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 xl:w-1/5 p-2">
                            <div className="h-full bg-white p-4 rounded-lg shadow-md flex flex-col justify-between">
                                <img
                                    src={product.image}
                                    alt={`Product ${product.id}`}
                                    className="mb-2 w-full h-auto" // Adjust max height as needed
                                />
                                <div>
                                    <h3 className="text-xl font-bold">{product.name}</h3>
                                    <p>{product.brand}</p>
                                    <p>{product.type}</p>
                                    <p>{product.price}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Products;
