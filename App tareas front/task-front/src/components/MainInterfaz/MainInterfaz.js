import React, { useState, useEffect} from "react";
import "./MainInterfaz.css";

function MainInterfaz() {


  // estado de las tareas que se agregan a la base de datos
  // el json que se envia debe tener todos los campos esperados por task
  // incluyendo la seccion de user
  const [newTask, setNewTask] = useState({
    user: {
      userId: parseInt(localStorage.getItem("userId")),
      firstName: "",
      lastName: "",
      password: "",
      username: ""
    },
    title: "",
    taskDescription: "",
    dueDate: "",
    priority: "",
    estatus: ""
  });
  
    // Estado para las tareas obtenidas del servidor
    const [serverTasks, setServerTasks] = useState([]); 
  
    const handleInputChange = (e) => {
      const { name, value } = e.target;
      setNewTask({ ...newTask, [name]: value });
      console.log("Nuevo estado de newTask:", newTask);
    };
  
    // funcion que agrega las tareas
    const addTask = (e) => {
      fetch("http://localhost:8080/tareas", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newTask),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            console.log("Error, probablemente error del servidor o enviaste mal el JSON");
            throw new Error("Error en la solicitud");
          }
        })
        .then((data) => {
          console.log("ID de la tarea creada:", data);
          fetchTasks();
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    };
  
    const fetchTasks = () => {
      const userId = parseInt(localStorage.getItem("userId"));
      if (userId) {
        fetch(`http://localhost:8080/tareas/${userId}`)
          .then((response) => {
            if (response.ok) {
              return response.json();
            } else {
              console.log("Error al obtener tareas del servidor");
              throw new Error("Error en la solicitud");
            }
          })
          .then((data) => {
            setServerTasks(data); // Actualiza el estado con las tareas del servidor
            console.log("la data traida del servidor es: ",data);
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }
    };

    
  
    // Llamar a fetchTasks al cargar el componente para que se cargue al iniciar
    useEffect(() => {
      fetchTasks();
    }, []);
  
    const handleSubmit = (e) => {
      console.log("El valor de newTask es: ",newTask)
      console.log("La fecha que llega es: ",newTask.dueDate)
      e.preventDefault();

      console.log("Voy a mostrar el valor que llega al handle submit del user id", newTask.user)
      if (
        newTask.title &&
        newTask.taskDescription &&
        newTask.dueDate &&
        newTask.priority &&
        newTask.user&&
        newTask.estatus
      ) {
        
        // hago el fetch y envio la tarea a la base de datos
        addTask();

        // dejar el almacenamiento listo para una nueva tarea
        setNewTask({
          user: {
            userId: parseInt(localStorage.getItem("userId")),
            firstName: "",
            lastName: "",
            password: "",
            username: ""
          },
          title: "",
          taskDescription: "",
          dueDate: "",
          priority: "",
          estatus: ""
        });

      } else {
        alert("Debe llenarse completamente el formulario");
      }
    };

    // definición de colores para los div de las task traidas del servidor
    const getTaskColor = (priority) => {
        console.log("La prioridad es: ", priority)
        // Esta función devuelve el color según la prioridad
        switch (priority) {
            case "Alta":
              return "linear-gradient(to right, #FF6B6B, #FF6B6B)"; // Rojo claro
            case "Media":
              return "linear-gradient(to right, #FFD166, #FFD166)"; // Amarillo claro
            case "Baja":
              return "linear-gradient(to right, #88FCA3, #88FCA3)"; // Verde claro
            default:
              return "linear-gradient(to right, #FF6BBD, #FF6BBD)"; // Gradiente rosa (default)
          }
    };

  return (
    <div className="main-interface">
      <div className="task-form">
        <h1>Agregar Nueva Tarea</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            placeholder="Título"
            value={newTask.title}
            onChange={handleInputChange}
          />
          <textarea
            name="taskDescription"
            placeholder="Descripción de la tarea"
            value={newTask.taskDescription}
            onChange={handleInputChange}
          />
          <input
            type="date"
            name="dueDate"
            value={newTask.dueDate}
            onChange={handleInputChange}
          />
          <select
            name="priority"
            value={newTask.priority}
            onChange={handleInputChange}
          >
            <option value="">Selecciona Prioridad</option>
            <option value="Alta">Alta</option>
            <option value="Media">Media</option>
            <option value="Baja">Baja</option>
          </select>
          <select
            name="estatus"
            value={newTask.estatus}
            onChange={handleInputChange}
          >
            <option value="">Selecciona Estatus</option>
            <option value="Pendiente">Pendiente</option>
            <option value="Completada">Completada</option>
            <option value="En Progreso">En Progreso</option>
          </select>
          <button type="submit">Agregar Tarea</button>
        </form>
      </div>

      {/*renderizacion de las tareas traidas del servidor*/}
      <div className="task-list">
        <h1>Lista de Tareas (Servidor)</h1>
        <ul>
          {serverTasks.map((task, index) => (
            <li key={index} className="task-item" style={{ backgroundImage: getTaskColor(task.priority) }}>
              <div>
                <strong>Título:</strong> {task.title}
              </div>
              <div>
                <strong>Descripción:</strong> {task.taskDescription}
              </div>
              <div>
                <strong>Fecha de Vencimiento:</strong> {task.dueDate}
              </div>
              <div>
                <strong>Prioridad:</strong> {task.priority}
              </div>
              <div>
                <strong>Estatus:</strong> {task.estatus}
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default MainInterfaz;
