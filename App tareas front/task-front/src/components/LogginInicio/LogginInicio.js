import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import "./LogginInicio.css";

function LogginInicio(props) {

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [writeUsername, setWriteUsername] = useState("");
  const [writePassword, setWritePassword] = useState("");

  const handleInputChange = (field, value) => {
    if (field === "username") {
      setWriteUsername(value);
    } else if (field === "password") {
      setWritePassword(value);
    }
  };

  // funcion que envia la solicitud get
  const verifyLoggin = () => {
    // json que se envia en el cuerpo de la solicitud
    const data = {
      username: writeUsername,
      password: writePassword,
    };
    // se hace el fetch que hace la peticion http post
    fetch("http://localhost:8080/usuarioContrasena", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (response.ok) {

          console.log("Expongo la respuesta que llegó al primer then")
          console.log(response);
          console.log(response.text);


          return response.text(); // Maneja la respuesta como texto
        } else {
          throw new Error("Error en la solicitud");
        }
      })
      .then((data2) => {
        console.log("el valor de data2 es de: ", data2)
        if (data2 !== "Credenciales inválidas") {
          alert("Credenciales válidas");
          console.log("La verificación en la base de datos fue correcta");
          // se debe enviar el data2 que es el userid al localStorage
          localStorage.setItem('userId', data2);

          // Realiza acciones si las credenciales son válidas
          navigate('/main'); // Redirige a la interfaz principal


        } else {
          alert("Credenciales inválidas");
          console.log("La verificación en la base de datos fue incorrecta");
          // Realiza acciones si las credenciales son inválidas


        }
      });
  };

  const handleValidation = (e) => {
    e.preventDefault(); // Previene el reinicio de la página

    // verificacion
    console.log("Los valores de writeUsername y writePássword son:");
    console.log(writeUsername, writePassword);

    if (writeUsername && writePassword) {
      setUsername(writeUsername);
      setPassword(writePassword);

      console.log("Datos a verificar en la base de datos:", username, password);

      // Se llama a la función que hace el fetch
      verifyLoggin();

      console.log();
    } else {
      alert("Los datos proporcionados no cumplen los requisitos.");
    }
  };

  return (
    <div className="divGeneralLoggin">
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleValidation}>
        <div className="field-container">
          <label>Usuario:</label>
          <input
            type="text"
            value={writeUsername}
            onChange={(e) => handleInputChange("username", e.target.value)}
          />
        </div>
        <div className="field-container">
          <label>Contraseña:</label>
          <input
            type="password"
            value={writePassword}
            onChange={(e) => handleInputChange("password", e.target.value)}
          />
        </div>
        <button type="submit">Iniciar sesión</button>
      </form>
    </div>
  );
}

export default LogginInicio;
