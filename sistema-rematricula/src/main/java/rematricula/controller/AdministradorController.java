package rematricula.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rematricula.dao.CidadesDao;
import rematricula.dao.CursosDao;
import rematricula.dao.DisciplinasDao;
import rematricula.dao.EstadosDao;
import rematricula.dao.ProfessoresDao;
import rematricula.dao.ProfessoresTurmasDao;
import rematricula.dao.TurmasDao;
import rematricula.dao.TurmasDisciplinasDao;
import rematricula.dao.UsuariosDao;
import rematricula.model.Cidades;
import rematricula.model.Cursos;
import rematricula.model.Disciplinas;
import rematricula.model.Professores;
import rematricula.model.Turmas;

@Controller
public class AdministradorController {
	
	@Inject
	UsuariosDao usuarioDao;
	
	@Inject 
	CursosDao cursosDao;
	
	@Inject 
	DisciplinasDao disciplinaDao;
	
	@Inject 
	EstadosDao estadosDao;
	
	@Inject 
	CidadesDao cidadesDao;
	
	@Inject
	ProfessoresDao professorDao;
	
	@Inject
	TurmasDao turmaDao;

	@Inject
	TurmasDisciplinasDao turmaDisciplinaDao;
	
	@Inject
	ProfessoresTurmasDao professorTurmaDao;
	
    //CRUD CURSO	
	@RequestMapping(value = "/cadastrar/curso", method = RequestMethod.GET)
	public String cadastroDeCurso() {
		return "cadastroCurso";
	}
	
	@RequestMapping(value = "/inserir-curso", method = RequestMethod.POST)
	public String inserirCurso(Cursos curso) {
		cursosDao.inserirCurso(curso);
		return "redirect:/listar/curso";
	}
	
	@RequestMapping(value = "/listar/curso", method = RequestMethod.GET)
	public String listarCurso(Model model) {
		model.addAttribute("cursos", cursosDao.consultaCursos());
		return "listarCurso";
	}
	
	@RequestMapping(value = "/deletar-curso", method = RequestMethod.GET)
	public String deletarCurso(Model model, Cursos curso) {
		cursosDao.deletaCurso(curso.getCodigo());
		model.addAttribute("disciplinas", cursosDao.consultaCursos());
		return "listarDisciplina";
	}
	
	//CRUD DISCIPLINA
	@RequestMapping(value = "/cadastrar/disciplina", method = RequestMethod.GET)
	public String cadastroDeDisciplina(Model model) {
		model.addAttribute("cursos", cursosDao.consultaCursos());
		return "cadastroDisciplina";
	}
	
	@RequestMapping(value = "/inserir-disciplina", method = RequestMethod.POST)
	public String inserirDisciplina(Disciplinas disciplina) {
		disciplinaDao.inserirDisciplinas(disciplina);
		return "redirect:/listar/disciplina";
	}
	
	@RequestMapping(value = "/listar/disciplina", method = RequestMethod.GET)
	public String listarDisciplina(Model model) {
		model.addAttribute("disciplinas", disciplinaDao.consultaDisciplinas());
		return "listarDisciplina";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listar/pre-requisito", method = RequestMethod.POST)
	public List<Disciplinas> listarPreRequisito(Model model,  @RequestParam int codigoCurso) {
		return disciplinaDao.consultaPreRequisito(codigoCurso);
	}
	
	@RequestMapping(value = "/deletar-disciplina", method = RequestMethod.GET)
	public String deletarDisciplina(Model model, Disciplinas disciplina) {
		disciplinaDao.deletaDisciplina(disciplina.getCodigo());
		model.addAttribute("disciplinas", disciplinaDao.consultaDisciplinas());
		return "listarDisciplina";
	}
	
	//CRUD PROFESSOR
	@RequestMapping(value = "/cadastrar/professor", method = RequestMethod.GET)
	public String cadastroDeProfessor(Model model) {
		model.addAttribute("estados", estadosDao.consultaEstados());
		return "cadastroProfessor";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listar/cidade", method = RequestMethod.POST)
	public List<Cidades> listarCidade(Model model,  @RequestParam int codigoEstado) {
		return cidadesDao.consultaCidades(codigoEstado);
	}
	
	@RequestMapping(value = "/inserir-professor", method = RequestMethod.POST)
	public String inserirProfessor(Professores professor) {
		usuarioDao.inserirLoginProfessor(professor);		
		professorDao.inserirProfessor(professor);
		return "redirect:/listar/professor";
	}
	
	@RequestMapping(value = "/listar/professor", method = RequestMethod.GET)
	public String listarProfessor(Model model) {
		model.addAttribute("professores", professorDao.consultaProfessores());
		return "listarProfessor";
	}
	
	@RequestMapping(value = "/deletar-professor", method = RequestMethod.GET)
	public String deletarProfessor(Model model, Professores professor) {
		professorDao.deletaProfessor(professor.getCodigo());
		usuarioDao.deletaLoginProfessor(professor.getCodigoLoginProfessor());
		model.addAttribute("professores", professorDao.consultaProfessores());
		return "listarProfessor";
	}
	
	//CRUD ALUNO
	@RequestMapping(value = "/cadastrar/aluno", method = RequestMethod.GET)
	public String cadastroDeAluno() {
		return "cadastroAluno";
	}
	
	//CRUD TURMA
	@RequestMapping(value = "/cadastrar/turma", method = RequestMethod.GET)
	public String cadastroDeTurma(Model model) {
		model.addAttribute("disciplinas", disciplinaDao.consultaDisciplinas());
		model.addAttribute("professores", professorDao.consultaProfessores());
		return "cadastroTurma";
	}
	
	@RequestMapping(value = "/inserir-turma", method = RequestMethod.POST)
	public String inserirTurma(Turmas turma) {
		turmaDao.inserirTurmas(turma);
		turmaDisciplinaDao.inserirTurmasDisciplinas(turma);
		professorTurmaDao.inserirProfessoresTurmas(turma);
		return "redirect:/listar/turma";
	}
	
	@RequestMapping(value = "/listar/turma", method = RequestMethod.GET)
	public String listarProfessoresTurmas(Model model) {
		model.addAttribute("turmas", turmaDao.consultaTurmas());
		return "listarTurma";
	}
	
	@RequestMapping(value = "/deletar-turma", method = RequestMethod.GET)
	public String deletarProfessor(Model model, Turmas turma) {
		professorTurmaDao.deletaProfessoresTurmas(turma.getCodigoProfessorTurma());
		turmaDisciplinaDao.deletaTurmasDisciplinas(turma.getCodigoTurmaDisciplina());
		turmaDao.deletaTurmas(turma.getCodigo());
		model.addAttribute("turmas", turmaDao.consultaTurmas());
		return "listarTurma";
	}
}
