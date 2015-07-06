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
import rematricula.model.AlunosDisciplinas;
import rematricula.model.Turmas;
import rematricula.model.Usuarios;

@Controller
public class ProfessorController {
	
	@Inject
	UsuariosDao usuarioDao;
	
	@Inject
	TurmasDao turmaDao;
	
	@Inject
	AlunosDisciplinasDao alunoDisciplinaDao;

	@RequestMapping(value = "/listar/professor-turma", method = RequestMethod.GET)
	public String listarProfessorTurma(HttpSession session, Model model, int codigoUsuarioProfessor) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		Usuarios codigoProfessorRecebido = usuarioDao.pegaCodigoProfessor(codigoUsuarioProfessor);
		model.addAttribute("turmaEDisciplina", turmaDao.consultaTurmaEDisciplinaPeloCodigoProfessor(codigoProfessorRecebido.getCodigo()));
		return "listarTurmasProfessor";
	}
	
	@RequestMapping(value = "/listar/turma-alunos", method = RequestMethod.GET)
	public String listarTurmaAlunos(HttpSession session, Model model, Turmas turma) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		model.addAttribute("alunos", turmaDao.consultaAlunoProfessorDisciplinaNumeroTurma(turma.getCodigoProfessor(),
																						  turma.getCodigoDisciplina(),
																						  turma.getNumeroTurma()));
		return "cadastroNotaAluno";
	}
	
	@RequestMapping(value = "/cadastrar/nota-aluno", method = RequestMethod.POST)
	public String cadastrarNotaAluno(HttpSession session, Model model, AlunosDisciplinas aluno) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		alunoDisciplinaDao.inserirNotaAluno(aluno.getNotaAluno(), aluno.getCodigoDisciplina(), aluno.getCodigo());
		return "redirect:/listar/turma-alunos";
	}
	
	@RequestMapping(value = "/listar/nota-aluno", method = RequestMethod.GET)
	public String listarNotaAluno(HttpSession session, Model model, Turmas turma) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		model.addAttribute("alunos", turmaDao.consultaAlunoNota(turma.getCodigoProfessor(), turma.getCodigoDisciplina(), turma.getNumeroTurma()));
		return "listarTurmaAlunos";
	}
}
