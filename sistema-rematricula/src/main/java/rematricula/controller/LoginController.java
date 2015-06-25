package rematricula.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;






import rematricula.dao.UsuariosDao;
import rematricula.model.NivelUsuario;
import rematricula.model.Usuarios;

@Controller
public class LoginController {
	@Inject
	UsuariosDao dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String telaLogin(Model model) {
			model.addAttribute("mensagem", "");
			return "telaLogin";
	}

	@RequestMapping(value = "/loginValida", method = RequestMethod.POST)
	public String validar(HttpSession session, Usuarios usuario, Model model) {
		Usuarios usuarioExiste = dao.validaLogin(usuario.getLoginUsuario(), usuario.getSenhaUsuario());
		if (usuarioExiste != null) {
			session.setAttribute("usuario logado", usuarioExiste);
			if (usuarioExiste.getNivelUsuario() == NivelUsuario.ADMINISTRADOR){
				//model.addAttribute("botaoCadastrar", true);
				return "index";
			}
			else if (usuarioExiste.getNivelUsuario() == NivelUsuario.PROFESSOR) {
				//model.addAttribute("botaoCadastrar", false);
				return "indexProfessor";
			} else {
				//model.addAttribute("botaoCadastrar", false);
				return "indexAluno";
			}
		} else {
			model.addAttribute("mensagem", "Usuário ou senha inválida");
			return "telaLogin";
		}		
	}
}
