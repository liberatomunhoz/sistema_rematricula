package rematricula.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import rematricula.dao.UsuariosDao;
import rematricula.model.Usuarios;

@Controller
public class LoginController {
	@Inject
	UsuariosDao dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String telaLogin() {
			return "telaLogin";
	}

	@RequestMapping(value = "/validaLogin", method = RequestMethod.POST)
	public String validar(Usuarios usuario) {
		Usuarios usuarioExiste = dao.validaLogin(usuario.getLoginUsuario(), usuario.getSenhaUsuario());
		if (usuarioExiste != null) {
				return "index";		
		}
		return "telaLogin";	
	}
}
