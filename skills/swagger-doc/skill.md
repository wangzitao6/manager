# swagger-doc Skill

## ID
swagger-doc

## Category
api-documentation

## DependsOn
- springboot-base

## Related
- mybatis-access
- mongodb-access
- thirdparty-integration
- validation
- aop-interceptor

## Purpose

用于分析和生成 Spring Boot 项目中的接口文档相关能力，包括：

- Swagger / OpenAPI 配置
- Controller 注解补全
- DTO / VO 字段说明
- 参数含义标注
- 返回结果说明
- 接口分组与标签管理
- 文档可读性优化
- 文档与代码一致性检查

该 skill 适用于本仓库中与接口文档相关的模块，尤其是：

- `src/main/java/com/wzt/demo/controller`
- `src/main/java/com/wzt/demo/dto`
- `src/main/java/com/wzt/demo/vo`
- `src/main/java/com/wzt/demo/config`
- 与 Swagger / OpenAPI 相关的配置和注解代码

---

## Trigger

当出现以下场景时，启用该 skill：

- 需要补充接口文档
- 需要生成 Swagger 注解
- 需要给 Controller、DTO、VO 添加说明
- 需要检查接口参数是否清晰
- 需要整理 API 分组与标签
- 需要统一返回结构文档
- 需要排查接口文档不显示或不完整
- 需要为第三方接口或内部接口补充说明

---

## Input

优先读取以下内容：

- `src/main/java/com/wzt/demo/controller`
- `src/main/java/com/wzt/demo/dto`
- `src/main/java/com/wzt/demo/vo`
- `src/main/java/com/wzt/demo/config`
- `pom.xml`
- Swagger / OpenAPI 相关配置
- 自定义注解
- 返回体封装类
- 全局异常处理类

如果可用，还应参考：

- 请求路径
- HTTP 方法
- 实际参数名
- 返回字段
- 枚举类
- 校验注解
- 示例请求/响应数据

---

## Output

该 skill 应输出以下内容之一或多个：

- Swagger 配置类
- OpenAPI 配置类
- Controller 注解补全
- DTO 注解补全
- VO 注解补全
- 接口分组说明
- 参数说明文档
- 返回值说明文档
- 文档排查建议
- API 文档规范

---

## Rules

### 1. 文档必须与真实代码一致
不能为了“看起来完整”而编造接口、字段或说明。  
所有注解和描述都应基于实际代码结构。

### 2. 优先描述业务语义
字段说明应尽量说明“这个字段有什么业务含义”，而不是只重复字段名。

例如：

- 不推荐：`userName 用户名`
- 推荐：`userName 登录账号名称，用于用户身份展示和检索`

### 3. 保持参数与返回结构清晰
对于 Controller 方法，必须明确：

- 请求方式
- 请求路径
- 入参来源
- 返回值类型
- 是否分页
- 是否需要登录
- 是否可能出现异常

### 4. 对 DTO / VO 补充准确注释
尤其关注：

- 必填 / 非必填
- 字段格式
- 长度限制
- 枚举值含义
- 时间格式
- 单位说明

### 5. 不要过度注解
不要给所有字段堆砌过多无意义注释。  
优先确保关键字段清晰，避免噪声过大。

### 6. 文档与校验保持一致
如果字段有校验注解，应让文档描述与校验规则一致，例如：

- 必填
- 长度范围
- 数值范围
- 格式要求

### 7. 注意敏感字段
以下字段通常不应在文档中暴露完整业务含义或样例：

- 密码
- Token
- 密钥
- 身份证号
- 手机号
- 内部签名字段

---

## Steps

### Step 1: 识别接口范围

先判断当前需要补文档的范围：

- Controller 类
- 某一组接口
- DTO / VO 类
- 返回体封装
- 全局配置类

---

### Step 2: 识别文档框架

确认项目使用的是：

- Swagger 2
- Springfox
- Springdoc OpenAPI
- 自定义文档方式

如果无法确认，应先查看依赖和配置。

---

### Step 3: 补全接口注解

对于 Controller，建议确认以下信息：

- `@Tag` / `@Api`
- `@Operation` / `@ApiOperation`
- `@Parameter`
- `@RequestBody`
- `@PathVariable`
- `@RequestParam`
- `@Schema`

根据项目所用框架选择对应注解。

---

### Step 4: 补全 DTO / VO 注解

为请求对象和返回对象补充：

- 字段说明
- 示例值
- 是否必填
- 数据格式
- 枚举解释

---

### Step 5: 分组与标签整理

按业务模块整理接口分组，例如：

- 用户管理
- 商品管理
- 订单管理
- 配置管理
- 第三方集成

让文档更易查找。

---

### Step 6: 检查统一返回结构

如果项目使用统一返回体，应在文档中说明：

- `code`
- `message`
- `data`
- `success`
- `traceId`

并确保示例与实际返回一致。

---

### Step 7: 验证文档可展示性

确认以下内容是否完整：

- 配置是否生效
- 包扫描是否正确
- Controller 是否被纳入文档
- 注解是否写在正确位置
- 泛型和嵌套对象是否能正确展示

---

## Examples

### Example 1: 给登录接口补文档

输入：

```text
POST /login
参数：username, password
返回：token, userInfo
```

输出方向：

- 接口名：用户登录
- 参数说明：用户名、密码
- 返回说明：登录 token、用户信息
- 注明密码字段敏感

---

### Example 2: 给分页查询接口补文档

输入：

```text
需要分页查询用户列表
```

输出方向：

- 请求参数：pageNum、pageSize、keyword
- 返回说明：分页列表、总数、当前页
- 标注是否支持模糊搜索

---

### Example 3: 给 VO 补充展示字段说明

输入：

```text
返回订单详情
```

输出方向：

- 订单号
- 下单时间
- 支付状态
- 金额单位
- 商品列表

---

### Example 4: 第三方接口说明

输入：

```text
需要说明短信发送接口
```

输出方向：

- 接口用途
- 请求参数
- 返回结果
- 限制说明
- 失败场景说明

---

## Pitfalls

### 1. 注解只写在方法上，字段没说明
如果 DTO / VO 字段缺少说明，文档阅读体验会很差。

### 2. 返回结构不一致
同一个接口体系内，返回格式不一致会导致文档混乱。

### 3. 把内部实现细节暴露给前端
文档应面向调用方，不应暴露过多内部实现信息。

### 4. 泛型对象展示不清晰
复杂泛型、嵌套对象、集合对象需要额外注意文档展示效果。

### 5. 与校验规则不一致
文档写“可选”，但校验注解是必填，会误导使用者。

### 6. 忽略错误响应说明
接口文档不应只写成功返回，也应说明常见失败场景。

---

## Recommended Outputs

当使用该 skill 时，建议产出以下文件或内容：

- Controller 注解补全结果
- DTO / VO 字段文档
- Swagger 配置类
- API 分组说明
- 示例请求 / 响应文档
- 接口说明 Markdown

---

## Scope

该 skill 适用于以下范围：

- REST API 文档
- 请求参数说明
- 返回值说明
- DTO / VO 标注
- OpenAPI / Swagger 配置
- 接口分组整理

不适用于：

- 数据库 SQL 设计
- 缓存策略设计
- AOP 逻辑实现
- 第三方 SDK 的底层实现
