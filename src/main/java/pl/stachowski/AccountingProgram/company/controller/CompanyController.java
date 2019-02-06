package pl.stachowski.AccountingProgram.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.stachowski.AccountingProgram.company.entity.Company;
import pl.stachowski.AccountingProgram.company.service.CompanyService;

@Controller
@RequestMapping("user/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	@GetMapping("add")
	public ModelAndView addNewCompanies() {
		ModelAndView model = new ModelAndView();
		model.addObject("company", new Company());
		model.setViewName("company/add");
		return model;
	}
	
	@PostMapping("add")
	public ModelAndView addNewCompanies(@AuthenticationPrincipal UserDetails currentUser, Company company) {
		ModelAndView model = new ModelAndView("redirect:/user/home");
		companyService.save(currentUser, company);
		return model;
	}
	
	@GetMapping("load")
	public ModelAndView loadListOfAllMyCompanies(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView model = new ModelAndView();
		model.addObject("company", companyService.loadAllCompanies(currentUser));
		model.setViewName("company/loadAllCompanies");
		return model;
	}
}
