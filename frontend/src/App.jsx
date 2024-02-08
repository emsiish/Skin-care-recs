// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StartPage from './components/StartPage';
import QuestionPage from './components/QuestionPage';
import ResultsPage from './components/Results';
import ProductsPage from './components/ProductsPage';
import Navbar from './components/Navbar';
import './components/styles.css';
import DoctorsPage from "./components/DoctorsPage";
import './App.css';

const App = () => (
    <Router>
        <Navbar />
        <Routes>
            <Route path="/" element={<StartPage />} />
            <Route path="/question/:questionNumber" element={<QuestionPage totalQuestions={3}/>} />
            <Route path="/results" element={<ResultsPage />} />
            <Route path="/products" element={<ProductsPage />} />
            <Route path="/doctors" element={<DoctorsPage />} />
        </Routes>
    </Router>
);

export default App;
