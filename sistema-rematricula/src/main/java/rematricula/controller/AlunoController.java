package rematricula.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rematricula.dao.AlunosDisciplinasDao;
import rematricula.dao.UsuariosDao;
import rematricula.model.Usuarios;

@Controller
public class AlunoController {
	
	@Inject
	AlunosDisciplinasDao alunoDisciplinaDao;
	
	@Inject
	UsuariosDao usuarioDao;
	
	@RequestMapping(value = "/aluno/apresentacao", method = RequestMethod.GET)
	public String alunoApresentacao(HttpSession session, Model model) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		return "apresentacaoAluno";
	}
	
	@RequestMapping(value = "/aluno/rematricula", method = RequestMethod.GET)
	public String alunoRematricula(HttpSession session, Model model, int codigoUsuarioAluno, int semestre) {
		model.addAttribute("rematricula", alunoDisciplinaDao.consultaDisciplinaRematricula(codigoUsuarioAluno, semestre));
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		model.addAttribute("disciplinaReprovada", alunoDisciplinaDao.consultaDisciplinaReprovadaRematricula(codigoUsuarioAluno, semestre));
		return "listarRematriculaAluno";
	}
	
	@RequestMapping(value = "/aluno/grade-curricular", method = RequestMethod.GET)
	public String alunoGradeCurricular(HttpSession session, Model model, int codigoUsuarioAluno) {
		Usuarios codigoAlunoRecebido = usuarioDao.pegaCodigoAluno(codigoUsuarioAluno);
		model.addAttribute("alunos", alunoDisciplinaDao.consultaGradeCurricular(codigoAlunoRecebido.getCodigo()));
		model.addAttribute("codigoESemestre", alunoDisciplinaDao.consultaCodigoSemestreAluno(codigoAlunoRecebido.getCodigo()));
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		return "gradeCurricularAluno";
	}
	
	@ResponseBody
	@RequestMapping(value = "/aluno/cadastrar-rematricula", method = RequestMethod.GET)
	public String inserirDisciplinaRematricula(HttpSession session, Model model, @RequestParam int codigoUsuarioAluno, @RequestParam int codigoDisciplina) {
			model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
			Usuarios codigoAlunoRecebido = usuarioDao.pegaCodigoAluno(codigoUsuarioAluno);
			model.addAttribute("codigoESemestre", alunoDisciplinaDao.consultaCodigoSemestreAluno(codigoAlunoRecebido.getCodigo()));
			alunoDisciplinaDao.inserirAlunosDisciplinasInicial(codigoDisciplina, codigoAlunoRecebido.getCodigo());
			return "redirect:/aluno/rematricula";
	}
}
