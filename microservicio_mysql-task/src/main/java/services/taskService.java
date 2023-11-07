package services;

import java.util.List;

import model.Task;

public interface taskService {
	String agregarTask(Task tarea);

	List<Task> recuperarTareas(int user_id);

	void actualizarTarea(Task tarea);

	boolean eliminarTarea(int idTask);

	Task buscarTarea(int idTask);

}
