package rematricula.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class AlunosDisciplinasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_ALUNOS_DISCIPLINAS = "INSERT INTO alunos_disciplinas (disciplina_cod, aluno_cod, status) VALUES (?, ?, 'Matriculado')";
	
	public void inserirAlunosDisciplinasInicial(int codigoDisciplina, int codigoAluno) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_ALUNOS_DISCIPLINAS,
				codigoDisciplina,
				codigoAluno
				);
	}
}
