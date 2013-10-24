package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.PrePersist;
import javax.validation.Validation;
import javax.validation.Validator;

public class PersonValidator {

  Validator validator;

  public PersonValidator() {
    validator = Validation.buildDefaultValidatorFactory()
        .getValidator();
  }

  @PrePersist
  public void validatePrePersist(Person person) {
    validator.validate(person);
  }

}
