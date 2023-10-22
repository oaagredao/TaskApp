package services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TaskDaoInterfaz;
import model.Task;

@Service
public class TaskServiceImplement implements taskService {
	
	@Autowired
	TaskDaoInterfaz taskDao;

	@Override
	public boolean agregarTask(Task tarea) {
		if (taskDao.recuperarTask(tarea.getTaskId())==null) {
			taskDao.agregarTarea(tarea);
			return true;
		}
		return false;
	}

	@Override
	public List<Task> recuperarTareas() {
		return taskDao.devolverTasks();
	}

	@Override
	public void actualizarTarea(Task tarea) {
		if (taskDao.recuperarTask(tarea.getTaskId())!=null) {
			taskDao.actualizarTask(tarea);
		}
	}

	@Override
	public boolean eliminarTarea(int idTask) {
		if (taskDao.recuperarTask(idTask)!=null) {
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
