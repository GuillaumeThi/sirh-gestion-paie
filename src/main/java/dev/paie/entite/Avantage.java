package dev.paie.entite;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Avantage {

	@Id
	private Integer id;
	private String code;
	private String nom;
	private BigDecimal montant;
	
	@ManyToMany(mappedBy="avantages")
    private List<ProfilRemuneration> listProfilAvantages;
	
	public Avantage() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
