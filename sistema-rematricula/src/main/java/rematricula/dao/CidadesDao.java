package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Cidades;

@Component
public class CidadesDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
	private static final String COMANDO_SQL_SELECT_CIDADE = "SELECT cod_cidade, nome_cidade"
			+ " FROM cidades"
			+ " WHERE estado_cod = ?";
	
	public List<Cidades> consultaCidades(int codigoEstado) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_CIDADE,
						new RowMapper<Cidades>() {
							public Cidades mapRow(ResultSet rs, int arg1) throws SQLException {
								Cidades cidade = new Cidades();
								cidade.setCodigo(rs.getInt("cod_cidade"));
								cidade.setNomeCidade(rs.getString("nome_cidade"));
								return cidade;
							}
						}, codigoEstado);
	}
}
