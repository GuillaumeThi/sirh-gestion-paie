package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepo;
	@Autowired ProfilRepository profilRepo;
	@Autowired GradeRepository gradeRepo;
	@Autowired RemunerationEmployeRepository remEmployeRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmployeGet() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepo.findAll());
		mv.addObject("profils", profilRepo.findAll());
		mv.addObject("grades", gradeRepo.findAll());
		return mv;
	}
	
	@RequestMapping(value="/creer", method=RequestMethod.POST)
    public String form(
    		@RequestParam("matricule") String matricule,
    		@RequestParam("grade") Integer gradeId,
    		@RequestParam("entreprise") Integer entrepriseId,
    		@RequestParam("profil") Integer profilId,
    		Model model
    	) {
    
		
		RemunerationEmploye re = new RemunerationEmploye(matricule, entrepriseRepo.findOne(entrepriseId), profilRepo.findOne(profilId), gradeRepo.findOne(gradeId));
		re.setDateCreation();
		remEmployeRepo.saveAndFlush(re);
        
        //model.addAttribute("statusMessageKey", "person.form.msg.success");
		
		return "redirect:/mvc/employes/lister";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmployeGet() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("employes", remEmployeRepo.findAll());
		return mv;
	}
}
