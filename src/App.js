// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import WelcomePage from './components/Welcome';
import QuestionPage from './components/Questionnaire';
import ResultsPage from './components/Results';

const App = () => (
    <Router>
      <Routes>
        <Route path="/" element={<WelcomePage />} />
        <Route path="/question/:questionNumber" element={<QuestionPage />} />
        <Route path="/results" element={<ResultsPage />} />
      </Routes>
    </Router>
);

export default App;
