package rematricula.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rematricula.dao.UsuariosDao;

@Controller
public class MenuController {
	@Inject
	UsuariosDao dao;

	@RequestMapping(value = "/cadastrarCurso", method = RequestMethod.GET)
	public String cadastroDeCurso() {
		return "cadastroCurso";
	}
	
	@RequestMapping(value = "/cadastrarDisciplina", method = RequestMethod.GET)
	public String cadastroDeDisciplina() {
		return "cadastroDisciplina";
	}
	
	@RequestMapping(value = "/cadastrarProfessor", method = RequestMethod.GET)
	public String cadastroDeProfessor() {
		return "cadastroProfessor";
	}
	
	@RequestMapping(value = "/cadastrarAluno", method = RequestMethod.GET)
	public String cadastroDeAluno() {
		return "cadastroAluno";
	}
	
	@RequestMapping(value = "/cadastrarTurma", method = RequestMethod.GET)
	public String cadastroDeTurma() {
		return "cadastroTurma";
	}
	
	@RequestMapping(value = "/listarCurso", method = RequestMethod.GET)
	public String listarCurso() {
		return "listarCurso";
	}
}
