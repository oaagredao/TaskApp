import React, { useState } from "react";
import "./Registro.css";
import {  useNavigate } from 'react-router-dom';

function Registro(props) {
  // navegador de rutas
  const navigate = useNavigate();

  const minLength = 8;
  const maxLength = 20;
  const [user, setUser] = useState({
    email: "",
    password: "",
    username: "",
    firstName: "",
    lastName: "",
  });

  const validate = (field, regex) => {
    return regex.test(user[field]);
  };

  const handleChange = (field, value) => {
    setUser({
      ...user,
      [field]: value,
    });
  };

  // funcion que crea usuario y envia en solicitud post a base de datos
  const addUserToDatabase = () => {
    // Construye el objeto de usuario con los datos del estado
    const newUser = {
      email: user.email,
      password: user.password,
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
    };

    // Realiza una solicitud POST para agregar el usuario
    fetch("http://localhost:8080/usuarios", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newUser),
    })
      .then((response) => response.text())
      .then((data) => {
        if (data === "true") {
          // Usuario agregado con éxito
          alert("Usuario agregado con éxito");

          // agregar un movimiento a la ventana de Loggin
          navigate('/login');

        } else {
          // Fallo al agregar el usuario
          alert("Error al agregar el usuario");
        }
      })
      .catch((error) => {
        console.error("Error en la solicitud: " + error);
      });
  };

  const handleValidation = () => {
    if (
      validate("email", /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/) &&
      validate(
        "password",
        new RegExp(
          `^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).{${minLength},${maxLength}}$`
        )
      ) &&
      validate("username", /^[A-Za-z0-9]+$/) &&
      user.firstName &&
      user.lastName
    ) {
      // accion tomada al validar
      console.log("Datos válidos:", user);
      // llamar a la funcion que crea el objeto y lo envia
      addUserToDatabase();
    } else {
      alert("Los datos proporcionados no cumplen los requisitos.");
    }
  };

  return (
    <div className="divGeneralLoggin">
      <h2>Iniciar sesión</h2>
      <form>
        <div className="field-container">
          <label>Nombre de usuario:</label>
          <input
            type="text"
            value={user.username}
            onChange={(e) => handleChange("username", e.target.value)}
          />
        </div>
        <div className="field-container">
          <label>Primer Nombre:</label>
          <input
            type="text"
            value={user.firstName}
            onChange={(e) => handleChange("firstName", e.target.value)}
          />
        </div>
        <div className="field-container">
          <label>Apellido:</label>
          <input
            type="text"
            value={user.lastName}
            onChange={(e) => handleChange("lastName", e.target.value)}
          />
        </div>
        <div className="field-container">
          <label>Correo electrónico:</label>
          <input
            type="email"
            value={user.email}
            onChange={(e) => handleChange("email", e.target.value)}
          />
        </div>
        <div className="field-container">
          <label>Contraseña:</label>
          <input
            type="password"
            value={user.password}
            onChange={(e) => handleChange("password", e.target.value)}
          />
        </div>
        <button type="button" onClick={handleValidation}>
          Iniciar sesión
        </button>
      </form>
    </div>
  );
}

export default Registro;
