package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Alunos;
import rematricula.model.NivelUsuario;
import rematricula.model.Professores;
import rematricula.model.Usuarios;

@Component
public class UsuariosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_VALIDA_LOGIN = "SELECT cod_usuario, login, nivel_usuario FROM usuarios WHERE login = ? AND senha = ?";
	private static final String COMANDO_SQL_INSERT_LOGIN_PROFESSOR = "INSERT INTO usuarios (login, senha, nivel_usuario) VALUES (?, ?, 'PROFESSOR')";
	private static final String COMANDO_SQL_INSERT_LOGIN_ALUNO = "INSERT INTO usuarios (login, senha, nivel_usuario) VALUES (?, ?, 'ALUNO')";
	private static final String COMANDO_SQL_DELETE_LOGIN = " DELETE FROM usuarios WHERE cod_usuario = ?";
	private static final String COMANDO_SQL_SELECT_CODIGO_PROFESSOR = "SELECT p.cod_professor"
																	+ " FROM usuarios AS u"
																    + " INNER JOIN professores AS p ON p.cod_usuario = u.cod_usuario"
																    + " WHERE u.cod_usuario = ?";
	private static final String COMANDO_SQL_SELECT_CODIGO_ALUNO = "SELECT a.cod_aluno"
																+ " FROM usuarios AS u"
															    + " INNER JOIN alunos AS a ON a.cod_usuario = u.cod_usuario"
															    + " WHERE u.cod_usuario = ?";
	
	
	public Usuarios validaLogin(String loginUsuario, String senhaUsuario) {
		List<Usuarios> usuarioExistente = jdbcTemplate.query(COMANDO_SQL_VALIDA_LOGIN, new RowMapper<Usuarios>() {
							@Override
							public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
								Usuarios usuario = new Usuarios();
								
								usuario.setCodigo(rs.getInt("cod_usuario"));
								usuario.setLoginUsuario(rs.getString("login"));
								usuario.setNivelUsuario(NivelUsuario.valueOf(rs.getString("nivel_usuario")));	
								return usuario;
							}
						}, loginUsuario, senhaUsuario);
		
		return usuarioExistente.isEmpty() ? null : usuarioExistente.get(0);
	}
	
	public void inserirLoginProfessor(Professores usuarioProfessor) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_LOGIN_PROFESSOR,
				usuarioProfessor.getLoginUsuario(),
				usuarioProfessor.getSenhaUsuario()
				);
		
		Integer idLoginProfessorGerada = jdbcTemplate.queryForObject(
				"SELECT LAST_INSERT_ID()", Integer.class);
		usuarioProfessor.setCodigo(idLoginProfessorGerada);
	}
	
	public void inserirLoginAluno(Alunos usuarioAluno) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_LOGIN_ALUNO,
				usuarioAluno.getLoginUsuario(),
				usuarioAluno.getSenhaUsuario()
				);
		
		Integer idLoginAlunoGerada = jdbcTemplate.queryForObject(
				"SELECT LAST_INSERT_ID()", Integer.class);
		usuarioAluno.setCodigo(idLoginAlunoGerada);
	}
	
	public void deletaLogin(int idUsuario) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_LOGIN,
				idUsuario);	
	}
	
	public Usuarios pegaCodigoProfessor(int codigoLogin) {
		List<Usuarios> codigoProfesssor =  jdbcTemplate.query(COMANDO_SQL_SELECT_CODIGO_PROFESSOR, new RowMapper<Usuarios>() {
							@Override
							public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
								Usuarios usuario = new Usuarios();
								
								usuario.setCodigo(rs.getInt("p.cod_professor"));
								return usuario;
							}
						}, codigoLogin);
		
		return codigoProfesssor.isEmpty() ? null : codigoProfesssor.get(0);
	}
	
	public Usuarios pegaCodigoAluno(int codigoLogin) {
		List<Usuarios> codigoAluno =  jdbcTemplate.query(COMANDO_SQL_SELECT_CODIGO_ALUNO, new RowMapper<Usuarios>() {
							@Override
							public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
								Usuarios usuario = new Usuarios();
								
								usuario.setCodigo(rs.getInt("a.cod_aluno"));
								return usuario;
							}
						}, codigoLogin);
		
		return codigoAluno.isEmpty() ? null : codigoAluno.get(0);
	}
}
