package rematricula.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rematricula.dao.AlunosDisciplinasDao;
import rematricula.dao.TurmasDao;
import rematricula.dao.UsuariosDao;
import rematricula.model.NivelUsuario;
import rematricula.model.Usuarios;

@Controller
public class LoginController {
	@Inject
	UsuariosDao usuarioDao;
	
	@Inject 
	TurmasDao turmaDao;
	
	@Inject
	AlunosDisciplinasDao alunoDisciplinaDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String telaLogin(Model model) {
			model.addAttribute("mensagem", "");
			return "telaLogin";
	}

	@RequestMapping(value = "/loginValida", method = RequestMethod.POST)
	public String validar(HttpSession session, Usuarios usuario, Model model) {
		Usuarios usuarioExiste = usuarioDao.validaLogin(usuario.getLoginUsuario(), usuario.getSenhaUsuario());
		if (usuarioExiste != null) {
			session.setAttribute("usuario logado", usuarioExiste);
			if (usuarioExiste.getNivelUsuario() == NivelUsuario.ADMINISTRADOR){
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
				model.addAttribute("menuAdmin", true);
				model.addAttribute("menuProfessor", false);
				model.addAttribute("menuAluno", false);
				return "index";
			}
			else if (usuarioExiste.getNivelUsuario() == NivelUsuario.PROFESSOR) {
				Usuarios codigoProfessorRecebido = usuarioDao.pegaCodigoProfessor(usuarioExiste.getCodigo());
				model.addAttribute("turmaEDisciplina", turmaDao.consultaTurmaEDisciplinaPeloCodigoProfessor(codigoProfessorRecebido.getCodigo()));
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
				return "listarTurmasProfessor";
			} else {
				Usuarios codigoAlunoRecebido = usuarioDao.pegaCodigoAluno(usuarioExiste.getCodigo());
				model.addAttribute("alunos", alunoDisciplinaDao.consultaGradeCurricular(codigoAlunoRecebido.getCodigo()));
				model.addAttribute("codigoESemestre", alunoDisciplinaDao.consultaCodigoSemestreAluno(codigoAlunoRecebido.getCodigo()));
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
				model.addAttribute("menuAdmin", false);
				model.addAttribute("menuProfessor", false);
				model.addAttribute("menuAluno", true);
				return "apresentacaoAluno";
			}
		} else {
			model.addAttribute("mensagem", "Usuário ou senha inválida");
			return "telaLogin";
		}		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/";
	}
}
