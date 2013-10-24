package net.evolutionaryarchitecture.jpa.samples.web;

import net.evolutionaryarchitecture.jpa.samples.domain.Event;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class EventValidator implements Validator {

	public boolean supports(Class candidate) {
		return Event.class.isAssignableFrom(candidate);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required",
				"Title is required.");
	}

}
