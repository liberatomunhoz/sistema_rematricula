package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import rematricula.model.Usuarios;

@Component
public class UsuariosDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public Usuarios validaLogin(String loginUsuario, String senhaUsuario) {
		List<Usuarios> usuarioExistente = jdbcTemplate.query("select login, senha, tipo, cod_usuario from usuarios where login = ? and senha = ?", new RowMapper<Usuarios>() {
							@Override
							public Usuarios mapRow(ResultSet rs, int arg1) throws SQLException {
								Usuarios usuario = new Usuarios();
								usuario.setLoginUsuario(rs.getString("login"));
								usuario.setSenhaUsuario(rs.getString("senha"));
								usuario.setTipoUsuario(rs.getInt("tipo"));
								usuario.setCodigoUsuario(rs.getInt("cod_usuario"));
								return usuario;
							}
						}, loginUsuario, senhaUsuario);
		
		return usuarioExistente.isEmpty() ? null : usuarioExistente.get(0);
	}	
}
