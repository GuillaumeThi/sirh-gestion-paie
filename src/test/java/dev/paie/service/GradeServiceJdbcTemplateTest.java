package dev.paie.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class  })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	
@Autowired
private GradeService gradeService;

@Test
public void test_sauvegarder_lister_mettre_a_jour() {
	
		Grade grade = new Grade();
		
		grade.setCode("salut");
		grade.setNbHeuresBase(new BigDecimal("1000"));
		grade.setTauxBase(new BigDecimal("2000"));
	
		//TODO sauvegarder un nouveau grade
		gradeService.sauvegarder(grade);
		
		//TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		List<Grade> grades = new ArrayList<Grade>();
		grades = gradeService.lister();
		Grade last = grades.get(grades.size() - 1);
		
		assertThat(last.getCode(), equalTo("salut"));
		
		//TODO modifier un grade
		last.setCode("salut, j'ai change");
		gradeService.mettreAJour(last);
		
		//TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		grades = gradeService.lister();
		last = grades.get(grades.size() - 1);
		assertThat(last.getCode(), equalTo("salut, j'ai change"));
	}
}
