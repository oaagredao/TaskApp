package dao;

import java.util.List;

import model.User;

public interface UserDaoInterfaz {
	
	void agregarUsuario(User usuario);
	
	User recuperarUsuario(String username);
	
	void eliminarUsuario(String username);
	
	List<User> devolverUsuarios();
	
	void eliminarUsuario(int idUsuario);

	User recuperarUsuario(int idUsuario);
	
	void actualizarUsuario(User usuario);

}// interfaz
