package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.NivelUsuario;
import rematricula.model.Usuarios;

@Component
public class UsuariosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private final String COMANDO_SQL_VALIDA_LOGIN = "SELECT cod_usuario, login, senha, nivel_usuario FROM usuarios WHERE login = ? AND senha = ?";

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
}
