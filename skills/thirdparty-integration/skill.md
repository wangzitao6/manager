# thirdparty-integration Skill

## ID
thirdparty-integration

## Category
integration

## DependsOn
- springboot-base

## Related
- swagger-doc
- aop-interceptor
- redis-cache
- validation

## Purpose

用于分析和生成第三方系统集成相关能力，包括：

- OSS / 对象存储上传下载
- 短信发送
- 邮件发送
- 外部 HTTP API 调用
- 第三方 SDK 封装
- 回调处理
- 签名验签
- 配置隔离
- 超时与重试策略

该 skill 适用于本仓库中所有与外部系统交互的模块，尤其是：

- `src/main/java/com/wzt/demo/integration`
- `src/main/java/com/wzt/demo/client`
- `src/main/java/com/wzt/demo/service`
- `src/main/java/com/wzt/demo/config`
- 与第三方平台交互的 Controller、Util、Adapter、SDK 封装代码

---

## Trigger

当出现以下场景时，启用该 skill：

- 需要接入短信平台
- 需要接入对象存储
- 需要调用外部 HTTP 接口
- 需要封装第三方 SDK
- 需要处理回调通知
- 需要设计签名、验签、重试机制
- 需要统一第三方配置管理
- 需要排查第三方调用失败问题

---

## Input

优先读取以下内容：

- `src/main/java/com/wzt/demo/integration`
- `src/main/java/com/wzt/demo/client`
- `src/main/java/com/wzt/demo/config`
- `src/main/java/com/wzt/demo/service`
- `pom.xml`
- 第三方相关配置文件
- HTTP 调用代码
- SDK 初始化代码
- 回调接口代码
- 日志输出与异常处理代码

如果可用，还应参考：

- 第三方平台文档
- 接口地址
- 请求/响应格式
- 签名规则
- 重试规则
- 超时要求
- 鉴权方式

---

## Output

该 skill 应输出以下内容之一或多个：

- 第三方调用封装类
- 配置类
- 请求/响应 DTO
- SDK 初始化代码
- 短信发送方案
- OSS 上传下载方案
- HTTP 客户端调用示例
- 回调处理方案
- 签名与验签方案
- 错误处理与重试建议
- 第三方调用排查建议

---

## Rules

### 1. 不要默认第三方已接入
如果项目代码里没有相关依赖、配置或调用，不要直接假设已经接入成功。

### 2. 配置必须隔离
第三方配置应集中管理，避免硬编码：

- AppKey
- Secret
- Endpoint
- Bucket
- TemplateId
- AccessKey

### 3. 封装必须统一
第三方调用应尽量通过统一封装层完成，避免业务代码直接散落调用 SDK。

### 4. 超时与重试必须明确
第三方接口天然不稳定，应明确：

- 连接超时
- 读取超时
- 重试次数
- 重试间隔
- 熔断策略

### 5. 回调必须幂等
第三方回调可能重复投递，处理逻辑必须支持幂等。

### 6. 签名验签必须安全
涉及签名时，应注意：

- 参数顺序
- 编码方式
- 时间戳
- nonce
- 防重放
- 密钥保护

### 7. 错误处理要可追踪
必须保留足够上下文信息用于排查：

- 请求参数摘要
- 响应状态
- 错误码
- traceId
- 第三方 requestId

但避免打印敏感信息明文。

---

## Steps

### Step 1: 识别第三方类型

先判断接入类型属于哪一类：

- 短信
- OSS / 文件存储
- 邮件
- 支付
- API 平台
- SDK
- Webhook / 回调

---

### Step 2: 查看现有封装

检查项目是否已经存在：

- Client
- Adapter
- Util
- Service
- Config
- 常量类

如果已有封装，优先复用，不要重复实现。

---

### Step 3: 设计配置与初始化

确认第三方参数如何注入：

- `application.yml`
- 环境变量
- 配置中心
- 常量类
- Bean 初始化

并确保敏感信息不会硬编码在代码中。

---

### Step 4: 设计请求响应模型

根据第三方接口定义请求和响应 DTO，明确：

- 必填字段
- 可选字段
- 默认值
- 错误码含义
- 成功返回判断条件

---

### Step 5: 设计调用与容错

实现第三方调用时，应考虑：

- 超时
- 重试
- 异常包装
- 降级
- 异步处理
- 幂等控制

---

### Step 6: 设计回调与确认逻辑

如果第三方会回调，应明确：

- 验签
- 幂等
- 状态更新顺序
- 成功响应格式
- 失败重试规则

---

### Step 7: 设计日志与排查方案

记录必要信息：

- 请求目标
- 请求摘要
- 响应结果
- 耗时
- 错误信息
- 第三方返回码

同时避免泄露敏感字段。

---

## Examples

### Example 1: 短信发送

输入：

```text
需要接入短信验证码发送
```

输出方向：

- 配置短信平台参数
- 封装发送服务
- 记录验证码缓存
- 处理发送失败与重试
- 接口返回统一化

---

### Example 2: OSS 文件上传

输入：

```text
需要支持图片上传到对象存储
```

输出方向：

- OSS 客户端配置
- 上传工具类
- 文件名重命名规则
- 返回访问 URL
- 权限与过期策略

---

### Example 3: 外部 API 调用

输入：

```text
需要调用第三方用户信息接口
```

输出方向：

- HTTP Client 封装
- 请求 DTO、响应 DTO
- 超时与重试配置
- 错误码映射
- 日志记录与异常处理

---

### Example 4: 回调处理

输入：

```text
第三方支付完成后会回调我方接口
```

输出方向：

- 回调 Controller
- 验签逻辑
- 幂等处理
- 状态更新
- 响应第三方成功确认

---

## Pitfalls

### 1. 直接在业务代码里调用 SDK
这样会导致耦合过高、难测试、难替换。

### 2. 配置散落
如果第三方参数散落在多个地方，后续维护很困难。

### 3. 回调不幂等
重复回调会导致订单状态错乱、重复发货或重复入账。

### 4. 日志泄露敏感信息
不要在日志中打印：

- 密钥
- 验证码明文
- Token
- 用户隐私数据

### 5. 没有超时控制
第三方接口如果阻塞，可能拖垮整个业务线程池。

### 6. 错误码处理不统一
应将第三方错误统一转换为项目内部错误语义，避免调用方自行猜测。

---

## Recommended Outputs

当使用该 skill 时，建议产出以下文件或内容：

- 第三方封装类
- 配置类
- DTO 定义
- 失败重试策略
- 回调处理说明
- 调用排查指南
- 集成说明文档

---

## Scope

该 skill 适用于以下范围：

- 短信平台
- OSS / 文件存储
- 外部 HTTP API
- SDK 封装
- 回调处理
- 验签
- 重试与降级

不适用于：

- 数据库 SQL 设计
- Redis 缓存策略本身
- 页面前端展示
- MyBatis 映射设计
