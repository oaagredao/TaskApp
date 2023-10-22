package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import services.userService;

@RestController
public class UserController {
	
	@Autowired
	userService userService;	
	
	// metodos get
	@GetMapping(value = "usuarios",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> recuperarUsuarios(){
		return userService.recuperarUsuarios();
	}
	
	@GetMapping(value = "usuarios/{idUsuario}",produces = MediaType.APPLICATION_JSON_VALUE)
	public User buscarUsuario(@PathVariable("idUsuario")  int idUsuario) {
		return userService.buscarUsuario(idUsuario);
	}
	
	// peticiones post
	@PostMapping(value = "usuarios",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public String agregarUsuario(@RequestBody User usuario) {
		return String.valueOf(userService.agregarUsuario(usuario));
	}
	
	//peticion update
	@DeleteMapping(value = "usuarios/{idUsuario}",produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarUsuario(@PathVariable ("idUsuario") int idUsuario) {
		return String.valueOf(userService.eliminarUsuario(idUsuario));
	}
	
	

}// controller
