# aop-interceptor Skill

## ID
aop-interceptor

## Category
web-cross-cutting

## DependsOn
- springboot-base

## Related
- redis-cache
- swagger-doc
- transaction-safety
- validation

## Purpose

用于分析和生成 Spring Boot 中的横切能力相关代码，包括：

- AOP 切面
- 请求日志
- 方法耗时统计
- 参数打印
- 权限校验
- 登录拦截
- 白名单放行
- 请求/响应预处理
- 统一上下文信息注入

该 skill 适用于本仓库中与横切逻辑相关的模块，尤其是：

- `src/main/java/com/wzt/demo/aop`
- `src/main/java/com/wzt/demo/interceptor`
- `src/main/java/com/wzt/demo/config`
- 与请求链路、日志、鉴权相关的代码

---

## Trigger

当出现以下场景时，启用该 skill：

- 需要编写 AOP 切面
- 需要统计接口耗时
- 需要打印请求参数和响应结果
- 需要做登录拦截或权限拦截
- 需要做接口白名单控制
- 需要做统一请求上下文处理
- 需要排查请求链路中的日志或异常
- 需要把公共逻辑从业务代码中抽离

---

## Input

优先读取以下内容：

- `src/main/java/com/wzt/demo/aop`
- `src/main/java/com/wzt/demo/interceptor`
- `src/main/java/com/wzt/demo/config`
- `src/main/java/com/wzt/demo/utils`
- `pom.xml`
- 相关 Controller
- 相关注解定义
- 日志配置
- 安全/认证相关配置

如果可用，还应参考：

- 请求路径规则
- 白名单列表
- 登录态存储方式
- MDC / TraceId 方案
- HandlerInterceptor / Filter / Aspect 使用方式

---

## Output

该 skill 应输出以下内容之一或多个：

- AOP 切面类
- 拦截器类
- 配置注册类
- 自定义注解
- 请求日志方案
- 耗时统计方案
- 鉴权与放行规则
- 调试与排查建议
- 横切能力设计说明

---

## Rules

### 1. 区分 AOP、Interceptor、Filter 的职责
不要混用概念：

- **AOP**：方法级别增强
- **Interceptor**：Spring MVC 请求级别拦截
- **Filter**：Servlet 层过滤

应根据实际场景选择合适的方式，不要滥用。

### 2. 不要默认存在鉴权体系
如果项目中没有明确认证或权限代码，不要直接假设有登录态、Token 校验或用户上下文。

### 3. 日志输出要可控
请求日志、响应日志、参数日志应避免：

- 过度打印
- 敏感信息泄露
- 大对象全量输出
- 生产环境噪声过大

### 4. 拦截规则要明确
对于登录拦截、白名单、权限控制，应明确：

- 哪些路径放行
- 哪些路径拦截
- 拦截失败如何响应
- 是否区分 GET / POST
- 是否区分静态资源和 API

### 5. AOP 切点要精准
切点表达式应避免过宽，防止误拦截不相关方法。

### 6. 不要把业务逻辑写进横切层
AOP 和 Interceptor 应只处理横切职责，不应承载核心业务流程。

---

## Steps

### Step 1: 识别横切需求

判断当前需求属于以下哪类：

- 记录日志
- 统计耗时
- 鉴权校验
- 参数校验前置
- 请求上下文注入
- 统一响应处理
- 请求频率控制

---

### Step 2: 选择实现方式

根据场景选择：

- 方法增强：AOP
- 请求入口拦截：Interceptor
- 低层请求过滤：Filter

如果多个层级都可实现，优先选择最贴近业务边界且最容易维护的方式。

---

### Step 3: 设计切点或拦截路径

对于 AOP：

- 明确包路径
- 明确注解范围
- 明确方法签名范围

对于 Interceptor：

- 明确 `addPathPatterns`
- 明确 `excludePathPatterns`
- 明确顺序

---

### Step 4: 设计日志与上下文

如果需要日志能力，应明确：

- 请求开始日志
- 请求结束日志
- 耗时统计
- 异常日志
- traceId / requestId
- 用户标识

若项目已有日志链路方案，应优先复用。

---

### Step 5: 设计鉴权与放行

如果涉及登录拦截，应明确：

- token 从哪里读取
- token 如何校验
- 用户信息如何注入上下文
- 失败时返回什么状态
- 是否支持匿名访问

---

### Step 6: 注册到 Spring 容器

确认实现类是否需要：

- `@Component`
- `@Configuration`
- `@Bean`
- `WebMvcConfigurer`
- `@Aspect`

并检查是否已被扫描到。

---

### Step 7: 验证副作用

检查是否会影响：

- 接口性能
- 异常传播
- 事务边界
- 重复执行
- 重入问题
- 多线程上下文丢失

---

## Examples

### Example 1: 接口耗时统计

输入：

```text
需要记录所有 Controller 方法耗时
```

输出方向：

- 使用 AOP 包裹 Controller 层
- 输出方法名、入参、耗时、返回结果摘要
- 控制日志级别，避免生产过量输出

---

### Example 2: 登录拦截

输入：

```text
除了登录接口，其他接口都要校验 token
```

输出方向：

- 使用 `HandlerInterceptor`
- 配置白名单路径
- 从请求头中读取 token
- 校验失败直接返回未授权响应

---

### Example 3: 方法级统一日志

输入：

```text
需要在 service 层记录业务方法耗时
```

输出方向：

- 使用 AOP
- 通过自定义注解或包路径切点控制范围
- 记录方法入参和耗时

---

### Example 4: 请求上下文注入

输入：

```text
需要在请求进入后保存当前用户信息
```

输出方向：

- 使用 Interceptor 或 Filter
- 在 preHandle 中解析 token
- 将用户信息放入 ThreadLocal
- 在 afterCompletion 中清理上下文

---

## Pitfalls

### 1. 切点过宽
如果切点写得太宽，可能会拦截到：

- 框架内部方法
- 工具类方法
- 非目标业务方法

### 2. 忘记清理上下文
如果使用 ThreadLocal 保存上下文信息，必须在请求完成后清理，否则可能导致内存泄漏或数据串扰。

### 3. 日志过多
请求和响应全量打印容易导致：

- 日志膨胀
- 性能下降
- 敏感信息泄露

### 4. 拦截顺序错误
多个拦截器同时存在时，要注意执行顺序和放行逻辑。

### 5. 误把业务逻辑放进切面
切面中只应处理横切逻辑，不应承载复杂业务判断。

### 6. 对异常处理不完整
如果拦截器或 AOP 抛异常，可能导致请求直接失败，应明确异常处理与统一返回策略。

---

## Recommended Outputs

当使用该 skill 时，建议产出以下文件或内容：

- `aop/*.java`
- `interceptor/*.java`
- `config/*.java`
- 自定义注解
- 请求日志设计说明
- 鉴权拦截说明
- 横切能力规范文档

---

## Scope

该 skill 适用于以下范围：

- AOP 切面
- 拦截器
- 请求日志
- 鉴权
- TraceId
- 请求上下文
- 统一预处理

不适用于：

- 数据库 CRUD
- Redis 缓存策略本身
- 第三方 SDK 深度实现
- 前端页面开发
