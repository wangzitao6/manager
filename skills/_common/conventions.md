# Common Conventions

本文件用于定义整个 skills 目录下通用的编写规范、命名规范和输出规范。  
所有 skill 文件在编写时都应参考本文件。

---

## 1. General Principles

### 1.1 以仓库实际代码为准
所有分析必须基于真实代码、真实目录、真实配置。  
如果信息不足，必须明确标记“待确认”，不能臆造。

### 1.2 先理解基础，再进入专项
优先加载 `springboot-base`，再进入数据层、缓存、文档、第三方集成等专项 skill。

### 1.3 输出要可直接落地
Skill 的输出应尽量满足以下任意一种用途：

- 直接写入 Markdown 文档
- 直接作为代码生成参考
- 直接作为重构建议
- 直接作为调试排查依据

---

## 2. Naming Conventions

### 2.1 Skill 命名
Skill 名称建议使用小写加连字符，例如：

- `springboot-base`
- `mybatis-access`
- `redis-cache`
- `aop-interceptor`
- `swagger-doc`
- `thirdparty-integration`
- `mongodb-access`

### 2.2 目录命名
目录名应与 skill 名一致：

```text
skills/mybatis-access/
skills/redis-cache/
```

### 2.3 文件命名
每个 skill 目录内建议统一使用：

- `skill.md`

如果需要补充说明文档，可再增加：

- `usage.md`
- `examples.md`
- `notes.md`

---

## 3. Skill File Structure

每个 skill 文件建议包含以下固定字段：

```md
# Skill Name

## ID
...

## Category
...

## DependsOn
...

## Related
...

## Purpose
...

## Trigger
...

## Input
...

## Output
...

## Rules
...

## Steps
...

## Examples
...

## Pitfalls
...
```

---

## 4. Dependency Rules

### 4.1 强依赖
如果某个 skill 需要另一个 skill 作为前置基础，应写入 `DependsOn`。

例如：

- `mybatis-access` DependsOn `springboot-base`
- `swagger-doc` DependsOn `springboot-base`

### 4.2 弱关联
如果只是功能相关，但不构成前置依赖，应写入 `Related`。

例如：

- `mybatis-access` Related `swagger-doc`
- `redis-cache` Related `aop-interceptor`

### 4.3 避免循环依赖
禁止出现互相强依赖的情况。  
建议采用以下模式：

- `springboot-base` 作为根 skill
- 其他 skill 单向依赖它
- 专项 skill 之间只写 `Related`

---

## 5. Output Conventions

### 5.1 语言风格
输出建议使用：

- 简洁中文
- 必要时保留英文术语
- 术语第一次出现时尽量说明含义

### 5.2 代码格式
如果输出代码，应保持：

- 代码块完整
- 包名统一
- 命名清晰
- 可直接复制使用

### 5.3 文档格式
如果输出文档，应优先使用：

- 标题
- 分节
- 列表
- 表格
- 示例代码块

---

## 6. Analysis Rules

### 6.1 不要默认存在某些依赖
不要未经确认就默认项目中一定存在：

- Redis
- Swagger
- MongoDB
- MyBatis-Plus
- OSS
- SMS SDK

必须根据代码和配置确认。

### 6.2 保留不确定性
如果无法确认某个实现细节，应写：

- `待确认`
- `未在当前代码中发现`
- `需要进一步查看`

### 6.3 分层分析
分析时优先区分：

- controller
- service
- dao / mapper
- entity
- dto
- vo
- config
- util
- aop
- interceptor
- thirdpart

---

## 7. Recommended Shared Terms

建议统一使用以下术语：

- `Entity`：数据库实体
- `DTO`：请求或传输对象
- `VO`：展示对象
- `DAO` / `Mapper`：数据访问层
- `Service`：业务层
- `Controller`：接口层
- `Config`：配置类
- `Utils`：工具类
- `ThirdParty`：第三方封装

---

## 8. Example Metadata Block

建议每个 skill 使用如下 metadata：

```md
## ID
mybatis-access

## Category
data-access

## DependsOn
- springboot-base

## Related
- swagger-doc
- transaction-safety
```

---

## 9. Maintenance Rules

### 9.1 新增 skill 时
需要同步更新：

- `skills/index.md`
- `skills/router.md`
- 新 skill 自身的 `skill.md`

### 9.2 修改 skill 时
如果 skill 的职责变化，应同步更新：

- 名称
- 分类
- 依赖
- 相关 skill
- 路由映射

---

## 10. Minimal Writing Standard

当编写新 skill 时，最少应包含：

- Purpose
- Trigger
- Input
- Output
- Rules
- Steps

若内容较完整，再补充：

- Examples
- Pitfalls
- Notes
