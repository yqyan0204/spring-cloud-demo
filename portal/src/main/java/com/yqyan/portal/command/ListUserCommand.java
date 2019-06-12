package com.yqyan.portal.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.yqyan.portal.common.Constants;
import com.yqyan.portal.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/5/31 09:20
 **/

public class ListUserCommand extends HystrixCommand<List<User>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUserCommand.class);

    private RestTemplate restTemplate;

    public ListUserCommand(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("userGroup"));
        this.restTemplate = restTemplate;
    }


    @Override
    protected List<User> run() throws Exception {
        LOGGER.info("ListUserCommand start");
        return  restTemplate.getForObject(Constants.USER_CENTER_URL_PREFIX + "listAll", List.class);
    }

    /**
     * fallback方法
     * @return
     */
    @Override
    protected List<User> getFallback() {
        return Arrays.asList(new User());
    }
}
