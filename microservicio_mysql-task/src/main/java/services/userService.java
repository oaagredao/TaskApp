package services;

import java.util.List;



import model.User;


public interface userService {
	
	boolean agregarUsuario(User usuario);
	List<User> recuperarUsuarios();
	void actualizarUsuario(User usuario);
	boolean eliminarUsuario(int idUsuario);
	User buscarUsuario(int idUsuario);

}// class
