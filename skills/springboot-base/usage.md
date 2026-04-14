# Examples: Spring Boot 基础工程与通用能力集成

本文给出该项目中常见能力的示例用法，涵盖项目启动、数据库访问、Redis 缓存、Swagger2、AOP、拦截器、OSS、短信、文件上传、JSON 处理、工具类使用、事务控制和常见业务组合流程等场景。

---

## 1. 项目启动示例

### 1.1 启动类
Spring Boot 启动类通常如下：

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 1.2 启动方式
常见启动方式如下：

#### 方式一：IDE 启动
直接运行 `Application.java`。

#### 方式二：Maven 启动
```bash
mvn spring-boot:run
```

#### 方式三：打包后启动
```bash
mvn clean package
java -jar target/mybatis-0.0.1-SNAPSHOT.jar
```

### 1.3 访问地址
根据当前配置，启动后基础访问路径通常为：

```text
http://localhost:9070/louxe-open-api
```

---

## 2. MySQL + MyBatis-Plus 查询示例

### 2.1 实体类示例
```java
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
}
```

### 2.2 Mapper 接口示例
```java
public interface UserMapper extends BaseMapper<User> {
}
```

### 2.3 Service 示例
```java
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    public List<User> listAll() {
        return userMapper.selectList(null);
    }
}
```

### 2.4 Controller 示例
```java
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/list")
    public List<User> listUser() {
        return userService.listAll();
    }
}
```

### 2.5 常见操作
```java
userMapper.insert(user);
userMapper.deleteById(id);
userMapper.updateById(user);
userMapper.selectById(id);
userMapper.selectList(null);
```

---

## 3. XML Mapper 查询示例

### 3.1 Mapper 接口
```java
public interface StudentMapper {
    Student findByName(@Param("name") String name);
}
```

### 3.2 XML 文件
```xml
<mapper namespace="com.wzt.mybatis.mapper.StudentMapper">
    <select id="findByName" resultType="com.wzt.mybatis.entity.Student">
        SELECT id, name, age
        FROM student
        WHERE name = #{name}
    </select>
</mapper>
```

### 3.3 调用示例
```java
@Autowired
private StudentMapper studentMapper;

public Student queryStudent(String name) {
    return studentMapper.findByName(name);
}
```

### 3.4 使用场景
- 多表联查
- 条件动态拼接
- 排序分页
- 复杂统计查询

---

## 4. Redis 缓存示例

### 4.1 字符串缓存
```java
@Autowired
private StringRedisTemplate stringRedisTemplate;

public void saveCache(String key, String value) {
    stringRedisTemplate.opsForValue().set(key, value);
}

public String getCache(String key) {
    return stringRedisTemplate.opsForValue().get(key);
}
```

### 4.2 对象缓存
```java
@Autowired
private RedisTemplate<String, Object> redisTemplate;

public void saveUser(User user) {
    redisTemplate.opsForValue().set("user:" + user.getId(), user);
}

public User getUser(Long id) {
    return (User) redisTemplate.opsForValue().get("user:" + id);
}
```

### 4.3 带缓存查询逻辑
```java
public User getUserWithCache(Long id) {
    String key = "user:" + id;
    User user = (User) redisTemplate.opsForValue().get(key);
    if (user != null) {
        return user;
    }

    user = userMapper.selectById(id);
    if (user != null) {
        redisTemplate.opsForValue().set(key, user);
    }
    return user;
}
```

### 4.4 设置过期时间
```java
redisTemplate.opsForValue().set("token:" + id, token, 30, TimeUnit.MINUTES);
```

### 4.5 使用场景
- 热点数据缓存
- 验证码缓存
- 登录态缓存
- 临时状态缓存

---

## 5. Session 共享示例

### 5.1 登录写入 Session
```java
@PostMapping("/login")
public String login(HttpSession session) {
    session.setAttribute("username", "admin");
    return "login success";
}
```

### 5.2 读取 Session
```java
@GetMapping("/current-user")
public Object currentUser(HttpSession session) {
    return session.getAttribute("username");
}
```

### 5.3 判断登录状态
```java
@GetMapping("/check-login")
public String checkLogin(HttpSession session) {
    Object username = session.getAttribute("username");
    if (username == null) {
        return "not login";
    }
    return "login user: " + username;
}
```

### 5.4 使用场景
- 登录态保存
- 用户临时状态保存
- 多实例部署会话共享

---

## 6. Swagger2 接口文档示例

