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

事务：一组要么同时执行成功，要么同时失败的 SQL 语句。

**事务开始于**

* 连接到数据库上，并执行一条 DML 语句（insert, uodate或delete）
* 前一个事务结束后，又输入了另一条 DML 语句

**事务结束于**

* 执行了 commit （提交，当所有内容都成功，再提交）或 rollback（回滚，一个事务过程中，有一个失败，则全部失败） 语句。
* 执行了一条 DDL 语句，例如 CREATE TABLE 语句，在这种情况下，会自动执行 commit 语句。
* 执行了一条 DDL 语句，例如 GRANT 语句，在这种情况下，会自动执行 commit 语句。
* 断开与数据库的连接
* 执行了一条 DML 语句，该语句却失败了，在这种情况下，会为这个无效的 DML 语句执行 rollback 语句。

#### 4.1 事务的四大特性

(ACID)

* 原子性（Atomicity）：表示一个事务中所有曹组是一个整体，要么全部成功，要么全部失败；
* 一致性（Consistency）：表示一个事务内有一个操作失败，所有更改都必须回滚到修改前状态；
* 隔离性（Isolation）：事务查看数据所处的状态，要么是另一并发事务修改它之前的状态，要么是另一并发事务修改它之后的状态，事务不会查看中间状态的数据；
* 持久性（Durability）：持久性事务完成之后，对数据库的影响是永久性的。

```mysql
# 开启事务
START TRANSACTION;

# 事务内部DML
UPDATE account SET money = money-100 WHERE id=1;
UPDATE account SET money = money+100 WHERE id=2;

# 提交事务
COMMIT; # connection.commit();

# 回滚
ROLLBACK; # connection.rollback()

```

### 5. 管理与常用函数

#### 5.1 导出导入数据库

导出数据库表

```mysql
mysqldump -uroot -p 数据库名 > D:/school.sql
```

导入数据库

```mysql
mysql -uroot -p
mysql>use 数据库
# 然后使用source命令，后面参数为脚本文件
mysql>source D:/dbname.sql
```

#### 5.2 创建用户和授权

#### 5.3 日期处理

#### 5.4 字符串处理	

### 6. 视图 
