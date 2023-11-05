import React, { useState } from 'react';
import "./MainInterfaz.css"

function MainInterfaz() {
    const [newTask, setNewTask] = useState({
        title: "",
        task_description: "",
        due_date: "",
        priority: "",
        estatus: ""
    });

    const [tasks, setTasks] = useState([]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewTask({ ...newTask, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (newTask.title && newTask.task_description && newTask.due_date && newTask.priority && newTask.estatus) {
            setTasks([...tasks, newTask]);
            setNewTask({
                title: "",
                task_description: "",
                due_date: "",
                priority: "",
                estatus: ""
            });

            //debe enviarse la información a la base de datos
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
                        name="task_description"
                        placeholder="Descripción de la tarea"
                        value={newTask.task_description}
                        onChange={handleInputChange}
                    />
                    <input
                        type="date"
                        name="due_date"
                        value={newTask.due_date}
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
            <div className="task-list">
                <h1>Lista de Tareas</h1>
                <ul>
                    {tasks.map((task, index) => (
                        <li key={index} className="task-item">
                            {/* Contenido de la tarea */}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default MainInterfaz;