import React, {Fragment} from 'react';
import {BrowserRouter as Router, Navigate, Outlet, Route, Routes} from 'react-router-dom';
import StartPage from './components/StartPage';
import QuestionPage from './components/QuestionPage';
import ResultsPage from './components/Results';
import ProductsPage from './components/ProductsPage';
import Navbar from './components/Navbar';
import './components/styles.css';
import DoctorsPage from "./components/DoctorsPage";
import './App.css';
import Login from "./components/Login";
import {AuthProvider, useAuth} from "./components/Auth";
import Register from "./components/Register";

const PrivateRoute = ({ element, ...props }) => {
    const { token } = useAuth();
    return token ? <Outlet /> : <Navigate to="/login" />;
};

const App = () => (
        <Router>
            <Fragment>
                <AuthProvider>
                    <Navbar />
                    <Routes>
                        <Route exact path='/' element={<PrivateRoute/>}>
                            <Route exact path="/" element={<StartPage />} />
                            <Route exact path="/question/:questionNumber" element={<QuestionPage totalQuestions={3} />} />
                            <Route exact path="/results" element={<ResultsPage />} />
                            <Route exact path="/products" element={<ProductsPage />} />
                            <Route exact path="/doctors" element={<DoctorsPage />} />
                        </Route>
                        <Route exact path="/login" element={<Login />} />
                        <Route exact path="/register" element={<Register />} />
                    </Routes>
                </AuthProvider>
            </Fragment>
        </Router>
);

export default App;
