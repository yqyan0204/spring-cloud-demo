package com.yqyan.portal.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.yqyan.portal.common.Constants;
import com.yqyan.portal.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/5/31 10:15
 **/

public class ListUserObservableCommand extends HystrixObservableCommand<List<User>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUserObservableCommand.class);

    private RestTemplate restTemplate;

    public ListUserObservableCommand(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("userGroup"));
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<List<User>> construct() {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    LOGGER.info("================= construct start...");

                    List<User> users = restTemplate.getForObject(Constants.USER_CENTER_URL_PREFIX + "listAll", List.class);
                    LOGGER.info("================= listUsers: {}", users);

                    subscriber.onNext(users);
                    subscriber.onCompleted();

                    LOGGER.info("================= construct finished");
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * fallback 方法
     * @return
     */
    @Override
    protected Observable<List<User>> resumeWithFallback() {
        LOGGER.info("ListUserObservableCommand fallback start");
        return Observable.just(Arrays.asList(new User()));
    }
}
