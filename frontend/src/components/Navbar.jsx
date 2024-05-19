import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from "./Auth";

const Navbar = () => {
    const navigate = useNavigate();
    const { token, logout } = useAuth();

    const logoutButton = () => {
        logout();
        navigate('/login');
    }

    return (
        <nav className="fixed top-0 left-0 w-full bg-blue-600 p-4 z-10">
            <ul className="flex">
                <li>
                    <Link to="/" className="text-white font-bold">Quiz</Link>
                </li>
                <li>
                    <Link to="/products" className="text-white font-bold">My Products</Link>
                </li>
                <li>
                    <Link to="/doctors" className="text-white font-bold">Doctors</Link>
                </li>
                <div className="ml-auto">
                    {token && (
                        <Link to="#" onClick={logoutButton} className="text-white font-bold">Logout</Link>
                    )}
                </div>
            </ul>
        </nav>
    );
};

export default Navbar;
