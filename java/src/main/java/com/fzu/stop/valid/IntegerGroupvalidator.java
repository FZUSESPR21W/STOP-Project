package com.fzu.stop.valid;


import com.fzu.stop.valid.annotation.IntegerGroup;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerGroupvalidator implements ConstraintValidator<IntegerGroup,Integer> {
    int[] integerGroup;
    @Override
    public void initialize(IntegerGroup constraintAnnotation) {
        integerGroup=constraintAnnotation.integerGroup();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        Boolean flag=false;
        for (int value : integerGroup) {
            if (value==integer) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
