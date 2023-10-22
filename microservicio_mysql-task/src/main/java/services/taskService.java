package services;

import java.util.List;



import model.Task;


public interface taskService {
	boolean agregarTask(Task tarea);
	List<Task> recuperarTareas();
	void actualizarTarea(Task tarea);
	boolean eliminarTarea(int idTask);
	Task buscarTarea(int idTask);

}
