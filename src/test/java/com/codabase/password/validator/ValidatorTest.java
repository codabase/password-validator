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
import javax.validation.constraints.AssertTrue;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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

	/**
	 * password="12345r" Testcase 0 digit with one char at the end (valid)
	 */
	@Test
	public void passwordValid0()  {

		// The password
		UserPassword userPassword= new UserPassword("1234r");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertTrue(passwodValidationErrors.isEmpty());
	}

	/**
	 *		Testcase 1 (no) lowercase
	 */
	@Test
	public void passwordInValid1()  {

		// The password
		UserPassword userPassword= new UserPassword("12345");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 2 (no) digit
	 */
	@Test
	public void passwordInValid2()  {

		// The password
		UserPassword userPassword= new UserPassword("abcde");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 3 short password
	 */
	@Test
	public void passwordInValid3()  {

		// The password
		UserPassword userPassword= new UserPassword("aba1");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 4 long password
	 */
	@Test
	public void passwordInValid4()  {

		// The password
		UserPassword userPassword= new UserPassword("1234567890abc");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 5 hAS SPACE
	 */
	@Test
	public void passwordInValid5()  {

		// The password
		UserPassword userPassword= new UserPassword(" 1234a");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 6 hAS Uppercase
	 */
	@Test
	public void passwordInValid6()  {

		// The password
		UserPassword userPassword= new UserPassword("1234Ua");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
	}

	/**
	 * Testcase 7 repeating sequence
	 */
	@Test
	public void passwordInValid7()  {

		// The password
		UserPassword userPassword= new UserPassword("1231234a");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertFalse(passwodValidationErrors.isEmpty());
		//Must have at least have one lowercase letter and one digit, can only contain lowercase and digit. Length must be between 5 and 12. No consecutive sequence of character";
	}

	/**
	 * Testcase 8 check the size of the errors
	 */
	@Test
	public void passwordInValid8()  {

		// The password
		UserPassword userPassword= new UserPassword("1231234a");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		assertEquals(1,passwodValidationErrors.size());
	}

	/**
	 * Testcase 8 check the message
	 */
	@Test
	public void passwordInValid9()  {

		// The password
		UserPassword userPassword= new UserPassword("1231234a");
		Set<ConstraintViolation<UserPassword>> passwodValidationErrors = validator.validate(userPassword);
		Object[] errors = passwodValidationErrors.toArray();
		assertEquals(1,errors.length);
		ConstraintViolation<UserPassword> error = (ConstraintViolation<UserPassword>)errors[0];
		assertEquals("Must have at least have one lowercase letter and one digit, can only contain lowercase and digit. Length must be between 5 and 12. No consecutive sequence of character",error.getMessage());
	}








}