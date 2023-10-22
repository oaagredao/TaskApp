package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dao.UserDaoInterfaz;
import model.User;

@Service
public class UserServiceImplement implements userService {
	@Autowired
	UserDaoInterfaz userDao;
	

	@Override
	public boolean agregarUsuario(User usuario) {
		if (userDao.recuperarUsuario(usuario.getUserId())==null) {
			userDao.agregarUsuario(usuario);
			return true;
		}
		return false;
	}

	@Override
	public List<User> recuperarUsuarios() {
		return userDao.devolverUsuarios();
	}

	@Override
	public void actualizarUsuario(User usuario) {
		if (userDao.recuperarUsuario(usuario.getUserId())!=null) {
			userDao.actualizarUsuario(usuario);
		}
	}

	@Override
	public boolean eliminarUsuario(int idUsuario) {
		if (userDao.recuperarUsuario(idUsuario)!= null) {
			userDao.eliminarUsuario(idUsuario);
			return true;
		}
		return false;
	}

	@Override
	public User buscarUsuario(int idUsuario) {
			return userDao.recuperarUsuario(idUsuario);
	}

}
