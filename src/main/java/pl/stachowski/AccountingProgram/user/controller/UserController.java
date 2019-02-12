package pl.stachowski.AccountingProgram.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.stachowski.AccountingProgram.user.entity.User;
import pl.stachowski.AccountingProgram.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("edit")
	public ModelAndView editUserDetails(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/editUserInfo");
		model.addObject("user", userService.findCurrentLogUser(currentUser));
		return model;
	}
	
	@PostMapping("edit")
	public ModelAndView editUserDetails(@Valid User user, BindingResult result, @AuthenticationPrincipal UserDetails currentUser) {
		if(result.hasErrors()) {
			return new ModelAndView("user/editUserInfo");
		}
		userService.editUser(user, currentUser);
		return new ModelAndView("redirect:/user/home");
	}
}
