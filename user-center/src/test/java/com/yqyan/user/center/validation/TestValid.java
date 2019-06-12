package com.yqyan.user.center.validation;

import org.junit.Test;

/**
 * @Author yanyaqiang
 * @Date 2019/4/29 10:45
 **/
public class TestValid {

    @Test
    public void testJavax(){
        User user = new User(1, null, 130, (byte) 1, null);
        user.setDiscount(11d);
//        Assert.assertTrue(ParamValidator.check(user));


    }

}
