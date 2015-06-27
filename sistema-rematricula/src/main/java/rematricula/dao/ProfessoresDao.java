package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Professores;

@Component
public class ProfessoresDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_PROFESSORES = "INSERT INTO professores (nome_completo, cpf, data_nasc, email, cidade_cod, cod_usuario) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String COMANDO_SQL_SELECT_PROFESSORES = "SELECT p.cod_professor, p.nome_completo, p.data_nasc, p.email, p.cpf, u.login, u.senha, c.nome_cidade, p.cod_usuario"
																+ " FROM professores AS p"
																+ " INNER JOIN cidades AS c ON c.cod_cidade = p.cidade_cod"
																+ " INNER JOIN usuarios AS u ON p.cod_usuario = u.cod_usuario";
	private static final String COMANDO_SQL_DELETE_PROFESSORES = " DELETE FROM professores WHERE cod_professor = ?";
	
	public void inserirProfessor(Professores professor) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_PROFESSORES,
				professor.getNomeCompleto(),
				professor.getCpf(),
				professor.getDataNascimento(),
				professor.getEmail(),
				professor.getCodigoCidade(),
				professor.getCodigo()
				);
	}
	
	public List<Professores> consultaProfessores() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_PROFESSORES,
						new RowMapper<Professores>() {
							public Professores mapRow(ResultSet rs, int arg1) throws SQLException {
								Professores professor = new Professores();
								professor.setCodigo(rs.getInt("p.cod_professor"));
								professor.setNomeCompleto(rs.getString("p.nome_completo"));
								professor.setDataNascimento(rs.getDate("p.data_nasc"));
								professor.setEmail(rs.getString("p.email"));
								professor.setLoginUsuario(rs.getString("u.login"));
								professor.setSenhaUsuario(rs.getString("u.senha"));
								professor.setNomeCidade(rs.getString("c.nome_cidade"));
								professor.setCodigoLoginProfessor(rs.getInt("p.cod_usuario"));
								return professor;
							}
						});
	}
	
	public void deletaProfessor(int professor) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_PROFESSORES,
				professor);
	}
}
