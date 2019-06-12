package com.yqyan.admin.service;

import com.yqyan.admin.config.DisableHystrixConfiguration;
import com.yqyan.user.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author yanyaqiang
 * @Date 2019/6/10 20:17
 **/

@FeignClient(value = "user-center")
public interface FeignUserService extends UserService {
}
