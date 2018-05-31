package com.codabase.password.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com.codabase.password.validator/spring-beans.xml"})
public class ValidatorTest {

	@Autowired
	private PasswordValidator passwordValidator;
	Validator validator; //= validatorFactory.getValidator();

	@Before
	public void setUp() throws Exception {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	public void passwordValidationTest()  {

		//Getting Validator instance with Annotations
		UserPassword userPassword= new UserPassword("12345r");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		if(!passwodValidationErrors.isEmpty()){
			for(ConstraintViolation<UserPassword> error : passwodValidationErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
			}
		}

	}

}