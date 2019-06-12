# Spring Cloud 全家桶演示demo

## 组件列表

*[x] Eureka
*[x] Ribbon
*[x] Hystrix
*[x] Feign
*[x] Zuul
*[ ] Config
*[ ] Bus
*[ ] Stream
*[ ] Sleuth

## 模块划分

| 模块 | 使用组件 | 功能 |
| --- | --- | --- |
| eureka-server | eureka-server | 服务注册和发现中心 |
| user-center | eureka-client | 用户中心，提供用户管理功能 |
| portal | eureka-client, ribbon, hystrix | 门户入口， 调用用户中心服务 |
| admin | eureka-client, feign | 管理入口，调用用户中心服务 |
| api-gateway | eureka-client, zuul | API网关 |
| user-api | | 用户服务接口，供feign服务提供者和使用使用 | 
| model | | 公共java bean |  

## 项目启动

1. 配置本地数据库。
    执行 user-center/src/main/resources/database.sql 建库建表。修改 db.properties 更改数据库配置
2. 编译项目。
    
    ```sh
    cd spring-cloud-demo
     mvn clean install -DskipTests 
    ```
3. 启动 eureka-server
    
    ```sh
    cd spring-cloud-demo/eureka-server
    # 启动两个节点，演示高可用
    mvn spring-boot:run -Dspring.profiles.active=node1
    mvn spring-boot:run -Dspring.profiles.active=node2
    ```     
    访问地址：http://localhost:1111/, http://localhost:1112/
    
4. 启动 user-center

    ```
    cd spring-cloud-demo/user-center
    # 启动两个节点，演示高可用
    mvn spring-boot:run -Dspring.profiles.active=dev
    mvn spring-boot:run -Dspring.profiles.active=test
    ```
    访问地址：http://localhost:8081/user/1, http://localhost:8082/user/1
    
    
5. 启动 portal
 
    ```
    cd spring-cloud-demo/portal
    mvn spring-boot:run
    ```   
    访问地址：http://localhost:9000/user/1
6. 启动 admin
    
    ```
    cd spring-cloud-demo/admin
    mvn spring-boot:run
    ```    
    
    访问地址：http://localhost:9001/admin/1
    
7. 启动 api-gateway

    ```
    cd spring-cloud-demo/api-gateway
    mvn spring-boot:run
    ```
    
    访问地址：http://localhost:5555/api-gateway/user-center/user/1  
    http://localhost:5555/api-gateway/portal/user/1  
    http://localhost:5555/api-gateway/admin/admin/1  
    