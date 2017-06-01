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
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { ServicesConfig.class  })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Cotisation cotisation = new Cotisation();
		
		cotisation.setCode("un code");
		cotisation.setLibelle("un libellé");
		cotisation.setTauxSalarial(new BigDecimal("2000"));
		cotisation.setTauxPatronal(new BigDecimal("5000"));
	
		//TODO sauvegarder un nouveau cotisation
		cotisationService.sauvegarder(cotisation);
		
		//TODO vérifier qu'il est possible de récupérer le nouveau cotisation via la méthode lister
		List<Cotisation> cotisations = new ArrayList<Cotisation>();
		cotisations = cotisationService.lister();
		Cotisation last = cotisations.get(cotisations.size() - 1);
		
		assertThat(last.getCode(), equalTo("un code"));
		
		//TODO modifier un cotisation
		last.setCode("un autre code");
		cotisationService.mettreAJour(last);
		
		//TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		cotisations = cotisationService.lister();
		last = cotisations.get(cotisations.size() - 1);
		assertThat(last.getCode(), equalTo("un autre code"));

	}

}
