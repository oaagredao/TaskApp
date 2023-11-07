package controller;

import java.util.List;

// anotaciones del logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.UserDaoInterfaz;
import model.Task;
import services.taskService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

	// logger
	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	// inyectamos la interfaz con los metodos del service
	@Autowired
	taskService tService;
	@Autowired
	UserDaoInterfaz userDao;

	// solicitudes get
	@GetMapping(value = "tareas/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> recuperarTareas(@PathVariable("user_id") int user_id) {
		return tService.recuperarTareas(user_id);
	}

	@GetMapping(value = "tarea/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Task buscarTarea(@PathVariable("idTask") int idTask) {
		return tService.buscarTarea(idTask);
	}

	// solicitudes post
	// verificar cual es el Task tarea que llega
	@PostMapping(value = "tareas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String agregarTask(@RequestBody Task tarea) {
		logger.info("Recibida una nueva tarea: {}", tarea.toString());

		return tService.agregarTask(tarea);
	}

	/*
	 * @PostMapping(value = "tareas",consumes =
	 * MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	 * public String agregarTask(@RequestBody Task tarea) { return
	 * String.valueOf(tService.agregarTask(tarea)); }
	 */

	// solicitud update
	@PutMapping(value = "tareas", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarTarea(@RequestBody Task tarea) {
		tService.actualizarTarea(tarea);
	}

	// solicitud delete
	@DeleteMapping(value = "tareas/{idTask}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarTarea(@PathVariable int idTask) {
		return String.valueOf(tService.eliminarTarea(idTask));
	}

}// Controller
