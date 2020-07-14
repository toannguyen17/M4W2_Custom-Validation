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

import javax.validation.Valid;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView showForm(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("phonemunber", new PhoneNumber());
		return modelAndView;
	}

	@PostMapping("/")
	public String checkValidation (@Valid @ModelAttribute("phonemunber")PhoneNumber phoneNumber, BindingResult bindingResult, Model model){
		new PhoneNumber().validate(phoneNumber, bindingResult);
		if (bindingResult.hasFieldErrors()){
			bindingResult.getAllErrors().forEach(result -> {
				System.out.println(result.toString());
			});
			return "index";
		}
		else {
			model.addAttribute("phoneNumber", phoneNumber.getNumber());
			return "result";
		}

	}
}
