package dev.paie.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Avantage avantage = new Avantage();
		
		avantage.setId(new Integer(1));
		avantage.setCode("un code");
		avantage.setNom("un nom");
		avantage.setMontant(new BigDecimal("500"));
		
		//TODO sauvegarder un nouvel avantage
		avantageRepository.save(avantage);
		
		//TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		Avantage avantageAModifier = avantageRepository.findOne(1);
		
		//TODO modifier un avantage
		avantageAModifier.setCode("un autre code");
		avantageRepository.save(avantageAModifier);
		
		//TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		Avantage avantageATester = avantageRepository.findOne(1);
		assertThat(avantageATester.getCode(), equalTo("un autre code"));
		
		assertThat(avantageRepository.findByCode("un autre code").get(0).getCode(), equalTo("un autre code"));
	}
}
