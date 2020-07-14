package redt.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import redt.app.util.validate.PhoneNumber;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView showForm(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("phonemunber", new PhoneNumber());
		return modelAndView;
	}

	@PostMapping("/")
	public ModelAndView checkValidation(@Validated @ModelAttribute("phonemunber") PhoneNumber phoneNumber, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		new PhoneNumber().validate(phoneNumber, bindingResult);
		if (bindingResult.hasFieldErrors()) {
			modelAndView.addObject("phonemunber", new PhoneNumber());
			modelAndView.setViewName("index");
		}else{
			modelAndView.addObject("phonemunber",phoneNumber);
			modelAndView.setViewName("result");
		}
		return modelAndView;
	}
}
