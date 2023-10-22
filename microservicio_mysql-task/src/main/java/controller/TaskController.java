package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Task;
import services.taskService;

@RestController
public class TaskController {
	
	//inyectamos la interfaz con los metodos del service
	@Autowired
	taskService tService;
	
	// solicitudes get
	@GetMapping(value = "tareas",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> recuperarTareas(){
		return tService.recuperarTareas();
	}
	
	@GetMapping(value = "tareas/{idTask}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Task buscarTarea(@PathVariable("idTask") int idTask) {
		return tService.buscarTarea(idTask);
	}
	
	//solicitudes post
	@PostMapping(value = "tareas",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public String agregarTask(@RequestBody Task tarea) {
		return String.valueOf(tService.agregarTask(tarea));
	}
	
	// solicitud update
	@PutMapping(value = "tareas",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarTarea(@RequestBody Task tarea) {
		tService.actualizarTarea(tarea);
	}
	
	// solicitud delete
	@DeleteMapping(value = "tareas/{idTask}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarTarea(@PathVariable int idTask) {
		return String.valueOf(tService.eliminarTarea(idTask));
	}
	
	
	
}// Controller
