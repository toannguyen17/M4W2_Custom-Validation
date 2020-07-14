package redt.app.util.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PhoneNumber implements Validator {
	private String number;

	public PhoneNumber (){
	}
	public PhoneNumber (String number){
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return PhoneNumber.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PhoneNumber phoneNumber = (PhoneNumber) target;

		String phone = phoneNumber.getNumber();

		ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");

		if (phone.length() > 11 || phone.length() < 10){
			errors.rejectValue("number", "number.length");
		}
		if(!phone.startsWith("0")){
			errors.rejectValue("number", "number.startsWith");
		}
		if (!phone.matches("^0[0-9]{9}+$")){
			errors.rejectValue("number", "number.matches");
		}
	}
}
