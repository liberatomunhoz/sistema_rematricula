package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.Turmas;

@Component
public class TurmasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_TURMAS = "INSERT INTO turmas (numero_turma) VALUES (?)";
	
	private static final String COMANDO_SQL_SELECT_TURMAS = "SELECT pt.cod_professor_turma, td.cod_turmas_disciplinas, t.cod_turma, p.nome_completo, t.numero_turma, d.nome_disc"
																	  + " FROM turmas AS t"
																	  + " INNER JOIN professores_turmas AS pt ON t.cod_turma = pt.turma_cod"
																	  + " INNER JOIN professores AS p ON p.cod_professor = pt.professor_cod"
																	  + " INNER JOIN turmas_disciplinas AS td ON t.cod_turma = td.turma_cod"
																	  + " INNER JOIN disciplinas AS d ON d.cod_disc = td.disc_cod"
																	  + " ORDER BY t.numero_turma";
	private static final String COMANDO_SQL_DELETE_TURMAS = "DELETE FROM turmas WHERE cod_turma = ?";
	
	private static final String COMANDO_SQL_SELECT_TURMA_DISCIPLINA_CODIGO_PROFESSOR = "SELECT p.cod_professor, p.nome_completo, t.numero_turma, d.cod_disc, d.nome_disc"
																					 + " FROM professores AS p"
																					 + " INNER JOIN professores_turmas AS pt ON p.cod_professor = pt.professor_cod"
																					 + " INNER JOIN turmas AS t ON t.cod_turma = pt.turma_cod"
																					 + " INNER JOIN turmas_disciplinas AS td ON t.cod_turma = td.turma_cod"
																					 + " INNER JOIN disciplinas AS d ON d.cod_disc = td.disc_cod"
 																					 + " WHERE p.cod_professor = ?";
	
	private static final String COMANDO_SQL_SELECT_ALUNO_PROFESSOR_DISCIPLINA_NUMERO_TURMA = "SELECT a.cod_aluno, a.nome_completo, ad.nota, d.cod_disc"
																						   + " FROM alunos AS a"
																						   + " INNER JOIN alunos_disciplinas AS ad ON a.cod_aluno = ad.aluno_cod"
																						   + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
																						   + " INNER JOIN turmas_disciplinas AS td ON d.cod_disc = td.disc_cod"
																						   + " INNER JOIN turmas AS t ON t.cod_turma = td.turma_cod"
																						   + " INNER JOIN professores_turmas AS pt ON pt.turma_cod = t.cod_turma"
																						   + " WHERE pt.professor_cod = ?"
																						   + " AND d.cod_disc = ?"
																						   + " AND t.numero_turma = ?"
																						   + " AND ('Concluído' LIKE CONCAT('%', ad.status, '%') OR ('Reprovado' LIKE CONCAT('%', ad.status, '%')) OR ('Matriculado' LIKE CONCAT('%', ad.status, '%')))";
	private static final String COMANDO_SQL_SELECT_ALUNO_NOTA = "SELECT a.nome_completo, ad.nota"
 														      + " FROM alunos AS a"
														      + " INNER JOIN alunos_disciplinas AS ad ON a.cod_aluno = ad.aluno_cod"
														      + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
														      + " INNER JOIN turmas_disciplinas AS td ON d.cod_disc = td.disc_cod"
														      + " INNER JOIN turmas AS t ON t.cod_turma = td.turma_cod"
														      + " INNER JOIN professores_turmas AS pt ON pt.turma_cod = t.cod_turma"
														      + " WHERE pt.professor_cod = ?"
														      + " AND d.cod_disc = ?"
														      + " AND t.numero_turma = ?"
														      + " AND ('Concluído' LIKE CONCAT('%', ad.status, '%') OR ('Reprovado' LIKE CONCAT('%', ad.status, '%')) OR ('Matriculado' LIKE CONCAT('%', ad.status, '%')))";
	
	public void inserirTurmas(Turmas turma) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_TURMAS,
				turma.getNumeroTurma()
				);
		
		Integer idTurmaGerada = jdbcTemplate.queryForObject(
				"SELECT LAST_INSERT_ID()", Integer.class);
		turma.setCodigo(idTurmaGerada);
	}
	
	public List<Turmas> consultaTurmas() {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_TURMAS,
						new RowMapper<Turmas>() {
							public Turmas mapRow(ResultSet rs, int arg1) throws SQLException {
								Turmas turma = new Turmas();
								turma.setCodigo(rs.getInt("t.cod_turma"));
								turma.setCodigoProfessorTurma(rs.getInt("pt.cod_professor_turma"));
								turma.setCodigoTurmaDisciplina(rs.getInt("td.cod_turmas_disciplinas"));
								turma.setNomeCompleto(rs.getString("p.nome_completo"));
								turma.setNumeroTurma(rs.getInt("t.numero_turma"));
								turma.setNomeDisciplina(rs.getString("d.nome_disc"));
								return turma;
							}
						});
	}
	
	public void deletaTurmas(int idTurma) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_TURMAS,
				idTurma);
	}

	public List<Turmas> consultaTurmaEDisciplinaPeloCodigoProfessor(int codigoProfessor) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_TURMA_DISCIPLINA_CODIGO_PROFESSOR,
						new RowMapper<Turmas>() {
							public Turmas mapRow(ResultSet rs, int arg1) throws SQLException {
								Turmas turma = new Turmas();
								turma.setCodigoProfessor(rs.getInt("p.cod_professor"));
								turma.setNomeCompleto(rs.getString("p.nome_completo"));
								turma.setNumeroTurma(rs.getInt("t.numero_turma"));
								turma.setCodigoDisciplina(rs.getInt("d.cod_disc"));
								turma.setNomeDisciplina(rs.getString("d.nome_disc"));
								return turma;
							}
						}, codigoProfessor);	
	}
	
	public List<Turmas> consultaAlunoProfessorDisciplinaNumeroTurma(int codigoProfessor, int codigoDisciplina, int numeroTurma) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_ALUNO_PROFESSOR_DISCIPLINA_NUMERO_TURMA,
						new RowMapper<Turmas>() {
							public Turmas mapRow(ResultSet rs, int arg1) throws SQLException {
								Turmas turma = new Turmas();
								turma.setCodigo(rs.getInt("a.cod_aluno"));
								turma.setNomeCompleto(rs.getString("a.nome_completo"));
								turma.setNotaAluno(rs.getString("ad.nota"));
								turma.setCodigoDisciplina(rs.getInt("d.cod_disc"));
								return turma;
							}
						},codigoProfessor, codigoDisciplina, numeroTurma);
	}
	
	public List<Turmas> consultaAlunoNota(int codigoProfessor, int codigoDisciplina, int numeroTurma) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_ALUNO_NOTA,
						new RowMapper<Turmas>() {
							public Turmas mapRow(ResultSet rs, int arg1) throws SQLException {
								Turmas turma = new Turmas();
								turma.setNomeCompleto(rs.getString("a.nome_completo"));
								turma.setNotaAluno(rs.getString("ad.nota"));
								return turma;
							}
						},codigoProfessor, codigoDisciplina, numeroTurma);
	}
}
