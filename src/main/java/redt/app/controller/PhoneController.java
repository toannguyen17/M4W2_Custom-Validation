package redt.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import redt.app.model.Phone;
import redt.app.util.validate.PhoneValidation;

import javax.validation.Valid;

@Controller
public class PhoneController {
	@GetMapping("/")
	public ModelAndView showForm(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("phone", new Phone());
		return modelAndView;
	}

	@PostMapping("/")
	public String checkValidation (@ModelAttribute("phone") Phone phone, BindingResult bindingResult){
		new PhoneValidation().validate(phone, bindingResult);
		if (bindingResult.hasFieldErrors()){
			bindingResult.getAllErrors().forEach(result -> {
				ObjectError objectError = (ObjectError) result;
				System.out.println(objectError.toString());
			});
			return "index";
		}
		return "result";
	}
}
