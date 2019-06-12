package com.yqyan.user.center.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author yanyaqiang
 * @Date 2019/5/14 13:40
 **/

@Component
public class Constants {

    @Value("${spring.application.name}")
    private String userCenterServiceId;

    public String getUserCenterServiceId() {
        return userCenterServiceId;
    }

    public void setUserCenterServiceId(String userCenterServiceId) {
        this.userCenterServiceId = userCenterServiceId;
    }
}
