## 项目学习流程

### 0、参考链接&&工具使用

#### 1、参考链接



#### 2、工具使用

##### 1、项目接口版本管理工具

APIFOX工具



##### 2、远程仓库

GitHub



### 1、maven项目搭建

#### 1、项目结构图

**<img src="https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230305165613726.png" alt="image-20230305165613726" style="zoom:80%;" />**



#### 2、父pom.xml配置

```xml
<!-- SpringBoot版本配置，可以到Spring官网上去选择 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.3</version>
</parent>
```



#### 3、子pom.xml配置

```xml
<!-- 子项目直接可以使用父项目中声明的SpingBoot版本信息 -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```





#### ==注意==：

每搭建一步，都测试一下项目是否能成功运行，这样方便排查问题所在！！！



#### Append

##### 1、关于dependencies和dependencyManager区别&使用

###### 1、dependencies

父项目：

```bash
声明
```

子项目：

```bash
"不需要声明"就可以引入相应的jar
```



###### 2、depenedencyManager

父项目：

```bash
只声明依赖，不实现引入；
父项目可以声明jar对应的版本version信息
```



子项目：

```bash
需要显式的声明，但是可以继承父项目的version信息，可以做到调控jar版本的作用
```





### 2、注册中心的搭建

#### 0、参考链接

1、[Nacos参考文档](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)



#### 1、搭建Nacos运行环境

##### 1、安装Nacos

```bash
git clone https://github.com/alibaba/nacos.git
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  
ls -al distribution/target/

// change the $version to your actual path
cd distribution/target/nacos-server-$version/nacos/bin
```



##### 2、启动服务器

- ==注==：Nacos的运行需要以至少2C4g60g*3的机器配置下运行。



**windows**

```cmd
startup.cmd -m standalone
```



**Linux**

```bash
# 启动命令(standalone代表着单机模式运行，非集群模式):
sh startup.sh -m standalone

# 如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：
bash startup.sh -m standalone
```



##### 3、配置流程

###### 1、配置maven依赖

```xml
<!-- 引入Nacos -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        <version>2.1.1.RELEASE</version>
    </dependency>
```

这里需要注意使用的Nacos版本--SpringCloud版本--Spring版本

参考网站：[毕业版本依赖关系(推荐使用)Nacos](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E#%E6%AF%95%E4%B8%9A%E7%89%88%E6%9C%AC%E4%BE%9D%E8%B5%96%E5%85%B3%E7%B3%BB%E6%8E%A8%E8%8D%90%E4%BD%BF%E7%94%A8)



我这里使用的Nacos版本是2.1.1RELEASE版本

**![image-20230306220911823](https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230306220911823.png)**

所以对应的SpringCloud版本为2.2.9RELEASE

**![image-20230306221004661](https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230306221004661.png)**

找到对应的Srping版本为2.3.12RELEASE



###### 2、配置yml文件

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  application:
    name: api-passenger
```



###### 3、Application文件添加@EnableDiscoveryClient注解

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ApiPassengerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class);
    }
}
```



###### 4、启动Nacos并登陆监听

**![image-20230306221203800](https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230306221203800.png)**









### 3、项目相关接口的开发

#### 1、用户获取验证码接口

##### 1、时序图

**<img src="https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230305170449490.png" alt="image-20230305170449490" style="zoom:80%;" />**



##### 2、==骨架==编写

###### 1、编写接口Controller层

```java
@RestController
public class VerificationCodeController {
    
    @Autowired
    private VerificationCodeService verificationCodeService;
    
    
    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCode) {
        String passengerPhone = verificationCode.getPassengerPhone();
        System.out.println("接收到的电话号码为：" + passengerPhone);
        
        return verificationCodeService.generateVerificationCode(passengerPhone);
    }
}
```



###### 2、编写实体类

```java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VerificationCodeDTO {
    /** 电话号码 */
    private String passengerPhone;
}
```



###### 3、编写服务Service层

```java
@Service
public class VerificationCodeService {
    
    public String generateVerificationCode(String passengerPhone) {
        // 调用验证码服务，获取验证码    
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";
        
        // 存入Redis
        System.out.println("存入Redis");
        
        // 创建JSON(在这里需要在pom.xml里面引入JSON)
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");

        // 返回值
        return result.toString();
    }
}
```



#### 2、用户发送验证码

##### 1、时序图

**<img src="https://raw.githubusercontent.com/Francis-cell/Picture/main/image-20230402091632723.png" alt="image-20230402091632723" style="zoom:80%;" />**



##### 2、骨架编写



