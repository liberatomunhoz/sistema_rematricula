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
	private static final String COMANDO_SQL_SELECT_ALUNOS = "SELECT cod_aluno, cod_usuario, nome_completo FROM alunos";
	private static final String COMANDO_SQL_SELECT_ALUNO_DETALHE = "SELECT nome_completo, cpf, data_nasc, email, nome_pai, nome_mae, matricula, c.nome_cidade, u.senha"
														  + " FROM alunos AS a"
														  + " INNER JOIN cidades AS c ON c.cod_cidade = a.cidade_cod"
														  + " INNER JOIN usuarios AS u ON a.cod_usuario = u.cod_usuario"
														  + " WHERE a.cod_aluno = ?";
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
								aluno.setCodigo(rs.getInt("cod_aluno"));
								aluno.setCodigoLoginAluno(rs.getInt("cod_usuario"));
								aluno.setNomeCompleto(rs.getString("nome_completo"));
								return aluno;
							}
						});
	}

	public List<Alunos> consultaAlunoDetalhe(int codigoAluno) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_ALUNO_DETALHE,
						new RowMapper<Alunos>() {
							public Alunos mapRow(ResultSet rs, int arg1) throws SQLException {
								Alunos aluno = new Alunos();
								aluno.setNomeCompleto(rs.getString("nome_completo"));
								aluno.setCpf(rs.getInt("cpf"));
								aluno.setDataNascimento(rs.getDate("data_nasc"));
								aluno.setEmail(rs.getString("email"));
								aluno.setNomePaiAluno(rs.getString("nome_pai"));
								aluno.setNomeMaeAluno(rs.getString("nome_mae"));
								aluno.setMatriculaAluno(rs.getInt("matricula"));
								aluno.setNomeCidade(rs.getString("c.nome_cidade"));
								aluno.setSenhaUsuario(rs.getString("u.senha"));
								return aluno;
							}
						}, codigoAluno);
	}
}
