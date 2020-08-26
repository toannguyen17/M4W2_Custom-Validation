package redt.app.util.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import redt.app.model.Phone;

@Component
public class PhoneValidation implements Validator {
	public PhoneValidation(){
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return PhoneValidation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Phone phoneObj = (Phone) target;
		String phone = phoneObj.getPhone();

		ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");

		if (!phone.matches("^\\+?(84|0)((86|96|97|98|32|33|34|35|36|37|38|39|89|90|93|70|79|77|76|78|88|91|94|83|84|85|81|82|92|56|58|99|59)([0-9]{7}))$"))
			errors.rejectValue("phone", "phone.invalid");
	}
}
