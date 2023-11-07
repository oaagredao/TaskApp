package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Task;

@Repository
public class TaskDaoImplement implements TaskDaoInterfaz {

	@Autowired
	TaskJpaSpring taskSpring;

	@Override
	public void agregarTarea(Task tarea) {
		taskSpring.save(tarea);

	}

	@Override
	public Task recuperarTask(String titulo) {
		return taskSpring.findByTitle(titulo);
	}

	@Override
	public void eliminarTask(String titulo) {
		taskSpring.eliminarTask(titulo);

	}

	@Override
	public List<Task> devolverTasks(int user_id) {
		return taskSpring.findAllByUserid(user_id);
	}

	@Override
	public void eliminarTask(int idTask) {
		taskSpring.deleteById(idTask);

	}

	@Override
	public Task recuperarTask(int idTask) {
		return taskSpring.findById(idTask).orElse(null);
	}

	@Override
	public void actualizarTask(Task task) {
		taskSpring.save(task);

	}

}
