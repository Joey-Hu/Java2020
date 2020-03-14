## MySQL基础

### 1. 基本操作

```

1. 数据库（database）：存储数据的仓库

2. 数据库管理系统（DBMS）：MySQL	Oracle	SQL Server DB2

3. MySQL 安装、卸载

4. SQL 语句：
	
	DDL 数据定义语言：创建库，创建表    CREATE DATABASE    CREATE TABLE
	
	DML 数据操纵语言：添加    删除    更新    INSERT    DEETE    UPDATE
	
	DQL 数据查询语言：SELECT
	
	DCL 数据控制语言：用户的创建    权限分配
	
5. DQL
	
	简单查询    SELECT * FROM emp;    查询所有列
	
	条件查询  WHERE     运算符    =    !=    <>    >    >=    <    <=    IN    BETWEEN...AND    AND    OR    NOT    +  -  *  /  %
   
    模糊查询    LIKE    通配符   \_表示一个字符   %表示任意多个字符（0~n）
   
    字段控制    DISTINCT  去重    CONCAT  合并    AS  别名
   
    排序    ORDER BY    ASC  升序    DESC  降序
   
    聚合函数    COUNT  SUM  MAX  MIN  AVG
   
    分组查询    GROUP BY    HAVING  筛选
   
    LIMIT    限制查询
   
    书写顺序    SELECT 列表 FROM 表 【WHERE ----> GROUP BY ----> HAVING ----> ORDER BY ----> LIMIT】
    执行顺序    FROM 表 WHERE ----> GROUP BY ----> HAVING ----> SELECT ----> ORDER BY----> LIMIT
```

### 2. 数据完整性

作用：保证用书输入的数据保存到数据库中是正确的。-- 目前一般在前端页面进行验证

确保数据库的完整性 = 在创建表时给表添加约束

完整性分类：

* 实体完整性：行
* 域完整性：列
* 引用完整性：学生表（学号，姓名）    成绩表（学号，科目，成绩）    课程表（科目编号，科目名称）

#### 2.1 实体完整性约束

实体：表中的一行记录即代表一个实体（entity）

实体完整性作用：保证每一行数据不重复

约束类型：

* 主键约束（PRIMARY KEY）
* 唯一约束（UNIQUE）
* 自动增长列（AUTO_INCREMENT ）

**1. 主键约束**

特点：数据唯一且非空

添加方式1：

```mysql
CREATE TABLE student(
	id INT PRIMARY KEY,
	name VARCHAR(10)
);
```

添加方式2：

```mysql
CREATE TABLE student(
	classID INT,
	id INT,
	name VARCHAR(10),
	PRIMARY KEY(classID, id)
);
```

**2. 唯一约束**

数据不能重复，可为null

```mysql
CREATE TABLE student(
	id INT PRIMARY KEY,
	name VARCHAR(10) UNIQUE
);
```


**3. 自动增长列**

自动增长不能单独使用，一般配合主键使用。

给主键添加自动增长的数值，列只能是数值类型

```mysql
CREATE TABLE student(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(10) UNIQUE
) auto_increment = 100;
```

#### 2.2 域完整性约束



#### 2.3 引用完整性约束

外键约束：FOREIGN KEY

添加方式1：

```mysql
# 学生表（主表）
CREATE TABLE student(
	sid INT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	sex VARCHAR(10) DEFAULT 'male'
);
```

```mysql
# 成绩表（从表）
CREATE TABLE score(
	id INT,
	sid INT, 
	score DOUBLE(5,2),
	PRIMARY KEY(id, sid),
	CONSTRAINT fk_score_sid FOREIGN KEY(sid) REFERENCES student(id)
);
```

外键列的类型要与主键类型一致。


### 3. 多表查询




### 4. 数据库事务



#### 5. 管理与常用函数




#### 5. 视图