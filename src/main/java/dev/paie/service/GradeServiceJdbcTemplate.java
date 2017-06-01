package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		
		String sql = "INSERT INTO GRADE (ID, code, nbHeuresBase, tauxBase) VALUES(?,?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getId(), nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(), nouveauGrade.getTauxBase());
		
	}

	@Override
	public void mettreAJour(Grade grade) {
		
		String sql = "UPDATE GRADE set code=?, nbHeuresBase=?, tauxBase=? WHERE ID=?";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
		
	}

	@Override
	public List<Grade> lister() {
		
		return jdbcTemplate.query("SELECT * FROM grade", (rs, rowNum) -> {
					    Grade grade = new Grade();
					    grade.setId(rs.getInt("ID"));
					    grade.setCode(rs.getString("CODE"));
					    grade.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
					    grade.setTauxBase(rs.getBigDecimal("tauxBase"));
					    return grade;
					});

	}
}
