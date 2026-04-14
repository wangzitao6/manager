# Skill: Spring Boot 基础工程与通用能力集成

## 1. 能力简介
该项目是一个基于 Spring Boot 的 Java 后端基础工程，集成了 MyBatis-Plus、MyBatis XML、MySQL、Redis、Spring Session、Swagger2、AOP、拦截器、阿里云 OSS、阿里云短信以及文件上传等常见能力，适合作为中后台服务的基础模板、技术示例工程或通用能力验证工程。

本 Skill 用于描述该项目所具备的基础工程能力、目录组织方式、技术栈构成、运行配置和通用开发流程，便于后续继续扩展业务模块或沉淀可复用代码。

## 2. 适用场景
- Spring Boot 基础项目搭建
- Java 后端工程模板沉淀
- MyBatis-Plus 与 XML Mapper 混合开发
- MySQL 数据访问与连接池管理
- Redis 缓存与 Session 共享
- Swagger2 接口文档生成
- AOP 与拦截器统一增强
- 阿里云 OSS 文件上传
- 阿里云短信服务接入
- 通用工具类与常量管理

## 3. 核心能力
### 3.1 项目启动与运行配置
- 使用 Spring Boot 作为核心启动框架
- 通过 `application.properties` 统一管理服务端口、上下文路径、数据源、缓存、第三方服务等配置
- 通过 `logback.xml` 管理日志输出规则

### 3.2 数据库访问能力
- 使用 MySQL 作为主数据库
- 使用 Druid 作为数据源连接池
- 使用 MyBatis-Plus 提供基础 CRUD 和增强能力
- 同时支持 XML Mapper 文件编写 SQL
- 支持实体类与数据库表字段映射
- 支持数据库自增主键策略

### 3.3 Redis 缓存与 Session 能力
- 使用 Redis 作为缓存存储
- 使用 Jedis 作为 Redis 客户端
- 使用 Spring Session Data Redis 支持 Session 共享
- 适用于缓存热点数据、临时状态数据和登录会话管理

### 3.4 接口文档能力
- 使用 Swagger2 自动生成接口文档
- 方便接口调试与前后端联调
- 降低接口说明和沟通成本

### 3.5 AOP 与拦截器能力
- 支持切面增强
- 可用于日志记录、耗时统计、统一异常增强、权限控制等
- 支持请求拦截器
- 可用于请求预处理、登录校验、参数检查等

### 3.6 第三方服务接入能力
- 集成阿里云 OSS
- 集成阿里云短信服务
- 通过配置项集中管理第三方服务密钥和参数
- 适合封装上传、通知、外部调用等能力

### 3.7 文件上传能力
- 支持较大文件上传
- 对 `multipart` 请求大小进行了显式配置
- 适合配合 OSS 或本地文件存储使用

### 3.8 通用工具与辅助能力
- 使用 Hutool 提供常用工具方法
- 使用 Fastjson 进行对象序列化与 JSON 处理
- 使用 Lombok 简化实体和 DTO 编写
- 使用 Velocity / Freemarker 支持模板渲染和代码生成场景

## 4. 目录映射
### 4.1 主包结构
项目主包从目录截图可见为以下风格：

- `com.wzt.demo`
  - `aop`：切面处理
  - `bean`：实体类和数据对象
  - `cache`：缓存相关封装
  - `config`：配置类
  - `example`：示例业务代码
  - `interceptor`：请求拦截器
  - `thirdpart`：第三方服务封装
  - `utils`：工具类

> 说明：配置文件中出现了 `com.wzt.mybatis.entity`，说明项目可能经历过包名调整或重构，具体包路径需要以源码中的实际实体类位置为准。

### 4.2 资源目录
- `resources/mapper/*.xml`：MyBatis XML 映射文件
- `resources/mapper/*Mapper.xml`：MyBatis-Plus Mapper 映射文件
- `resources/application.properties`：应用配置
- `resources/logback.xml`：日志配置

### 4.3 SQL 目录
- `sql/wang.sql`：数据库初始化脚本或测试数据脚本

## 5. 技术栈
- Java 8
- Spring Boot 2.0.2.RELEASE
- Maven
- MyBatis-Plus 2.1.9
- MyBatis XML
- MySQL
- Druid 数据源
- Redis
- Jedis
- Spring Session Data Redis
- Swagger2
- Fastjson 1.2.83
- Hutool 4.1.12
- Lombok 1.14.4
- Logback
- AspectJ Weaver
- Alibaba OSS SDK
- Alibaba SMS SDK
- Velocity
- Freemarker

