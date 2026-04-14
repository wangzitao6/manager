# SDD - [模块名称]

## 1. 文档信息

- 项目名称：[项目名称]
- 模块名称：[模块名称]
- 文档版本：v1.0
- 创建时间：[YYYY-MM-DD]
- 作者：[你的名字]
- 技术栈：
    - Spring Boot
    - MyBatis / MyBatis-Plus
    - MySQL
    - Swagger / OpenAPI
    - Bean Validation
    - Lombok（如项目使用）
    - JUnit（如需要测试）

---

## 2. 背景与目标

### 2.1 背景
简要说明为什么需要这个模块，解决什么业务问题。

### 2.2 目标
本模块需要实现的核心目标，例如：
- 支持新增
- 支持修改
- 支持删除
- 支持详情查询
- 支持分页查询
- 支持条件筛选

### 2.3 非目标
本模块暂不实现的功能，例如：
- 不做复杂审批流
- 不做导出导入
- 不做权限细分控制

---

## 3. 业务范围

### 3.1 功能列表
1. 新增 [模块实体]
2. 修改 [模块实体]
3. 删除 [模块实体]
4. 查询详情
5. 分页查询
6. 条件筛选查询

### 3.2 业务规则
列出所有业务限制和判断逻辑，例如：
- 名称不能为空
- 名称唯一
- 状态值只能为指定枚举
- 删除使用逻辑删除
- 查询仅返回未删除数据

---

## 4. 现有项目约束

请严格遵循当前项目的以下规范：

1. 包结构必须与项目现有风格一致
2. 统一返回体必须复用项目已有类
3. 异常处理必须复用项目已有异常体系
4. Swagger 注解必须与项目现有配置一致
5. 参数校验必须使用项目已有校验方式
6. Mapper / XML 风格必须符合项目现有写法
7. 不要新增与项目无关的第三方依赖
8. 不要随意更改已有公共工具类或常量类

---

## 5. 数据模型设计

### 5.1 数据库表
表名：[table_name]

### 5.2 表结构

| 字段名 | 类型 | 是否必填 | 默认值 | 说明 |
|---|---|---:|---|---|
| id | bigint | 是 | 自增 | 主键 |
| name | varchar(50) | 是 |  | 名称 |
| status | varchar(20) | 是 |  | 状态 |
| remark | varchar(255) | 否 |  | 备注 |
| create_time | datetime | 是 | 当前时间 | 创建时间 |
| update_time | datetime | 是 | 当前时间 | 更新时间 |
| deleted | tinyint | 是 | 0 | 删除标记 |

### 5.3 索引与约束
- 主键：id
- 唯一约束：[(字段1, 字段2)]
- 普通索引：[(字段名)]
- 逻辑删除字段：deleted

### 5.4 建表 SQL
```sql
-- 在这里写完整建表 SQL
```

---

## 6. 实体设计

### 6.1 Entity
实体类名称：[EntityName]

### 6.2 字段映射
| Java 字段 | 数据库字段 | 类型 | 说明 |
|---|---|---|---|
| id | id | Long | 主键 |
| name | name | String | 名称 |
| status | status | String | 状态 |

### 6.3 设计要求
- 实体字段名与数据库字段名一一对应
- 使用驼峰命名
- 保留必要注释
- 如项目使用 Lombok，则使用对应注解
- 如项目不使用 Lombok，则生成完整 getter/setter

---

## 7. DTO 设计

### 7.1 创建 DTO
类名：[CreateDTO]

字段：
- name：非空，最大长度 50
- status：非空
- remark：可空，最大长度 255

### 7.2 更新 DTO
类名：[UpdateDTO]

字段：
- id：非空
- name：非空，最大长度 50
- status：非空
- remark：可空，最大长度 255

### 7.3 查询 DTO
类名：[QueryDTO]

字段：
- pageNum：默认 1，最小 1
- pageSize：默认 10，范围 1-100
- name：可选
- status：可选

### 7.4 DTO 设计要求
- DTO 只用于接收请求参数
- DTO 必须加参数校验注解
- DTO 不直接暴露数据库字段
- DTO 命名风格必须统一

---

## 8. VO 设计

### 8.1 返回 VO
类名：[VOName]

字段：
- id
- name
- status
- remark
- createTime
- updateTime

### 8.2 设计要求
- VO 仅用于返回前端
- 不返回 deleted 字段
- 不返回内部敏感字段
- 字段命名适合前端使用

---

## 9. 枚举设计

### 9.1 状态枚举
类名：[StatusEnum]

枚举值：
- ENABLED
- DISABLED

### 9.2 设计要求
- 用枚举统一管理状态值
- 避免字符串硬编码
- 如项目已有枚举规范，请遵循现有写法

---

## 10. 接口设计

### 10.1 新增
- 方法：POST
- 路径：/api/[resources]
- 请求体：CreateDTO
- 返回：统一响应体 + VO 或提示信息

### 10.2 修改
- 方法：PUT
- 路径：/api/[resources]/{id}
- 请求体：UpdateDTO
- 返回：统一响应体

### 10.3 删除
- 方法：DELETE
- 路径：/api/[resources]/{id}
- 返回：统一响应体