### 6.1 Controller 注解示例
```java
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("根据 ID 查询用户")
    @GetMapping("/{id}")
    public User getById(@ApiParam("用户ID") @PathVariable Long id) {
        return userService.getById(id);
    }
}
```

### 6.2 请求对象注解示例
```java
@Data
public class LoginRequest {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String code;
}
```

### 6.3 使用场景
- 接口说明
- 接口调试
- 前后端联调
- 接口文档生成

### 6.4 访问方式
Swagger UI 地址通常类似：

```text
http://localhost:9070/louxe-open-api/swagger-ui.html
```

> 具体路径取决于 Swagger 配置类。

---

## 7. AOP 切面示例

### 7.1 日志切面
```java
@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.wzt.mybatis.service.*.*(..))")
    public void before() {
        System.out.println("方法执行前");
    }

    @After("execution(* com.wzt.mybatis.service.*.*(..))")
    public void after() {
        System.out.println("方法执行后");
    }

    @Around("execution(* com.wzt.mybatis.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        return result;
    }
}
```

### 7.2 使用场景
- 操作日志
- 方法耗时统计
- 权限增强
- 统一异常预处理

---

## 8. 拦截器示例

### 8.1 登录拦截器
```java
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("username");
        if (user == null) {
            response.setStatus(401);
            response.getWriter().write("unauthorized");
            return false;
        }
        return true;
    }
}
```

### 8.2 注册拦截器
```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/webjars/**"
                );
    }
}
```

### 8.3 使用场景
- 登录校验
- 权限判断
- 请求审计
- 黑名单控制

---

## 9. OSS 文件上传示例

### 9.1 上传服务封装
```java
@Service
public class OssService {

    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("file is empty");
        }

        String fileName = file.getOriginalFilename();
        return "https://oss.example.com/" + fileName;
    }
}
```

### 9.2 控制器示例
```java
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return ossService.upload(file);
    }
}
```

### 9.3 使用场景
- 图片上传
- 附件上传
- 静态资源托管
- 文件外链生成

### 9.4 注意事项
- 文件名建议唯一化
- 注意 bucket 权限
- 注意上传失败异常处理

---

## 10. 阿里云短信示例

### 10.1 短信服务
```java
@Service
public class SmsService {

    public void sendCode(String phone, String code) {
        // 调用阿里云短信 SDK
        // 发送验证码
    }
}
```

### 10.2 控制器示例
```java
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String send(@RequestParam String phone) {
        smsService.sendCode(phone, "123456");
        return "success";
    }
}
```

### 10.3 与 Redis 组合示例
```java
@PostMapping("/send-code")
public String sendCode(@RequestParam String phone) {
    String code = RandomUtil.randomNumbers(6);
    stringRedisTemplate.opsForValue().set("sms:code:" + phone, code, 5, TimeUnit.MINUTES);
    smsService.sendCode(phone, code);
    return "success";
}
```

### 10.4 使用场景
- 验证码登录
- 找回密码
- 通知短信
- 风控校验

---

## 11. 文件上传示例

### 11.1 单文件上传
```java
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "file is empty";
        }
        return file.getOriginalFilename();
    }
}
```

### 11.2 多文件上传
```java
@PostMapping("/uploads")
public List<String> uploads(@RequestParam("files") MultipartFile[] files) {
    List<String> names = new ArrayList<>();
    for (MultipartFile file : files) {
        names.add(file.getOriginalFilename());
    }
    return names;
}
```

### 11.3 校验文件大小
```java
@PostMapping("/upload-check")
public String uploadCheck(@RequestParam("file") MultipartFile file) {
    if (file.getSize() > 10 * 1024 * 1024) {
        return "file too large";
    }
    return file.getOriginalFilename();
}
```

### 11.4 使用场景
- 图片上传
- Excel 导入
- 文档上传
- 压缩包上传

---

## 12. JSON 返回示例

### 12.1 统一响应对象
```java
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMsg("success");
        r.setData(data);
        return r;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<>();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }
}
```

### 12.2 使用方式
```java
@GetMapping("/detail/{id}")
public Result<User> detail(@PathVariable Long id) {
    User user = userService.getById(id);
    return Result.ok(user);
}
```

---

## 13. Fastjson 示例

### 13.1 对象转 JSON
```java
User user = new User();
user.setId(1L);
user.setUsername("admin");

String json = JSON.toJSONString(user);
System.out.println(json);
```

### 13.2 JSON 转对象
```java
String json = "{\"id\":1,\"username\":\"admin\"}";
User user = JSON.parseObject(json, User.class);
```

