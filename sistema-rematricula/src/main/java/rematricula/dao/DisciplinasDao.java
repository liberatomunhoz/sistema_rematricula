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
	
	private static final String COMANDO_SQL_INSERT_DISCIPLINAS = "INSERT INTO disciplinas (curso_cod, nome_disc, disc_pre, semestre) VALUES (?, ?, ?, ?)";
	private static final String COMANDO_SQL_SELECT_DISCIPLINAS = "SELECT d.cod_disc, c.nome_curso, d.nome_disc, d.semestre, d.disc_pre"
	+ " FROM disciplinas AS d"
	+ " INNER JOIN cursos AS c ON c.cod_curso = d.curso_cod"
	+ " WHERE d.semestre >= 1"
	+ " ORDER BY d.semestre";
	private static final String COMANDO_SQL_SELECT_PREREQUISITO = "SELECT d.cod_disc, d.nome_disc"
			+ " FROM disciplinas AS d"
			+ " INNER JOIN cursos AS c ON c.cod_curso = d.curso_cod"
			+ " WHERE c.cod_curso = ?";
	private static final String COMANDO_SQL_SELECT_DISCIPLINA_INICIAL = "SELECT d.cod_disc, d.nome_disc"
			+ " FROM disciplinas AS d"
			+ " INNER JOIN cursos AS c ON c.cod_curso = d.curso_cod"
			+ " WHERE d.semestre = 1"
			+ " AND c.cod_curso = ?";
	private static final String COMANDO_SQL_DELETE_DISCIPLINAS = " DELETE FROM disciplinas WHERE cod_disc = ?";
	private static final String COMANDO_SQL_SELECT_PREREQUISITO_DISCIPLINA = "SELECT d.nome_disc"
			+ " FROM disciplinas AS d"
			+ " WHERE d.cod_disc = ?";
	
	
	public void inserirDisciplinas(Disciplinas disciplina) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_DISCIPLINAS,
				disciplina.getCodCurso(),
				disciplina.getNomeDisciplina(),
				disciplina.getPreRequisito(),
				disciplina.getSemestre()
				);
	}
	
	public List<Disciplinas> consultaDisciplinas() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_DISCIPLINAS,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setCodigo(rs.getInt("d.cod_disc"));
								disciplina.setNomeCurso(rs.getString("c.nome_curso"));
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								disciplina.setSemestre(rs.getInt("d.semestre"));
								disciplina.setPreRequisito(rs.getInt("d.disc_pre"));
								return disciplina;
							}
						});
	}
	
	public List<Disciplinas> consultaPreRequisito(int codigoCurso) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_PREREQUISITO,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setCodigo(rs.getInt("d.cod_disc"));
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								return disciplina;
							}
						}, codigoCurso);
	}
	
	public List<Disciplinas> consultaPreRequisitoDisciplina(int codigoPreRequisito) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_PREREQUISITO_DISCIPLINA,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								return disciplina;
							}
						}, codigoPreRequisito);
	}
	
	public List<Disciplinas> consultaDisciplinaInicial(int codigoCurso) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_DISCIPLINA_INICIAL,
						new RowMapper<Disciplinas>() {
							public Disciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								Disciplinas disciplina = new Disciplinas();
								disciplina.setCodigo(rs.getInt("d.cod_disc"));
								disciplina.setNomeDisciplina(rs.getString("d.nome_disc"));
								return disciplina;
							}
						}, codigoCurso);
	}
	
	public void deletaDisciplina(int disciplina) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_DISCIPLINAS,
				disciplina);
	}

}
