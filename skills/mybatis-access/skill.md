# mybatis-access Skill

## ID
mybatis-access

## Category
data-access

## DependsOn
- springboot-base

## Related
- swagger-doc
- transaction-safety
- dto-vo-converter

## Purpose

用于分析和生成 MyBatis / MyBatis-Plus 相关代码，包括：

- Entity 设计
- Mapper 接口设计
- XML SQL 编写
- CRUD 方法生成
- 条件查询
- 分页查询
- 批量操作
- SQL 调试与优化
- 数据访问层结构梳理

该 skill 适用于本仓库中与数据库访问相关的模块，尤其是：

- `src/main/java/com/wzt/demo/example/dao`
- `src/main/java/com/wzt/demo/example/entity`
- `src/main/java/com/wzt/demo/example/service`
- `src/main/resources/mapper`

---

## Trigger

当出现以下场景时，启用该 skill：

- 需要根据数据库表生成实体类和 Mapper
- 需要补全 XML SQL
- 需要写复杂查询或动态 SQL
- 需要分析 DAO 层结构
- 需要排查 MyBatis 映射失败
- 需要优化分页、排序、条件筛选
- 需要梳理 CRUD 接口与数据库映射关系

---

## Input

优先读取以下内容：

- 数据表结构
- `src/main/java/com/wzt/demo/example/entity`
- `src/main/java/com/wzt/demo/example/dao`
- `src/main/resources/mapper`
- `src/main/java/com/wzt/demo/example/service`
- `src/main/java/com/wzt/demo/example/service/impl`
- `pom.xml`
- 数据源配置
- MyBatis 配置
- 相关 Controller 与 DTO

如果可用，还应参考：

- SQL 脚本
- 测试数据
- 查询日志
- 编译产物中的 mapper 资源

---

## Output

该 skill 应输出以下内容之一或多个：

- Entity 类
- Mapper 接口
- XML 文件
- SQL 语句
- Service 方法
- 分页示例
- 条件查询示例
- 批量插入/更新示例
- 字段映射说明
- SQL 问题排查建议
- 数据访问层结构建议

---

## Rules

### 1. 必须基于真实表结构或真实代码
如果没有表结构，不要凭空生成字段。  
如果没有实体类，不要随意假设字段名和类型。

### 2. 保持实体、Mapper、XML 一致
输出内容时必须保证：

- Entity 字段与表字段可映射
- Mapper 方法与 XML id 一致
- SQL 参数名与接口参数一致

### 3. 避免重复造轮子
如果项目已经使用了现成的基础类或通用 Mapper，应优先复用。

### 4. 区分 MyBatis 与 MyBatis-Plus
如果项目使用的是 MyBatis，重点输出 XML 和接口映射。  
如果项目使用的是 MyBatis-Plus，可优先输出 `BaseMapper`、`ServiceImpl`、`QueryWrapper` 等写法。

### 5. SQL 必须可执行
输出 SQL 时应尽量保证：

- 语法正确
- 参数完整
- 别名清晰
- 条件逻辑明确

### 6. 命名要统一
推荐统一命名风格：

- Entity：与表名语义一致
- Mapper：`xxxMapper`
- XML：`xxxMapper.xml`
- 方法名：`selectById`、`listByCondition`、`insertBatch`

### 7. 校验注解导入规范

生成 DTO / Form / Request 类时，校验注解必须优先遵循项目当前依赖版本：

- Spring Boot 3 或 Jakarta 环境：使用 `jakarta.validation.constraints.*`
- Spring Boot 2 或 javax 环境：使用 `javax.validation.constraints.*`

禁止使用 `org.hibernate.validator.constraints.Size`。

如果项目已有现成 DTO 风格，应优先复用现有包路径，避免混用多个校验注解来源。



### 8. Service 实现类生成规范

生成 ServiceImpl 前，必须先分析当前仓库已有的 Service / ServiceImpl 代码风格，包括但不限于：

- `src/main/java/**/service/impl/*ServiceImpl.java`
- `src/main/java/**/service/*Service.java`
- `src/main/java/**/dao/*Mapper.java`
- `src/main/java/**/utils/*`
- `src/main/java/**/exception/*`

生成的新 ServiceImpl 必须严格复用项目现有风格，不得自行套用通用模板。

#### 8.1 必须对齐的内容
- 注入方式：按项目现有写法选择 `@Autowired`、构造器注入或其他方式
- 事务控制：按项目已有习惯决定是否添加 `@Transactional`
- 异常类型：必须使用项目已有业务异常，不得随意新建异常体系
- 属性拷贝：优先使用项目已有工具类，如 `BeanCopyUtil.copyProperties`
- 返回值风格：按项目现有风格保持 `Long` / `boolean` / `void` / VO 返回一致
- 方法命名：与已有 ServiceImpl 保持一致
- 校验与查重逻辑：与现有实现保持一致

