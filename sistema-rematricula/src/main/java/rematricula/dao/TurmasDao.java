package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Turmas;

@Component
public class TurmasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_TURMAS = "INSERT INTO turmas (numero_turma) VALUES (?)";
	private static final String COMANDO_SQL_SELECT_TURMAS = "SELECT pt.cod_professor_turma, td.cod_turmas_disciplinas, t.cod_turma, p.nome_completo, t.numero_turma, d.nome_disc"
																	  + " FROM turmas AS t"
																	  + " INNER JOIN professores_turmas AS pt ON t.cod_turma = pt.turma_cod"
																	  + " INNER JOIN professores AS p ON p.cod_professor = pt.professor_cod"
																	  + " INNER JOIN turmas_disciplinas AS td ON t.cod_turma = td.turma_cod"
																	  + " INNER JOIN disciplinas AS d ON d.cod_disc = td.disc_cod";
	private static final String COMANDO_SQL_DELETE_TURMAS = "DELETE FROM turmas WHERE cod_turma = ?";
	
	public void inserirTurmas(Turmas turma) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_TURMAS,
				turma.getNumeroTurma()
				);
		
		Integer idTurmaGerada = jdbcTemplate.queryForObject(
				"SELECT LAST_INSERT_ID()", Integer.class);
		turma.setCodigo(idTurmaGerada);
	}
	
	public List<Turmas> consultaTurmas() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_TURMAS,
						new RowMapper<Turmas>() {
							public Turmas mapRow(ResultSet rs, int arg1) throws SQLException {
								Turmas turma = new Turmas();
								turma.setCodigo(rs.getInt("t.cod_turma"));
								turma.setCodigoProfessorTurma(rs.getInt("pt.cod_professor_turma"));
								turma.setCodigoTurmaDisciplina(rs.getInt("td.cod_turmas_disciplinas"));
								turma.setNomeCompleto(rs.getString("p.nome_completo"));
								turma.setNumeroTurma(rs.getInt("t.numero_turma"));
								turma.setNomeDisciplina(rs.getString("d.nome_disc"));
								return turma;
							}
						});
	}
	
	public void deletaTurmas(int idTurma) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_TURMAS,
				idTurma);
	}
}
