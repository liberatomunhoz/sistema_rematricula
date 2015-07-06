package rematricula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import rematricula.model.AlunosDisciplinas;


@Component
public class AlunosDisciplinasDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMANDO_SQL_INSERT_ALUNOS_DISCIPLINAS = "INSERT INTO alunos_disciplinas (disciplina_cod, aluno_cod, status, nota) VALUES (?, ?, 'Matriculado', '')";
	private static final String COMANDO_SQL_UPDATE_NOTA_ALUNO_CONCUIDO = "UPDATE alunos_disciplinas SET nota = ?, status = 'Concluído' WHERE disciplina_cod = ? AND aluno_cod = ?";
	private static final String COMANDO_SQL_UPDATE_NOTA_ALUNO_REPROVADO = "UPDATE alunos_disciplinas SET nota = ?, status = 'Reprovado' WHERE disciplina_cod = ? AND aluno_cod = ?";
	private static final String COMANDO_SQL_SELECT_GRADE_CURRICULAR_ALUNO = "SELECT d.cod_disc, a.cod_aluno, d.semestre, t.numero_turma, d.nome_disc, a.nome_completo, ad.nota, ad.status"
																		  + " FROM alunos AS a"
																		  + " INNER JOIN alunos_disciplinas AS ad ON a.cod_aluno = ad.aluno_cod"
																	      + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
																	      + " INNER JOIN turmas_disciplinas AS td ON d.cod_disc = td.disc_cod"
																	      + " INNER JOIN turmas AS t ON t.cod_turma = td.turma_cod"
																	      + " WHERE a.cod_aluno = ?";
	private static final String COMANDO_SQL_SELECT_CODIGO_SEMESTRE_ALUNO = "SELECT a.cod_aluno, d.semestre"
																		  + " FROM alunos AS a"
																		  + " INNER JOIN alunos_disciplinas AS ad ON a.cod_aluno = ad.aluno_cod"
																	      + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
																	      + " WHERE a.cod_aluno = ?"
																	      + " LIMIT 1";
	private static final String COMANDO_SQL_SELECT_DISCIPLINA_PARA_REMATRICULA = "SELECT d.semestre, d.cod_disc, d.nome_disc" 
																			   + " FROM disciplinas AS d"
                                                                               + " WHERE d.disc_pre IN (SELECT ad.disciplina_cod"
				                                                               + " FROM alunos_disciplinas AS ad"
				                                                               + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
				                                                               + " WHERE d.semestre = ?"
				                                                               + " AND ad.aluno_cod = ?"
				                                                               + " AND ('Concluído' LIKE CONCAT('%', ad.status, '%')))";
	
	private static final String COMANDO_SQL_SELECT_DISCIPLINA_PARA_REMATRICULA_CASO_REPROVE = "SELECT d.semestre, d.cod_disc, d.nome_disc" 
			   																				+ " FROM disciplinas AS d"
			   																				+ " WHERE d.cod_disc IN (SELECT ad.disciplina_cod"
			   																				+ " FROM alunos_disciplinas AS ad"
																				            + " INNER JOIN disciplinas AS d ON d.cod_disc = ad.disciplina_cod"
																				            + " WHERE d.semestre = ?"
																				            + " AND ad.aluno_cod = ?"
																				            + " AND ('Reprovado' LIKE CONCAT('%', ad.status, '%')))";
	
	private static final String COMANDO_SQL_DELETE_ALUNOS_DISCIPLINAS = "DELETE FROM alunos_disciplinas WHERE aluno_cod = ?";
	
	
	public void inserirAlunosDisciplinasInicial(int codigoDisciplina, int codigoAluno) {
		jdbcTemplate.update(
				COMANDO_SQL_INSERT_ALUNOS_DISCIPLINAS,
				codigoDisciplina,
				codigoAluno
				);
	}
	
	public void inserirNotaAluno(String notaAluno, int codigoDisciplina, int codigoAluno){
		if (notaAluno.equals("AP") || notaAluno.equals("A")) {
			jdbcTemplate.update(COMANDO_SQL_UPDATE_NOTA_ALUNO_CONCUIDO,
					notaAluno, codigoDisciplina, codigoAluno);
		} else {
			jdbcTemplate.update(COMANDO_SQL_UPDATE_NOTA_ALUNO_REPROVADO,
					notaAluno, codigoDisciplina, codigoAluno);
		}
	}
	
	public List<AlunosDisciplinas> consultaGradeCurricular(int codigoAluno) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_GRADE_CURRICULAR_ALUNO,
						new RowMapper<AlunosDisciplinas>() {
							public AlunosDisciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								AlunosDisciplinas alunoGrade = new AlunosDisciplinas();
								alunoGrade.setCodigoDisciplina(rs.getInt("d.cod_disc"));
								alunoGrade.setCodigo(rs.getInt("a.cod_aluno"));
								alunoGrade.setSemestre(rs.getInt("d.semestre"));
								alunoGrade.setNumeroTurma(rs.getInt("t.numero_turma"));
								alunoGrade.setNomeCompleto(rs.getString("a.nome_completo"));
								alunoGrade.setNomeDisciplina(rs.getString("d.nome_disc"));
								alunoGrade.setNotaAluno(rs.getString("ad.nota"));
								alunoGrade.setStatus(rs.getString("ad.status"));
								return alunoGrade;
							}
						}, codigoAluno);
	}
	
	public List<AlunosDisciplinas> consultaCodigoSemestreAluno(int codigoAluno) {
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_CODIGO_SEMESTRE_ALUNO,
						new RowMapper<AlunosDisciplinas>() {
							public AlunosDisciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								AlunosDisciplinas aluno = new AlunosDisciplinas();
								aluno.setCodigo(rs.getInt("a.cod_aluno"));
								aluno.setSemestre(rs.getInt("d.semestre"));
								return aluno;
							}
						}, codigoAluno);
	}
	
	public List<AlunosDisciplinas> consultaDisciplinaRematricula(int codigoAluno, int semestre){			
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_DISCIPLINA_PARA_REMATRICULA,
						new RowMapper<AlunosDisciplinas>() {
							public AlunosDisciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								AlunosDisciplinas aluno = new AlunosDisciplinas();
								aluno.setCodigoDisciplina(rs.getInt("d.cod_disc"));
								aluno.setNomeDisciplina(rs.getString("d.nome_disc"));
								aluno.setSemestre(rs.getInt("d.semestre"));
								return aluno;
							}
						}, semestre, codigoAluno);
	}
	
	public List<AlunosDisciplinas> consultaDisciplinaReprovadaRematricula(int codigoAluno, int semestre){			
		return jdbcTemplate
				.query(COMANDO_SQL_SELECT_DISCIPLINA_PARA_REMATRICULA_CASO_REPROVE,
						new RowMapper<AlunosDisciplinas>() {
							public AlunosDisciplinas mapRow(ResultSet rs, int arg1) throws SQLException {
								AlunosDisciplinas aluno = new AlunosDisciplinas();
								aluno.setCodigoDisciplina(rs.getInt("d.cod_disc"));
								aluno.setNomeDisciplina(rs.getString("d.nome_disc"));
								aluno.setSemestre(rs.getInt("d.semestre"));
								return aluno;
							}
						}, semestre, codigoAluno);
	}

	public void deletaAlunoDisciplina(int codigo) {
		jdbcTemplate.update(COMANDO_SQL_DELETE_ALUNOS_DISCIPLINAS,
				codigo);		
	}
}