### 10.4 详情
- 方法：GET
- 路径：/api/[resources]/{id}
- 返回：VO

### 10.5 分页查询
- 方法：GET
- 路径：/api/[resources]
- 请求参数：QueryDTO
- 返回：分页结果

---

## 11. 业务逻辑设计

### 11.1 新增业务逻辑
1. 校验参数
2. 判断唯一性
3. 插入数据库
4. 返回结果

### 11.2 修改业务逻辑
1. 校验参数
2. 判断记录是否存在
3. 判断是否与其他记录冲突
4. 更新数据库
5. 返回结果

### 11.3 删除业务逻辑
1. 判断记录是否存在
2. 执行逻辑删除
3. 返回结果

### 11.4 详情查询业务逻辑
1. 根据 ID 查询
2. 不存在则抛出业务异常
3. 返回 VO

### 11.5 分页查询业务逻辑
1. 接收分页参数
2. 组装动态查询条件
3. 仅查询未删除数据
4. 返回分页结果

---

## 12. Mapper 设计

### 12.1 Mapper 接口
类名：[MapperName]

### 12.2 Mapper 方法
- insert
- updateById
- deleteByIdLogical
- selectById
- selectPage
- countByUniqueKey

### 12.3 XML 设计要求
- namespace 必须匹配 Mapper 接口
- SQL 使用动态标签
- 字段映射必须准确
- 仅查询未删除数据
- 支持条件筛选
- 支持分页查询

---

## 13. 服务层设计

### 13.1 Service 接口
类名：[ServiceName]

### 13.2 Service 实现
类名：[ServiceImplName]

### 13.3 设计要求
- 所有业务逻辑放在 Service 层
- Controller 不写业务判断
- 事务控制放在 Service 层
- 统一抛出业务异常
- 处理重复、不存在、删除失败等场景

---

## 14. Controller 设计

### 14.1 Controller 名称
类名：[ControllerName]

### 14.2 设计要求
- REST 风格
- 使用统一返回体
- 只做参数接收和结果返回
- 必须加 Swagger 注解
- 必须加参数校验注解
- 不写复杂业务逻辑

---

## 15. 异常设计

### 15.1 业务异常
场景包括：
- 参数非法
- 数据不存在
- 数据已存在
- 删除失败
- 更新失败

### 15.2 错误码设计
| 错误码 | 错误信息 |
|---|---|
| 40001 | 参数错误 |
| 40002 | 数据不存在 |
| 40003 | 数据已存在 |
| 50001 | 系统异常 |

### 15.3 全局异常处理
如项目已有全局异常处理器，直接扩展；否则新增统一处理器。

---

## 16. 统一返回设计

### 16.1 返回结构
```json
{
  "code": 0,
  "message": "success",
  "data": {}
}
```

### 16.2 设计要求
- 成功和失败返回格式统一
- 与项目已有返回体保持一致
- 不要新增重复返回结构

---

## 17. 分页设计

### 17.1 请求参数
- pageNum
- pageSize

### 17.2 返回参数
- total
- list
- pageNum
- pageSize

### 17.3 设计要求
- 默认页码为 1
- 默认每页 10 条
- 最大每页 100 条
- 分页结果必须清晰

---

## 18. 校验规则

### 18.1 创建校验
- name：非空，长度限制
- status：非空
- remark：可空，长度限制

### 18.2 更新校验
- id：非空
- 其他字段同创建

### 18.3 查询校验
- pageNum：最小 1
- pageSize：1 到 100

---

## 19. 文件清单

请生成以下文件：

### Java 文件
- controller/[ControllerName].java
- service/[ServiceName].java
- service/impl/[ServiceImplName].java
- mapper/[MapperName].java
- entity/[EntityName].java
- dto/[CreateDTO].java
- dto/[UpdateDTO].java
- dto/[QueryDTO].java
- vo/[VOName].java
- enums/[StatusEnum].java
- exception/[BusinessException].java
- exception/[ErrorCode].java

### 资源文件
- resources/mapper/[MapperName].xml

### 如项目需要
- config 相关配置
- 全局异常处理类
- 分页工具类
- 常量类补充

---

## 20. 开发顺序要求

请按以下顺序生成代码：

1. 分析当前项目结构
2. 输出设计说明
3. 输出文件清单
4. 输出建表 SQL
5. 生成 Entity
6. 生成 DTO
7. 生成 VO
8. 生成 Enum
9. 生成 Mapper 接口
10. 生成 Mapper XML
11. 生成 Service
12. 生成 ServiceImpl
13. 生成 Controller
14. 生成异常处理
15. 检查编译问题
16. 修复问题
17. 输出简要使用说明

---

## 21. 代码生成要求

1. 生成的是可直接运行的代码
2. 所有文件必须注明路径
3. 不要省略 import
4. 不要省略关键注解
5. 不要输出伪代码
6. 不要输出与项目无关内容
7. 如项目已有同类类，请优先复用
8. 如有不确定项，先列出最少必要确认项，再继续生成

---

## 22. 验收标准

模块完成后应满足：

- 能新增数据
- 能修改数据
- 能逻辑删除
- 能查询详情
- 能分页查询
- 能条件筛选
- 能正确处理异常
- 能通过编译
- 能符合当前项目风格
