package dao;

import java.util.List;

import model.Task;


public interface TaskDaoInterfaz {
	
	void agregarTarea(Task tarea);
	
	Task recuperarTask(String titulo);
	
	void eliminarTask(String titulo);
	
	List<Task> devolverTasks();
	
	void eliminarTask(int idTask);

	Task recuperarTask(int idTask);
	
	void actualizarTask(Task task);

}// interfaz
