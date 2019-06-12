package com.yqyan.portal.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yqyan.portal.command.ListUserCommand;
import com.yqyan.portal.command.ListUserObservableCommand;
import com.yqyan.portal.model.User;
import com.yqyan.portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.yqyan.portal.common.Constants.USER_CENTER_URL_PREFIX;

/**
 * @Author yanyaqiang
 * @Date 2019/5/21 10:18
 **/

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 断路器超时时间默认为1s
     * @return
     */
    @Override
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),  //断路器超时时间: 2s
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")    //隔离策略, THREAD(默认) 或 SEMAPHORE
            },
            fallbackMethod = "listAllUsersFallback",    // 后备方法
            threadPoolKey = "userThreadPool",           //线程池名称
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),   //线程池中线程最大数量
                    @HystrixProperty(name = "maxQueueSize", value = "10")  //线程池请求队列长度
            }

    )
    public List<User> listAllUsers() {
        LOGGER.info("listAllUsers start");
        long before = System.currentTimeMillis();
        try {
            List<User> users = restTemplate.getForObject(USER_CENTER_URL_PREFIX + "listAll", List.class);
            LOGGER.info("listAllUsers response: {}", users);
            return users;
        }finally {
            LOGGER.info("listAllUsers cost time: {}", (System.currentTimeMillis() - before));
        }

    }

    private List<User> listAllUsersFallback(){
        LOGGER.info("listAllUsersFallback start");

        return Arrays.asList(new User());
    }

    @Override
    public List<User> listAllUsersCommand(Boolean async) {
        LOGGER.info("listAllUsersCommand start, async: {}", async);
        List<User> users = new ArrayList<>();
        long before = System.currentTimeMillis();
        try {
            if(async){
                // 异步执行
                Future<List<User>> future = new ListUserCommand(restTemplate).queue();
                sleep(1);
                users = future.get();
            }else {
                // 同步执行
                users = new ListUserCommand(restTemplate).execute();
            }

            LOGGER.info("listAllUsers response: {}", users);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
        } finally {
            LOGGER.info("listAllUsers cost time: {}", (System.currentTimeMillis() - before));
        }

        return users;
    }

    @Override
    public List<User> listAllUsersObservableCommand(Boolean immediate) {
        LOGGER.info("================= listAllUsersObservableCommand start, immediate: {}", immediate);
        final List<User> users = new ArrayList<>();
        long before = System.currentTimeMillis();
        try {
            if(immediate){
                // 异步执行，但是会立即执行 construct 方法
                Observable<List<User>> ob = new ListUserObservableCommand(restTemplate).observe();

                sleep(2);

                LOGGER.info("================= subscribe start...");
                ob.subscribe(usersParam -> {
                    LOGGER.info("================= subscribe1: {}", usersParam);
                    users.addAll(usersParam);
                });

                //允许重放 ！！！
                ob.subscribe(usersParam -> LOGGER.info("================= subscribe2: {}", usersParam));
                ob.subscribe(usersParam -> LOGGER.info("================= subscribe3: {}", usersParam));

            }else {
                // 异步执行，订阅时才执行 construct 方法
                Observable<List<User>> ob = new ListUserObservableCommand(restTemplate).toObservable();

                sleep(2);

                LOGGER.info("================= subscribe start...");
                ob.subscribe(usersParam -> {
                    LOGGER.info("================= subscribe1: {}", usersParam);
                    users.addAll(usersParam);
                });
                // 不允许重放，只能执行一次！！！
//                ob.subscribe(usersParam -> LOGGER.info("================= subscribe2: {}", usersParam));
//                ob.subscribe(usersParam -> LOGGER.info("================= subscribe3: {}", usersParam));
            }

            LOGGER.info("================= listAllUsers response: {}", users);
        }  finally {
            LOGGER.info("================= listAllUsers cost time: {}", (System.currentTimeMillis() - before));
        }
        return users;
    }

    @Override
    public User findUserById(Integer id){
        return restTemplate.getForObject(USER_CENTER_URL_PREFIX + "{1}", User.class, id);
    }

    @Override
    public Object addUser(Map<String, Object> user){
        return restTemplate.postForObject(USER_CENTER_URL_PREFIX + "add", user, Object.class);
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.error("sleep interrupt", e);
        }
    }
}
