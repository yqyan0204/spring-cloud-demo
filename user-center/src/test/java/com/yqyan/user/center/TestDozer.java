package com.yqyan.user.center;

import com.yqyan.model.User;
import com.yqyan.user.center.model.Person;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 11:22
 **/

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dozer.xml"})
public class TestDozer {

    @Autowired
    private Mapper mapper;


    @Test
    public void testMapping(){

        User user = new User(1, "max", 10, (byte) 1, null);
        Person person = mapper.map(user, Person.class);
        System.out.println("==========" + person);
    }



}
