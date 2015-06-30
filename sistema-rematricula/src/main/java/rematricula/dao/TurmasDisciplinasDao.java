package rematricula.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import rematricula.model.Turmas;

@Component
public class TurmasDisciplinasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_TURMAS_DISCIPLINAS = "INSERT INTO turmas_disciplinas (turma_cod, disc_cod) VALUES (?,?)";
	private static final String COMANDO_SQL_DELETE_TURMAS_DISCIPLINAS = "DELETE FROM turmas_disciplinas WHERE cod_turmas_disciplinas = ?";
	
	public void inserirTurmasDisciplinas(Turmas turma) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_TURMAS_DISCIPLINAS,
				turma.getCodigo(),
				turma.getCodigoDisciplina()
				);	
	}
	
	public void deletaTurmasDisciplinas(int idTurmaDisciplina) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_TURMAS_DISCIPLINAS,
				idTurmaDisciplina);
	}
}
