package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Estados;

@Component
public class EstadosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private final String COMANDO_SQL_SELECT_ESTADO = "SELECT cod_estado, uf FROM estados";
	
	public List<Estados> consultaEstados() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_ESTADO,
						new RowMapper<Estados>() {
							public Estados mapRow(ResultSet rs, int arg1) throws SQLException {
								Estados estado = new Estados();
								estado.setCodigo(rs.getInt("cod_estado"));
								estado.setUf(rs.getString("uf"));
								return estado;
							}
						});
	}
}
