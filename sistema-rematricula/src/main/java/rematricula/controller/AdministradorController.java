package rematricula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdministradorController {

	@RequestMapping(value = "/cadastrar/curso", method = RequestMethod.GET)
	public String cadastroDeCurso() {
		return "cadastroCurso";
	}
	
	@RequestMapping(value = "/cadastrar/disciplina", method = RequestMethod.GET)
	public String cadastroDeDisciplina() {
		return "cadastroDisciplina";
	}
	
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
	
	@RequestMapping(value = "/listar/curso", method = RequestMethod.GET)
	public String listarCurso() {
		return "listarCurso";
	}
}
