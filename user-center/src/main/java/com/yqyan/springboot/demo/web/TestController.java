package com.yqyan.springboot.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yanyaqiang
 * @Date 2019/4/12 18:00
 **/

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/test")
    public String home(){
        return "Hello World";
    }

    @GetMapping("/services")
    public List<String> getAllServices(){
       return client.getServices();
    }

    @GetMapping("/services/{id}")
    public List<String> getServiceById(@PathVariable("id") String serviceId){
        List<String> services = client.getInstances(serviceId).stream()
                .map(this::service2String).collect(Collectors.toList());
        return services;
    }

    private String service2String(ServiceInstance instance){
        return String.format("service_id: %s, host: %s, port: %s", instance.getServiceId(),
                instance.getHost(), instance.getPort());
    }

}
