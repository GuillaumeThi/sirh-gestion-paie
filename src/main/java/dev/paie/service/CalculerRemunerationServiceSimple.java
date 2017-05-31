package dev.paie.service;

import java.math.BigDecimal;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils formatter;

	@Autowired
	ResultatCalculRemuneration res;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		// PaieUtils formatter = new PaieUtils();
		// ResultatCalculRemuneration res = new ResultatCalculRemuneration();

		BigDecimal salaireDeBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		BigDecimal salaireBrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		BigDecimal totalRetenueSalariale = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrut))
				.collect(Collectors.reducing((v1, v2) -> v1.add(v2))).get();

		BigDecimal totalCotisationsPatronales = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxPatronal() != null)
				.map(c -> c.getTauxPatronal().multiply(salaireBrut))
				.collect(Collectors.reducing((v1, v2) -> v1.add(v2))).get();

		BigDecimal netImposable = salaireBrut.subtract(totalRetenueSalariale);
		BigDecimal netAPayer = netImposable
				.subtract(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables().stream()
						.filter(c -> c.getTauxSalarial() != null).map(c -> c.getTauxSalarial().multiply(salaireBrut))
						.collect(Collectors.reducing((v1, v2) -> v1.add(v2))).get());

		res.setSalaireDeBase(formatter.formaterBigDecimal(salaireDeBase));
		res.setSalaireBrut(formatter.formaterBigDecimal(salaireBrut));
		res.setTotalRetenueSalarial(formatter.formaterBigDecimal(totalRetenueSalariale));
		res.setTotalCotisationsPatronales(formatter.formaterBigDecimal(totalCotisationsPatronales));
		res.setNetImposable(formatter.formaterBigDecimal(netImposable));
		res.setNetAPayer(formatter.formaterBigDecimal(netAPayer));

		return res;
	}

}
