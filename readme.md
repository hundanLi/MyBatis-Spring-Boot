### MyBatis与Spring Boot整合

- [参考文档](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html)
- [完整代码](https://github.com/hundanLi/MyBatis-Spring-Boot)

#### 1.1 pom依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```



#### 1.2 自动配置

MyBatis-Spring-Boot-Starter为我们干了一系列工作：

- 自动检测数据源DataSource
- 创建并注册一个SqlSessionFactory实例：使用SqlSessionFactoryBean和数据源创建
- 创建并注册从SqlSessionFactory中获取的SqlSessionTemplate的实例
- 自动扫描映射器，将它们链接到SqlSessionTemplate并将它们注册到IOC容器，以便可以将它们注入到bean中，前提是Mapper接口添加了`@Mapper`注解。

实现细节可查看`org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration`类。

#### 1.3 基础配置

必须配置数据源和MyBatis映射文件位置（如有），`application.xml`文件内容：

```yaml
spring:
  # 数据源配置
  datasource:
    url: jdbc:mysql://localhost/mybatis_learning?serverTimezone=Asia/Shanghai
    username: root
    password: root


# MyBatis配置
mybatis:
  # SQL映射文件路径，classpath相对路径
  mapper-locations: mapper/*.xml
  # 类型别名包
  type-aliases-package: com.tcl.msb.entity
```

更多配置可以参见类`org.mybatis.spring.boot.autoconfigure.MybatisProperties`。

#### 1.4 代码示例

Mapper接口：

```java
@Mapper
public interface BlogMapper {
    List<Blog> selectAll();
}
```

SQL映射文件：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tcl.msb.mapper.BlogMapper">

    <select id="selectAll" resultType="com.tcl.msb.entity.Blog">
        select * from blog
    </select>

</mapper>
```

运行测试：

```java
@SpringBootTest
class MybatisSpringBootApplicationTests {

    @Autowired
    BlogMapper mapper;

    @Test
    void getStarted() {
        mapper.selectAll().forEach(System.out::println);
    }

}
```

