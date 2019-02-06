package pl.stachowski.AccountingProgram.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.stachowski.AccountingProgram.invoice.service.InvoiceService;

@Controller

public class HomeController {
	
	@Autowired
	private InvoiceService invoiceService;
	 
	@GetMapping("/user/home")
	public ModelAndView goToHomePage(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("invoices", invoiceService.findAllInvoiceForCurrentUser(currentUser));
		return modelAndView;
	}
}
