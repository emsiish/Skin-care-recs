// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import WelcomePage from './components/Welcome';
import QuestionPage from './components/Questionnaire';
import ResultsPage from './components/Results';
import Products from './components/Products';
import Navbar from './components/Navbar';
import './components/styles.css';
import Doctors from "./components/Doctors";

const App = () => (
    <Router>
        <Navbar />
        <Routes>
            <Route path="/" element={<WelcomePage />} />
            <Route path="/question/:questionNumber" element={<QuestionPage totalQuestions={3}/>} />
            <Route path="/results" element={<ResultsPage />} />
            <Route path="/products" element={<Products />} />
            <Route path="/doctors" element={<Doctors />} />
        </Routes>
    </Router>
);

export default App;
