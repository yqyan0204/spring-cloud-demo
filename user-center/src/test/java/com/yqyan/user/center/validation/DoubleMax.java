package com.yqyan.user.center.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * double 最大值验证器, 属性值必须小于等于最大值
 * @Author yanyaqiang
 * @Date 2019/4/29 11:04
 **/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DoubleMaxValidator.class)
public @interface DoubleMax {

    /**
     * 属性值必须小于或等于该值
     * @return
     */
    double value();

    String message() default "属性值必须小于或等于指定值";

    Class<?>[]groups() default {};

    Class<? extends Payload>[]payload() default {};
}
