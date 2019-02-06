package pl.stachowski.AccountingProgram.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.stachowski.AccountingProgram.company.entity.Company;
import pl.stachowski.AccountingProgram.company.service.CompanyService;
import pl.stachowski.AccountingProgram.invoice.entity.Invoice;
import pl.stachowski.AccountingProgram.invoice.service.InvoiceService;

@Controller
@RequestMapping("user/invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private CompanyService companyService;

	@GetMapping("/new")
	public ModelAndView createNewInvoice() {
		ModelAndView model = new ModelAndView();
		model.addObject("invoice", new Invoice());
		model.setViewName("invoice/newInvoice");
		return model;
	}
	
	@PostMapping("/new")
	public ModelAndView createNewInvoice(@AuthenticationPrincipal UserDetails currentUser, Invoice invoice) {
		ModelAndView model = new ModelAndView("redirect:/user/home");
		invoiceService.saveNewInvoiceForCurrentUser(currentUser,invoice);
		return model;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editInvoice(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("invoice", invoiceService.findInvoiceById(id));
		model.setViewName("invoice/editInvoice");
		return model;
	}
	
	@PostMapping("/edit/{id}")
	public ModelAndView editInvoice(@AuthenticationPrincipal UserDetails currentUser, Invoice invoice) {
		ModelAndView model = new ModelAndView("redirect:/user/home");
		invoiceService.saveEditInvoice(invoice);
		return model;
	}
	
	@ModelAttribute("listAllCompaniesForCurrentUser")
	public List<Company> getAllCompaniesForCurrentUser(@AuthenticationPrincipal UserDetails currentUser){	
		return companyService.loadAllCompanies(currentUser);
	}
}
