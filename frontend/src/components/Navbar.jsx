// src/components/Navbar.jsx
import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
    <nav className="fixed top-0 left-0 w-full bg-blue-600 p-4 z-10">
        <ul className="flex">
            <li className="mr-4">
                <Link to="/" className="text-white font-bold">Home</Link>
            </li>
            <li>
                <Link to="/products" className="text-white font-bold">Products</Link>
            </li>
            <li>
                <Link to="/doctors" className="text-white font-bold">Doctors</Link>
            </li>
        </ul>
    </nav>
);

export default Navbar;