## 6. 运行配置
### 6.1 服务配置
- 服务端口：`9070`
- 上下文路径：`/louxe-open-api`
- Session 超时时间：`300` 秒

### 6.2 数据源配置
- 数据库类型：MySQL
- 数据库连接池：Druid
- 数据库地址：本地或配置环境中的 `wang` 库
- 用户名：`root`
- 密码：`123456`

### 6.3 Redis 配置
- Redis 数据库索引：`0`
- Redis 地址：远程 Redis 服务
- Redis 端口：`16379`
- Redis 客户端：Jedis

### 6.4 第三方服务配置
- OSS 配置：通过 `oss.accessKeyId` 和 `oss.accessKeySecret` 注入
- 短信配置：通过 `sms.accessKeyId` 和 `sms.accessKeySecret` 注入
- 采用占位符形式管理敏感信息

### 6.5 文件上传配置
- 单文件最大：`3000Mb`
- 单请求最大：`3000Mb`

## 7. 工作流程
1. 应用启动，加载 Spring Boot 配置
2. 初始化 Druid 数据源和 MySQL 连接
3. 初始化 Redis 和 Spring Session 相关组件
4. 初始化 Swagger2 文档能力
5. 请求进入系统后先经过拦截器处理
6. 业务逻辑在 Service 层执行
7. 数据访问通过 MyBatis-Plus 或 XML Mapper 完成
8. 需要缓存的场景读写 Redis
9. 需要共享会话的场景使用 Spring Session
10. 需要文件上传的场景调用上传能力
11. 需要外部存储或通知的场景调用 OSS 或短信服务
12. AOP 对关键业务点进行增强
13. 返回 JSON 接口响应

## 8. 关键数据对象
从当前目录和配置可推断项目中存在以下类型对象：

- `User`
- `Student`
- 各类实体类
- 请求对象
- 响应对象
- DTO / VO / POJO

## 9. 输入输出
### 输入
- HTTP 请求参数
- JSON 请求体
- 数据库查询条件
- Redis key/value
- 文件上传内容
- OSS / 短信服务参数
- 配置文件中的运行参数

### 输出
- JSON 格式接口响应
- 数据库写入结果
- Redis 缓存结果
- Session 状态
- 文件上传结果
- 日志输出
- Swagger 接口说明

## 10. 约束与注意事项
- MyBatis XML 文件路径必须与 `mapper-locations` 配置一致
- MyBatis-Plus 实体包路径需与 `typeAliasesPackage` 保持一致
- Redis 连接地址、端口和认证信息必须正确
- OSS 和短信的密钥信息应避免硬编码在仓库中
- 文件上传大小配置较大，需注意服务器磁盘和带宽压力
- Session 超时时间较短，适合临时会话场景
- MongoDB 当前只是注释配置，不应视为已启用能力
- 项目中存在敏感信息占位符和明文示例值，正式环境应进行脱敏和外部化管理

## 11. 可复用能力
本项目可沉淀为以下通用模板：
- Spring Boot 基础工程模板
- MyBatis-Plus CRUD 模板
- MyBatis XML 映射模板
- Redis 缓存与 Session 模板
- Swagger2 接口文档模板
- AOP 和拦截器模板
- 阿里云 OSS 接入模板
- 阿里云短信接入模板
- 文件上传模板
- 通用工具类模板

## 12. 示例流程
### 示例 1：数据库查询流程
- Controller 接收请求
- Service 处理业务逻辑
- Mapper 通过 XML 或 MyBatis-Plus 查询数据库
- 返回 JSON 结果

### 示例 2：缓存访问流程
- 先从 Redis 查询数据
- 若缓存未命中，则访问 MySQL
- 将结果回写 Redis
- 返回最终结果

### 示例 3：会话管理流程
- 用户登录后生成 Session
- Session 信息写入 Redis
- 后续请求共享读取 Session
- Session 超时后自动失效

### 示例 4：文件上传流程
- 前端提交文件请求
- 后端接收 multipart 文件
- 校验大小与格式
- 上传至 OSS 或本地存储
- 返回访问地址或上传结果

## 13. 待补充信息
以下内容需要结合源码进一步补全：
- `Application.java` 的启动包和扫描范围
- `@MapperScan` 的具体配置方式
- `Swagger2` 的实际配置类
- `AOP` 和拦截器的切入点与规则
- `cache` 包的具体实现
- `thirdpart` 包接入的具体服务封装
- `example` 包中的业务示例
- `README.md` 中的项目说明和启动方法
- 实体类包路径与配置中 `typeAliasesPackage` 的最终一致性
