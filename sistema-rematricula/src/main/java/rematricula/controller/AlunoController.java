package rematricula.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rematricula.dao.AlunosDisciplinasDao;
import rematricula.dao.UsuariosDao;
import rematricula.model.Alunos;

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
	public String alunoRematricula(HttpSession session, Model model) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");
		model.addAttribute("rematricula", alunoDisciplinaDao.consultaDisciplinaRematricula(alunoRecebido.getCodigoLoginAluno()));
		model.addAttribute("disciplinaReprovada", alunoDisciplinaDao.consultaDisciplinaReprovadaRematricula(alunoRecebido.getCodigoLoginAluno()));
		return "listarRematriculaAluno";
	}
	
	@RequestMapping(value = "/aluno/grade-curricular", method = RequestMethod.GET)
	public String alunoGradeCurricular(HttpSession session, Model model) {
		model.addAttribute("loginUsuario", session.getAttribute("usuario logado"));
		Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");		
		model.addAttribute("alunos", alunoDisciplinaDao.consultaGradeCurricular(alunoRecebido.getCodigoLoginAluno()));
		return "gradeCurricularAluno";
	}
	
	@RequestMapping(value = "/aluno/cadastrar-rematricula", method = RequestMethod.GET)
	public String inserirDisciplinaRematricula(HttpSession session, Model model, int codigoDisciplina) {
			Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");
			alunoDisciplinaDao.inserirAlunosDisciplinasInicial(codigoDisciplina, alunoRecebido.getCodigoLoginAluno());
			return "redirect:/aluno/grade-curricular";
	}
	
	@RequestMapping(value = "/aluno/cadastrar-rematricula-reprovada", method = RequestMethod.GET)
	public String inserirDisciplinaRematriculaReprovada(HttpSession session, Model model, int codigoDisciplina) {
			Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");
			alunoDisciplinaDao.trocaStatusDisciplinaReprovada(alunoRecebido.getCodigoLoginAluno());
			return "redirect:/aluno/grade-curricular";
	}
	
	@RequestMapping(value = "/aluno/troca-status", method = RequestMethod.GET)
	public String trocarStatusDisciplinaRematricula(HttpSession session, Model model) {
			Alunos alunoRecebido = (Alunos) session.getAttribute("usuario logado");
			alunoDisciplinaDao.trocaStatusMatricula(alunoRecebido.getCodigoLoginAluno());
			return "redirect:/aluno/grade-curricular";
	}
	
	
}
