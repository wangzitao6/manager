# skills/router.md

# Skill Router

本文件用于将“任务类型”映射到对应的 skill。  
使用方式：先判断任务属于哪一类，再选择一个或多个 skill 加载。

---

## 1. 项目理解类任务

### 任务
- 分析项目目录结构
- 判断 Spring Boot 工程类型
- 识别启动类、配置类、基础依赖
- 生成工程总览文档

### 对应 skill
- `springboot-base`

---

## 2. 数据库访问类任务

### 任务
- 编写 CRUD
- 设计实体类、Mapper、XML
- 编写复杂 SQL
- 排查 MyBatis 映射问题
- 设计 MongoDB 文档访问逻辑

### 对应 skill
- `mybatis-access`
- `mongodb-access`

### 推荐优先级
1. 先加载 `springboot-base`
2. 再加载 `mybatis-access` 或 `mongodb-access`

---

## 3. 缓存类任务

### 任务
- Redis key 设计
- 缓存读写
- TTL 设置
- 缓存更新策略
- 缓存穿透/击穿/雪崩防护

### 对应 skill
- `redis-cache`

### 推荐优先级
1. `springboot-base`
2. `redis-cache`

---

## 4. Web 横切能力任务

### 任务
- AOP 切面
- 请求日志
- 方法耗时统计
- 拦截器
- 登录鉴权
- 白名单放行

### 对应 skill
- `aop-interceptor`

### 推荐优先级
1. `springboot-base`
2. `aop-interceptor`

---

## 5. 接口文档任务

### 任务
- Swagger 配置
- Controller 注解补全
- DTO / VO 字段说明
- 接口说明文档生成

### 对应 skill
- `swagger-doc`

### 推荐优先级
1. `springboot-base`
2. `swagger-doc`

---

## 6. 第三方集成任务

### 任务
- OSS 文件上传
- 短信发送
- 外部 HTTP API 调用
- SDK 封装
- 第三方配置管理

### 对应 skill
- `thirdparty-integration`

### 推荐优先级
1. `springboot-base`
2. `thirdparty-integration`

---

## 7. 组合任务映射

### 任务：新增一个带分页查询的接口并输出 Swagger 文档
加载：
- `springboot-base`
- `mybatis-access`
- `swagger-doc`

### 任务：新增 Redis 缓存并记录请求耗时
加载：
- `springboot-base`
- `redis-cache`
- `aop-interceptor`

### 任务：实现短信发送接口并补充文档
加载：
- `springboot-base`
- `thirdparty-integration`
- `swagger-doc`

### 任务：为 MongoDB 查询接口补充文档
加载：
- `springboot-base`
- `mongodb-access`
- `swagger-doc`

---

## 8. Load Strategy

### 基础规则
- 任何任务都先看 `springboot-base`
- 如果任务涉及持久化，再看数据类 skill
- 如果任务涉及 API 暴露，再看 `swagger-doc`
- 如果任务涉及横切逻辑，再看 `aop-interceptor`
- 如果任务涉及外部系统，再看 `thirdparty-integration`

---

## 9. Avoid

不要同时无差别加载全部 skill，建议按任务类型精准选择：

- 结构分析，不要加载数据层 skill
- SQL 任务，不要默认加载 Redis skill
- 文档任务，不要默认加载第三方集成 skill

---

## 10. Expansion Rule

后续如果新增 skill，例如：

- `transaction-safety`
- `file-upload`
- `validation`
- `security`

应在此 router 文件中同步补充对应映射，并在 `index.md` 更新目录。
