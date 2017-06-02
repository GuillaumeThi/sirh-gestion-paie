package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired ApplicationContext context;
	
	@PersistenceContext EntityManager em;
	
	@Override
	@Transactional
	public void initialiser() {
		
		
		//List<Periode> periodes = new ArrayList<Periode>();
		int currentYear = LocalDate.now().getYear();
		for(int i=1; i<13; i++) {
			LocalDate dateDebut = LocalDate.of(currentYear, i, 1);
			em.persist(new Periode(dateDebut, dateDebut.with(TemporalAdjusters.lastDayOfMonth())));
		}
		
		
		context.getBeansOfType(Grade.class).forEach((nomBean, bean) -> em.persist(bean));
		
		context.getBeansOfType(Entreprise.class).forEach((nomBean, bean) -> em.persist(bean));
		
		context.getBeansOfType(Cotisation.class).forEach((nomBean, bean) -> em.persist(bean));
		
		context.getBeansOfType(ProfilRemuneration.class).forEach((nomBean, bean) -> em.persist(bean));

	}

}
