package pl.stachowski.AccountingProgram.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.stachowski.AccountingProgram.user.entity.User;
import pl.stachowski.AccountingProgram.user.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@GetMapping("reg")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("registration");
		return model;
	}
	
	@PostMapping("reg")
	public ModelAndView saveNewUser(@Valid User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		User userExist = userService.findUserByEmail(user.getEmail());
		
		if(userExist != null) {
			result.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if(result.hasErrors()) {
			model.setViewName("registration");
		}
		else {
			userService.saveUser(user);
			model.addObject("successMessage", "User has been registered successfully");
			model.setViewName("login");
		}
		return model;
	}
}
