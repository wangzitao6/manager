# skills/index.md

# Skills Index

本目录用于存放本项目的 SDD Skills。  
所有技能按“基础能力 -> 专项能力 -> 交叉能力”的顺序组织。

---

## 1. Base

### springboot-base
- **路径**：`./springboot-base/skill.md`
- **用途**：Spring Boot 基础工程分析、配置、分层、通用能力说明
- **依赖**：无
- **说明**：所有其他 skill 的前置基础

---

## 2. Data Access

### mybatis-access
- **路径**：`./mybatis-access/skill.md`
- **用途**：MyBatis / MyBatis-Plus 数据访问、Mapper、XML、CRUD
- **依赖**：
    - springboot-base
- **相关**：
    - swagger-doc
    - transaction-safety

### mongodb-access
- **路径**：`./mongodb-access/skill.md`
- **用途**：MongoDB 数据访问、文档模型、查询与聚合
- **依赖**：
    - springboot-base
- **相关**：
    - swagger-doc

---

## 3. Cache

### redis-cache
- **路径**：`./redis-cache/skill.md`
- **用途**：Redis 缓存、Key 设计、TTL、缓存更新策略
- **依赖**：
    - springboot-base
- **相关**：
    - aop-interceptor

---

## 4. Web / Cross-Cutting

### aop-interceptor
- **路径**：`./aop-interceptor/skill.md`
- **用途**：AOP 切面、拦截器、日志、鉴权、请求预处理
- **依赖**：
    - springboot-base
- **相关**：
    - redis-cache
    - swagger-doc

### swagger-doc
- **路径**：`./swagger-doc/skill.md`
- **用途**：接口文档、Swagger 注释、DTO/VO 字段说明
- **依赖**：
    - springboot-base
- **相关**：
    - mybatis-access
    - mongodb-access
    - thirdparty-integration

---

## 5. Integration

### thirdparty-integration
- **路径**：`./thirdparty-integration/skill.md`
- **用途**：OSS、短信、外部 API、SDK 封装
- **依赖**：
    - springboot-base
- **相关**：
    - swagger-doc

---

## 6. Recommended Load Order

建议 AI 在处理任务时按以下顺序加载技能：

1. `springboot-base`
2. 根据任务类型加载专项 skill：
    - 数据库相关：`mybatis-access` / `mongodb-access`
    - 缓存相关：`redis-cache`
    - Web 横切能力：`aop-interceptor`
    - 文档相关：`swagger-doc`
    - 第三方集成：`thirdparty-integration`

---

## 7. Usage Rules

- 优先使用 `springboot-base` 识别工程骨架
- 数据访问任务优先使用 `mybatis-access`
- 接口文档任务优先使用 `swagger-doc`
- 拦截器、日志、鉴权任务优先使用 `aop-interceptor`
- 第三方调用任务优先使用 `thirdparty-integration`
- 不要在未确认代码存在的情况下假设依赖已启用

---

## 8. Notes

- 该索引文件是所有 skill 的统一入口
- 新增 skill 时，应同步更新本文件和 `router.md`
- 若 skill 之间存在强依赖，应在各自 skill 文件中明确写出 `DependsOn` 和 `Related`
