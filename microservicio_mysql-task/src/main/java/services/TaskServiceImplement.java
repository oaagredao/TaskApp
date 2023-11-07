package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TaskDaoInterfaz;
import dao.UserDaoInterfaz;
import model.Task;
import model.User;

@Service
public class TaskServiceImplement implements taskService {

	@Autowired
	TaskDaoInterfaz taskDao;

	@Autowired
	UserDaoInterfaz userDao;

	@Override
	public String agregarTask(Task tarea) {
		// Recuperar el userId del objeto User en la tarea
		int userId = tarea.getUser().getUserId();

		// Buscar al usuario real en la base de datos
		User user = userDao.recuperarUsuario(userId);

		if (user != null) {
			// Verificar si la tarea con el taskId ya existe en la base de datos
			Task existingTask = taskDao.recuperarTask(tarea.getTaskId());
			if (existingTask == null) {
				tarea.setUser(user); // Asociar la tarea al usuario encontrado
				taskDao.agregarTarea(tarea);
				return "Tarea agregada con éxito.";
			} else {
				return "La tarea ya existe en la base de datos.";
			}
		} else {
			return "No se encontró un usuario válido con el user_id proporcionado.";
		}
	}

	@Override
	public List<Task> recuperarTareas(int user_id) {
		return taskDao.devolverTasks(user_id);
	}

	@Override
	public void actualizarTarea(Task tarea) {
		if (taskDao.recuperarTask(tarea.getTaskId()) != null) {
			taskDao.actualizarTask(tarea);
		}
	}

	@Override
	public boolean eliminarTarea(int idTask) {
		if (taskDao.recuperarTask(idTask) != null) {
			taskDao.eliminarTask(idTask);
			return true;
		}
		return false;
	}

	@Override
	public Task buscarTarea(int idTask) {
		return taskDao.recuperarTask(idTask);
	}

}
