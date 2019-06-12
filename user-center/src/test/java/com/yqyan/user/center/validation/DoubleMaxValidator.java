package com.yqyan.user.center.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * double 最大值验证器, 属性值必须小于等于最大值
 * @Author yanyaqiang
 * @Date 2019/4/29 11:19
 **/
public class DoubleMaxValidator implements ConstraintValidator<DoubleMax, Double> {

    double maxValue;

    @Override
    public void initialize(DoubleMax doubleMax) {
        this.maxValue = doubleMax.value();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // null 值有效
        if(value == null){
            return true;
        }

        return value.doubleValue() <= maxValue;

    }
}