#### 8.2 生成要求
- 不得使用仓库中不存在的工具类、异常类、Mapper 方法
- 不得擅自改变现有代码风格
- 不得为了“看起来更规范”而重构现有模式
- 若项目中已有同类实现，必须优先模仿该实现
- 若存在多种写法，优先以当前模块同目录下的实现为准

### 8.3 风格优先级

生成 ServiceImpl 时，优先级如下：

1. 当前模块同目录下已有实现
2. 同包路径下其他模块实现
3. 项目全局通用约定
4. 通用 Spring / MyBatis 规范


### 8.4 生成前检查

在生成 ServiceImpl 前，必须先确认以下内容：

- Mapper 中实际存在的方法名
- Entity 中实际存在的字段名
- DTO 中实际存在的属性名
- 项目中现有的异常类名称
- 项目中现有的属性拷贝工具类
- 项目中现有的返回体类型

如果发现当前约定不明确，应先输出最少必要确认项，不要直接生成。



---

## Steps

### Step 1: 识别数据访问方式

判断项目是：

- MyBatis
- MyBatis-Plus
- 混合使用
- 仅保留 Mapper 结构

如果无法确认，应先查看依赖和配置。

---

### Step 2: 识别表与实体关系

根据表结构或已有 Entity 推导：

- 主键字段
- 普通字段
- 时间字段
- 逻辑删除字段
- 乐观锁字段
- 关联字段

---

### Step 3: 生成或补全 Entity

根据表字段生成实体类，并补充：

- 注解
- 字段类型
- 注释
- `@TableName`
- `@TableId`
- `@TableField`

如果项目未使用相关注解，则保持与现有风格一致。

---

### Step 4: 生成 Mapper 接口

根据查询需求生成接口方法，例如：

- `selectById`
- `selectListByCondition`
- `insert`
- `updateById`
- `deleteById`
- `insertBatch`
- `countByCondition`

---

### Step 5: 生成 XML SQL

为复杂查询编写 XML，包括：

- `select`
- `insert`
- `update`
- `delete`
- 动态条件
- 分页
- 联表查询
- 聚合统计

---

### Step 6: 生成 Service 层调用

补全 Service 和 ServiceImpl 中的调用逻辑，包括：

- 参数校验
- 事务控制
- 结果转换
- 异常处理

---

### Step 7: 检查映射一致性

检查以下内容是否一致：

- 表字段与实体字段
- XML 参数名与接口参数名
- 返回值类型与业务预期
- 分页参数与分页插件配置

---

## Examples

### Example 1: 生成基础 CRUD 映射

输入：

```text
表：user_info
字段：id, username, password, create_time, update_time
```

输出方向：

- `UserInfo` 实体
- `UserInfoMapper`
- `UserInfoMapper.xml`
- `UserInfoService`
- `UserInfoServiceImpl`

---

### Example 2: 生成条件查询

输入：

```text
需要按用户名模糊查询，并支持分页
```

输出方向：

- Mapper 方法：`selectPageByUsername`
- XML 动态 SQL
- Service 分页调用

---

### Example 3: 生成批量插入

输入：

```text
需要批量插入用户列表
```

输出方向：

- `insertBatch(List<UserInfo> list)`
- 批量插入 XML
- 参数校验建议

---

## Pitfalls

### 1. 只生成 Entity，不生成映射
如果只生成实体，没有 Mapper 和 SQL，数据访问链路是不完整的。

### 2. 参数名不一致
XML 中的参数名必须和接口声明一致，否则会导致运行时报错。

### 3. 字段类型映射错误
尤其要注意：

- 时间类型
- 大整数
- 布尔值
- 枚举值
- JSON 字段

### 4. 动态 SQL 过于复杂
复杂查询要注意：

- 条件嵌套
- 空值判断
- where 片段拼接
- 分页与排序

### 5. 忽略事务边界
涉及多表写入或批量操作时，应结合 `transaction-safety` skill 一起分析。

---

## Recommended Outputs

当使用该 skill 时，建议产出以下文件或内容：

- `entity/*.java`
- `dao/*.java`
- `mapper/*.xml`
- `service/*.java`
- `service/impl/*.java`
- SQL 设计说明
- 查询示例文档

---

## Scope

该 skill 适用于以下范围：

- 单表 CRUD
- 动态查询
- 联表查询
- 分页查询
- 批量写入
- SQL 调优
- MyBatis 映射排查

不适用于：

- 前端页面开发
- 业务流程设计
- Redis 缓存设计
- 第三方 SDK 封装
