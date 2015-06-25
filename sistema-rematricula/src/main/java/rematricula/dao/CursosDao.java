package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Cursos;


@Component
public class CursosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private final String COMANDO_SQL_INSERT_CURSOS = "INSERT INTO cursos (nome_curso) VALUES (?)";
	private final String COMANDO_SQL_SELECT_CURSOS = "SELECT cod_curso, nome_curso FROM cursos";
	
	public void inserirCurso(Cursos curso) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_CURSOS,
				curso.getNomeCurso()
				);
	}
	
	public List<Cursos> consultaCursos() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_CURSOS,
						new RowMapper<Cursos>() {
							public Cursos mapRow(ResultSet rs, int arg1) throws SQLException {
								Cursos curso = new Cursos();
								curso.setCodigo(rs.getInt("cod_curso"));
								curso.setNomeCurso(rs.getString("nome_curso"));
								return curso;
							}
						});
	}
}
