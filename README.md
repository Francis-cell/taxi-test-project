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





### 2、项目相关接口的开发

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

