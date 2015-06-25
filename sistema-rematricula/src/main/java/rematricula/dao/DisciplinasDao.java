package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Disciplinas;

@Component
public class DisciplinasDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private final String COMANDO_SQL_INSERT_DISCIPLINAS = "INSERT INTO disciplinas (nome_disc, disc_pre, curso_cod) VALUES (?, ?, ?)";
	private final String COMANDO_SQL_SELECT_DISCIPLINAS = "SELECT c.nome_curso, d.nome_disc, d.disc_pre"
	+ " FROM disciplinas AS d"
	+ " INNER JOIN cursos AS c ON c.cod_curso = d.curso_cod";
	private final String COMANDO_SQL_SELECT_PREREQUISITO = "SELECT d.cod_disc, d.nome_disc"
			+ " FROM disciplinas AS d"
			+ " INNER JOIN cursos AS c ON c.cod_curso = d.curso_cod"
			+ " WHERE c.cod_curso = ?";
	
	
	public void inserirDisciplinas(Disciplinas disciplina) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_DISCIPLINAS,
				disciplina.getNomeDisciplina(),
				disciplina.getPreRequisito(),
				disciplina.getCodCurso()
				);
	}
	
	public List<Disciplinas> consultaDisciplinas() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_DISCIPLINAS,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setNomeCurso(rs.getString("c.nome_curso"));
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								disciplina.setPreRequisito(rs.getInt("d.disc_pre"));
								return disciplina;
							}
						});
	}
	
	public List<Disciplinas> consultaPreRequisito() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_PREREQUISITO,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setCodigo(rs.getInt("d.cod_disc"));
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								return disciplina;
							}
						});
	}

}
