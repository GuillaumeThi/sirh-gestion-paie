package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepo;
	@Autowired ProfilRepository profilRepo;
	@Autowired GradeRepository gradeRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepo.findAll());
		mv.addObject("profils", profilRepo.findAll());
		mv.addObject("grades", gradeRepo.findAll());
		return mv;
	}
}
