package com.codabase.password.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, String> {
 
    @Override
    public void initialize(PasswordConstraint passwordConstraint) {
    }
    @Override
    public boolean isValid(String password,
      ConstraintValidatorContext cxt) {
        boolean isValid=false;
        // 1) password can only contain lowercase and digit with at least one of each
        // 2) minimum password length is 5
        // 3) maximum password length is 12
        isValid =  password.matches("^.*(?=.{5,12})(?=.*\\d)(?=.*[a-z]).*$");
        // 4) can not have same sequence of character followed right after
        //if (inValidPassword)
       // {
            String regex="(\\w{1,})\\1"; //  Regular Expression if any sequence followed by same
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(password);
       return isValid && !matcher.find();
       // }

    }
 
}