package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.NivelUsuario;
import rematricula.model.Professores;
import rematricula.model.Usuarios;

@Component
public class UsuariosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_VALIDA_LOGIN = "SELECT cod_usuario, login, senha, nivel_usuario FROM usuarios WHERE login = ? AND senha = ?";
	private static final String COMANDO_SQL_INSERT_LOGIN_PROFESSOR = "INSERT INTO usuarios (login, senha, nivel_usuario) VALUES (?, ?, 'PROFESSOR')";
	private static final String COMANDO_SQL_DELETE_LOGIN_PROFESSOR = " DELETE FROM usuarios WHERE cod_usuario = ?";
	
	public Usuarios validaLogin(String loginUsuario, String senhaUsuario) {
		List<Usuarios> usuarioExistente = jdbcTemplate.query(COMANDO_SQL_VALIDA_LOGIN, new RowMapper<Usuarios>() {
							@Override
							public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
								Usuarios usuario = new Usuarios();
								
								usuario.setCodigo(rs.getInt("cod_usuario"));
								usuario.setLoginUsuario(rs.getString("login"));
								usuario.setSenhaUsuario(rs.getString("senha"));
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
	
	public void deletaLoginProfessor(int idUsuario) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_LOGIN_PROFESSOR,
				idUsuario);
		
		
	}
	
	
}
