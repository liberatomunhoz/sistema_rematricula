package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Alunos;

@Component
public class AlunosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_ALUNOS = "INSERT INTO alunos (nome_completo, cpf, data_nasc, email, nome_pai, nome_mae, matricula, cidade_cod, cod_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String COMANDO_SQL_SELECT_ALUNOS = "SELECT nome_completo FROM alunos";
	
	public void inserirAluno(Alunos aluno) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_ALUNOS,
				aluno.getNomeCompleto(),
				aluno.getCpf(),
				aluno.getDataNascimento(),
				aluno.getEmail(),
				aluno.getNomePaiAluno(),
				aluno.getNomeMaeAluno(),
				aluno.getLoginUsuario(),
				aluno.getCodigoCidade(),
				aluno.getCodigo()
				);
	}
	
	public List<Alunos> consultaAlunos() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_ALUNOS,
						new RowMapper<Alunos>() {
							public Alunos mapRow(ResultSet rs, int arg1) throws SQLException {
								Alunos aluno = new Alunos();
								aluno.setNomeCompleto(rs.getString("nome_completo"));
								return aluno;
							}
						});
	}
}
