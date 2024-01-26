// src/components/Products.js
import React from 'react';

const Products = () => {
    // Replace this data with your actual product information, including image URLs
    const products = [
        {
            id: 1,
            name: 'Product A',
            description: 'Description of Product A',
            imageUrl: 'https://via.placeholder.com/150', // Placeholder image URL
        },
        {
            id: 2,
            name: 'Product B',
            description: 'Description of Product B',
            imageUrl: 'https://via.placeholder.com/150', // Placeholder image URL
        },
        {
            id: 3,
            name: 'Product C',
            description: 'Description of Product C',
            imageUrl: 'https://via.placeholder.com/150', // Placeholder image URL
        },
    ];

    return (
        <div className="product-page">
            <h2 className="text-3xl font-bold mb-4">Products</h2>
            <div className="flex flex-wrap">
                {products.map((product) => (
                    <div key={product.id} className="product-card bg-white p-4 m-4 rounded-lg shadow-md">
                        <img src={product.imageUrl} alt={`Product ${product.id}`} className="mb-2" />
                        <h3 className="text-xl font-bold">{product.name}</h3>
                        <p>{product.description}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Products;
