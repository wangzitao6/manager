# mongodb-access Skill

## ID
mongodb-access

## Category
data-access

## DependsOn
- springboot-base

## Related
- swagger-doc
- validation
- transaction-safety
- aop-interceptor

## Purpose

用于分析和生成 MongoDB 数据访问相关能力，包括：

- MongoDB Repository / Template 使用
- 文档模型设计
- 查询条件构建
- 分页查询
- 聚合查询
- 索引设计建议
- 字段映射规则
- 嵌套文档与数组处理
- MongoDB 写入与更新方案
- 与 Spring Boot 集成的配置与代码生成

该 skill 适用于本仓库中所有与 MongoDB 交互的模块，尤其是：

- `src/main/java/com/wzt/demo/mongodb`
- `src/main/java/com/wzt/demo/repository`
- `src/main/java/com/wzt/demo/service`
- `src/main/java/com/wzt/demo/entity`
- `src/main/java/com/wzt/demo/config`
- 与 `MongoTemplate`、`MongoRepository`、聚合管道、索引相关的代码

---

## Trigger

当出现以下场景时，启用该 skill：

- 需要设计 MongoDB 集合结构
- 需要编写 MongoDB 查询代码
- 需要使用 `MongoTemplate`
- 需要使用 `MongoRepository`
- 需要做分页、排序、聚合
- 需要处理嵌套对象或数组字段
- 需要设计索引
- 需要分析 MongoDB 写入/更新逻辑
- 需要排查 MongoDB 查询慢或结果不对
- 需要与 Spring Boot 集成 MongoDB

---

## Input

优先读取以下内容：

- `src/main/java/com/wzt/demo/mongodb`
- `src/main/java/com/wzt/demo/repository`
- `src/main/java/com/wzt/demo/entity`
- `src/main/java/com/wzt/demo/service`
- `src/main/java/com/wzt/demo/config`
- `pom.xml`
- MongoDB 相关配置文件
- 查询代码
- 聚合代码
- 索引定义
- 文档实体类

如果可用，还应参考：

- 集合名称
- 字段结构
- 查询条件
- 排序规则
- 分页要求
- 数据量规模
- 写入频率
- 访问热点字段

---

## Output

该 skill 应输出以下内容之一或多个：

- MongoDB 实体类
- Repository 接口
- `MongoTemplate` 查询示例
- 聚合查询示例
- 索引设计建议
- 分页查询方案
- 更新与批量更新方案
- 文档结构设计建议
- 查询性能排查建议
- Spring Boot MongoDB 配置示例

---

## Rules

### 1. 区分 MongoRepository 和 MongoTemplate
不要混用概念：

- **MongoRepository**：适合简单 CRUD、派生查询
- **MongoTemplate**：适合复杂条件、动态查询、聚合、批量操作

应根据场景选择合适方式。

### 2. 文档结构要贴合查询场景
MongoDB 的建模应优先考虑读取路径，而不是完全照搬关系型表结构。

### 3. 索引必须服务于查询
不要盲目增加索引，索引应围绕：

- 高频查询条件
- 排序字段
- 唯一约束
- 聚合过滤字段

### 4. 字段映射要明确
要注意：

- Java 字段名与 Mongo 字段名映射
- 时间类型映射
- 嵌套对象序列化
- 枚举存储方式

### 5. 查询条件应可维护
动态查询不要写成难以理解的拼接地狱，应尽量封装为清晰的方法或条件构建器。

### 6. 注意大文档与数组膨胀
MongoDB 文档不应无限增长，尤其是数组字段，容易影响性能和文档大小限制。

### 7. 写入与更新要考虑幂等
对于重复写入、状态更新、消息消费等场景，应设计幂等键或条件更新。

---

## Steps

### Step 1: 识别数据模型

先判断当前 MongoDB 文档属于哪种类型：

- 日志型文档
- 配置型文档
- 业务记录型文档
- 事件流水型文档
- 聚合结果型文档

---

### Step 2: 设计实体类

根据集合结构设计实体类，明确：

- `@Document` 集合名
- `@Id`
- 字段类型
- 嵌套对象
- 数组字段
- 时间字段
- 枚举字段

---

### Step 3: 选择访问方式

根据需求选择：

- 简单 CRUD：`MongoRepository`
- 动态查询：`MongoTemplate`
- 聚合分析：`Aggregation`
- 批量更新：`BulkOperations`

---

### Step 4: 设计查询条件

明确查询维度：

- 精确匹配
- 模糊匹配
- 范围查询
- 时间区间
- 多条件组合
- 排序分页

---

### Step 5: 设计索引

结合查询路径设计索引，并确认：

- 单字段索引
- 复合索引
- 唯一索引
- TTL 索引
- 部分索引

---

### Step 6: 设计写入与更新

明确：

- 新增是否覆盖
- 更新是否原子
- 是否需要条件更新
- 是否需要自增/版本控制
- 是否需要 upsert

---

### Step 7: 排查性能与一致性问题

检查：

- 是否命中索引
- 是否全表扫描
- 是否分页过深
- 是否数组过大
- 是否存在重复写入
- 是否存在并发更新冲突

---

## Examples

### Example 1: 简单文档查询

输入：

```text
根据用户 ID 查询最近一条访问记录
```

输出方向：

- 使用 `MongoTemplate` 或 Repository 派生查询
- 按时间倒序
- 限制返回条数为 $$1$$
- 为 `userId + createTime` 建索引

---

### Example 2: 动态条件查询

输入：

```text
根据状态、时间范围、关键词组合查询订单日志
```

输出方向：

- 使用 `Criteria` 动态拼接
- 支持空条件跳过
- 结果分页排序
- 针对状态和时间字段建复合索引

---

### Example 3: 聚合统计

输入：

```text
统计每天的访问量
```

输出方向：

- 使用聚合管道按日期分组
- 返回日期和计数
- 关注时间字段索引
- 如需高频统计可考虑预聚合

---

### Example 4: 批量更新

输入：

```text
将某批记录状态更新为已处理
```

输出方向：

- 使用批量更新或条件更新
- 明确更新条件
- 避免逐条单独写入
- 处理并发重复执行问题

---

## Pitfalls

### 1. 把关系型思维直接套到 MongoDB
MongoDB 不是简单替代 MySQL，建模方式不同，尤其要考虑读写路径和嵌套结构。

### 2. 索引设计不足
没有索引会导致查询慢，有过多索引会影响写入性能。

### 3. 深分页性能差
MongoDB 深分页通常性能较差，必要时考虑游标或时间分页。

### 4. 文档过大
数组和嵌套对象无限膨胀会影响性能，甚至超出文档大小限制。

### 5. 更新条件不严谨
如果条件过宽，容易误更新多条记录。

### 6. 并发写入导致覆盖
没有版本控制或条件更新时，可能出现后写覆盖先写。

---

## Recommended Outputs

当使用该 skill 时，建议产出以下文件或内容：

- MongoDB 实体类
- Repository 接口
- 查询封装类
- 索引设计说明
- 聚合查询样例
- 性能优化建议
- 文档结构设计说明

---

## Scope

该 skill 适用于以下范围：

- MongoDB 实体建模
- CRUD 访问
- 动态查询
- 聚合统计
- 索引优化
- 批量写入与更新
- Spring Boot MongoDB 集成

不适用于：

- 关系型数据库 SQL 设计
- Redis 缓存策略
- 前端页面开发
- 第三方 SDK 集成
