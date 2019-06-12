package com.yqyan.user.center.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 11:11
 **/
public class ParamValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static boolean check(Object obj){
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(new HashSet<>(violations));
        }
        return true;
    }

}
