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
import rematricula.model.Alunos;
import rematricula.model.NivelUsuario;
import rematricula.model.Professores;
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
			if (usuarioExiste.getNivelUsuario() == NivelUsuario.ADMINISTRADOR){
				session.setAttribute("usuario logado", usuarioExiste);
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
				return "redirect:/administrador-index";
			}
			else if (usuarioExiste.getNivelUsuario() == NivelUsuario.PROFESSOR) {
				Professores sessaoProfessor = usuarioDao.pegaDadosSessaoProfessor(usuarioExiste.getCodigo());
				session.setAttribute("usuario logado", sessaoProfessor);
				Professores professorRecebido = (Professores) session.getAttribute("usuario logado");
				model.addAttribute("turmaEDisciplina", turmaDao.consultaTurmaEDisciplinaPeloCodigoProfessor(professorRecebido.getCodigoLoginProfessor()));
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
				return "listarTurmasProfessor";
			} else {
				Alunos sessaoAluno = usuarioDao.pegaDadosSessaoAluno(usuarioExiste.getCodigo());
				session.setAttribute("usuario logado", sessaoAluno);
				Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");
				model.addAttribute("alunos", alunoDisciplinaDao.consultaGradeCurricular(alunoRecebido.getCodigo()));
				model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
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
