package rematricula.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import rematricula.model.Turmas;

@Component
public class ProfessoresTurmasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_PROFESSORES_TURMAS = "INSERT INTO professores_turmas (turma_cod, professor_cod) VALUES (?,?)";
	private static final String COMANDO_SQL_DELETE_PROFESSORES_TURMAS = "DELETE FROM professores_turmas WHERE cod_professor_turma = ?";
	
	public void inserirProfessoresTurmas(Turmas turma) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_PROFESSORES_TURMAS,
				turma.getCodigo(),
				turma.getCodigoProfessor()
				);	
	}
	
	public void deletaProfessoresTurmas(int idProfessorTurma) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_PROFESSORES_TURMAS,
				idProfessorTurma);
	}
}
