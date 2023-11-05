import React from 'react';
import "./Principal.css";
import { Link } from "react-router-dom";

function Principal(props) {
    return (
        <div className="principal-container">
            <h1 className="welcome-title">Bienvenido a mi aplicación de tareas</h1>
            <p className="description">Seleccione una de las siguientes opciones:</p>
            <ul className="options-list">
                <li>
                    <Link to="/login" className="btn login-btn">Iniciar Sesión</Link>
                </li>
                <li>
                    <Link to="/registro" className="btn register-btn">Crear una cuenta</Link>
                </li>
            </ul>
        </div>
    );
}

export default Principal;