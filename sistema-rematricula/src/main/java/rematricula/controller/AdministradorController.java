package rematricula.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rematricula.dao.CursosDao;
import rematricula.dao.DisciplinasDao;
import rematricula.model.Cursos;
import rematricula.model.Disciplinas;

@Controller
public class AdministradorController {
	
	@Inject 
	CursosDao cursosDao;
	
	@Inject 
	DisciplinasDao disciplinaDao;

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
	public String cadastroDeProfessor() {
		return "cadastroProfessor";
	}
	
	@RequestMapping(value = "/cadastrar/aluno", method = RequestMethod.GET)
	public String cadastroDeAluno() {
		return "cadastroAluno";
	}
	
	@RequestMapping(value = "/cadastrar/turma", method = RequestMethod.GET)
	public String cadastroDeTurma() {
		return "cadastroTurma";
	}
	
	
}
