package com.yqyan.user.center.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * double 最小值验证器, 属性值必须大于等于最小值
 * @Author yanyaqiang
 * @Date 2019/4/29 11:19
 **/
public class DoubleMinValidator  implements ConstraintValidator<DoubleMin, Double> {

    double minValue;

    @Override
    public void initialize(DoubleMin doubleMin) {
        this.minValue = doubleMin.value();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // null 值有效
        if(value == null){
            return true;
        }

        return value.doubleValue() >= minValue;

    }
}