### 13.3 使用场景
- 接口调试
- 数据缓存
- 对象序列化
- 临时格式转换

---

## 14. Hutool 工具示例

### 14.1 字符串工具
```java
boolean empty = StrUtil.isEmpty("");
```

### 14.2 日期工具
```java
String now = DateUtil.now();
```

### 14.3 随机工具
```java
String code = RandomUtil.randomNumbers(6);
```

### 14.4 集合工具
```java
boolean isEmpty = CollUtil.isEmpty(list);
```

### 14.5 使用场景
- 验证码生成
- 日期处理
- 字符串校验
- 集合判空

---

## 15. Lombok 示例

### 15.1 实体类简写
```java
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
}
```

### 15.2 构造方法注解
```java
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    private Long id;
    private String name;
}
```

### 15.3 使用场景
- DTO
- VO
- Entity
- 请求对象

---

## 16. 配置读取示例

### 16.1 使用 `@Value`
```java
@Value("${server.port}")
private String port;
```

### 16.2 使用 `@ConfigurationProperties`
```java
@Component
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
}
```

### 16.3 使用场景
- 数据源配置
- Redis 配置
- 第三方平台配置
- 文件上传配置

---

## 17. 事务示例

### 17.1 基本写法
```java
@Service
public class OrderService {

    @Transactional
    public void createOrder() {
        // 插入订单
        // 扣减库存
        // 写入日志
    }
}
```

### 17.2 异常回滚示例
```java
@Transactional
public void updateData() {
    userMapper.updateById(user);
    if (true) {
        throw new RuntimeException("error");
    }
}
```

### 17.3 使用场景
- 订单创建
- 库存扣减
- 批量写入
- 关联数据维护

---

## 18. 日志示例

### 18.1 使用 slf4j
```java
private static final Logger log = LoggerFactory.getLogger(UserService.class);

public void test() {
    log.info("开始执行");
    log.error("发生异常");
}
```

### 18.2 使用场景
- 请求日志
- 业务日志
- 错误日志
- 第三方调用日志

### 18.3 注意事项
- 不要打印密码、密钥、验证码
- 不要输出过多无意义日志
- 生产环境应控制日志级别

---

## 19. 常见业务组合示例

### 19.1 用户登录流程
1. 前端提交手机号和验证码
2. 后端从 Redis 中读取验证码
3. 验证码正确后写入 Session
4. 返回登录成功结果

```java
@PostMapping("/login")
public Result<String> login(@RequestParam String phone, @RequestParam String code, HttpSession session) {
    String cacheCode = stringRedisTemplate.opsForValue().get("sms:code:" + phone);
    if (cacheCode == null || !cacheCode.equals(code)) {
        return Result.fail("验证码错误");
    }
    session.setAttribute("phone", phone);
    return Result.ok("login success");
}
```

### 19.2 缓存查询流程
1. 先查 Redis
2. 未命中查数据库
3. 回写 Redis
4. 返回结果

### 19.3 文件上传流程
1. 接收 MultipartFile
2. 校验文件大小和类型
3. 上传到 OSS 或本地
4. 返回文件地址

### 19.4 文档联调流程
1. 打开 Swagger UI
2. 填写请求参数
3. 发送请求
4. 查看返回结果
5. 修正接口说明

---

## 20. 目录级示例对应关系

### `aop/`
- 切面日志
- 耗时统计
- 权限增强

### `bean/`
- 实体类
- DTO
- VO
- 返回对象

### `cache/`
- Redis 读写封装
- 缓存 key 管理
- 过期时间控制

### `config/`
- Swagger 配置
- Redis 配置
- MyBatis 配置
- WebMvc 配置

### `interceptor/`
- 登录拦截
- 权限控制
- 请求预处理

### `thirdpart/`
- OSS 封装
- 短信封装
- 外部 API 封装

### `utils/`
- 日期工具
- JSON 工具
- 字符串工具
- 文件工具

---

## 21. 调试示例

### 21.1 数据库调试
- 确认数据库启动
- 检查连接串是否正确
- 检查 Mapper XML 是否被加载
- 检查实体类包路径是否匹配配置

### 21.2 Redis 调试
- 确认 Redis 可连通
- 检查 key 是否正确
- 检查序列化是否一致
- 检查是否设置过期时间

### 21.3 第三方服务调试
- 检查 AccessKey 是否正确
- 检查接口域名是否正确
- 检查账号权限是否足够
- 检查返回异常是否被捕获

---

## 22. 备注
以上示例按当前项目技术栈抽象整理，具体实现以源码中的实际类、包名和配置为准。
