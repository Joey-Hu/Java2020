## Spring

### 1. 概述

Spring 是一个分层的 Java SE/EE 应用的 full-stack 开发框架，以 IoC（Inverse od Control: 反转控制） 和 AOP（Aspect Oriented Programming: 面向切面编程）提供了展现层 Spring MVC 和 持久层 Spring JDBC 以及业务层事务管理等众多企业级应用技术，还能整合世界众多的第三方框架和类库，逐渐成为使用最多的 Java EE 企业应用开源框架。

### 2. 三层架构、MVC 和 SSM

#### 2.1 三层架构

三层架构(3-tier application) 通常意义上的三层架构就是将整个业务应用划分为：表现层（UI）、业务逻辑层（BLL）、数据访问层（DAL）。区分层次的目的即为了“高内聚，低耦合”的思想，复杂项目不能把SQL语句直接写到程序里，不模块话，难以维护。应该采取三层架构。

1. 表现层：展现给用户的界面。
2. 业务逻辑层：针对具体问题的操作，对数据业务逻辑的处理。
3. 数据访问层：直接操作数据库，对数据库进行增删改查。

UI层调用BLL，BLL调用DAL，数据用Model进行传递，Model为各层之间架起了数据传输的桥梁。

优势：
1. 开发人员可以只关注整个结构中的其中某一层；
2. 可以很容易的用新的实现来替换原有层次的实现；
3. 可以降低层与层之间的依赖；
4. 有利于标准化；
5. 利于各层逻辑的复用。

缺点：
1. 降低了系统的性能，不适合用来开发小型项目。
2. 增加了开发成本。

#### 2.2 MVC

MVC框架是由Model模型（JavaBean），View视图(Jsp) 和 Controller控制器(Servlet)构成。

用户通过View页面向服务端提出请求，可以是表单请求、超链接请求、AJAX请求等。服务端Controller控制器接收到请求后对请求进行解析，找到相应 的Model对用户请求进行处理。Model处理后，将处理结果再交给ControllerController在接到处理结果后，根据处理结果找到要作为向客户端发回的响应View页面。页面经渲染（数据填充）后，再发送给客户端。

!["spring_MVC.png"](\img\spring_MVC.png)

MVC 和三层架构的关系：

三层架构是一种软件领域最普遍的分层式架构，而 MVC 是在三层架构的基础上设计的一种框架型架构，三层架构是一种宏观的概念，而 **MVC 就是一种比较具体的三层架构的框架实现**，我们在 MVC 的基础上把不同类别的代码文件进行分类就可以了。

一般认为View 层和 Controller 层都属于三层架构的用户界面层，而Model属于业务逻辑层和数据访问层。

#### 2.3 SSM

SSM主要由Spring，SpringMVC 和 Mybatis三个构成。它们在三层架构中所处的位置是不同的，即它们在三层架构中的功能各不相同，各司其职。

SpringMVC：作为View层的实现者，完成用户的请求接收功能。SpringMVC的Controller作为整个应用的控制器，完成用户请求的转发及对用户的响应。

MyBatis：作为 Dao层的实现者，完成对数据库的增、删、改、查功能。

Spring：以整个应用大管家的身份出现。整个应用中所有的Bean的生命周期行为，均由Spring来管理。即整个应用中所有对象的创建、初始化、销毁，及对象间关联关系的维护，均由Spring进行管理。



### 3. Spring 项目

#### 3.0 程序的耦合

耦合：程序见的依赖关系（类与类之间的依赖，方法与方法之间的依赖）

解耦：降低程序建的依赖关系，实际开发中，要做到：编译器不依赖，运行时才依赖

具体思路：

1. 通过反射来创建对象，避免使用 new 关键字；

   ```java
   Class.forName("com.mysql.jdbc.Driver");    // 反射
   ```

2. 通过读取配置文件来获取要创建的对象的全限定类名。

**实例：**

在之前的三层架构中会存在明显的耦合现象，代码如下所示：

```java
/*业务层代码*/
public class AccountServiceImpl implements AccountService {
	
    // 业务层代码依赖持久层
    private AccountDao accountDao = new AccountDaoImpl();

    public void saveAccount() {

        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}

/*表现层代码*/
public class Client {

    public static void main(String[] args) {
		
        // 表现层代码依赖业务层
        AccountService accountService = new AccountServiceImpl();

        for (int i = 0; i < 5; i++) {
            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
```

从上面的代码中我们可以看到，业务层代码依赖持久层，表现层代码依赖业务层。

下面就利用 BeanFactory 解决耦合问题：

> JavaBean：用java语言编写的可重用组件。
>
> javabean >  实体类

BeanFactory 就是一个创建 Bean 对象的工厂，可以用于创建 dao 和 service 对象。

创建方法：

1. 建立配置文件：唯一标识 = 全限定类名（key=value），配置文件类型可以是 xml，也可以是properties
2. 通过读取配置文件中配置的内容，反射创建对象

bean.properties

```properties
accountDao=com.hh.dao.impl.AccountDaoImpl
accountService=com.hh.service.impl.AccountServiceImpl
```

BeanFactory.properties

```java
public class BeanFactory {

    private static Properties properties;

    /**
     * 定义一个Map,用于存放我们要创建的对象。我们把它称之为容器
     */
    private static HashMap<String, Object> beans;

    /*使用静态代码块为Properties对象赋值*/
    static {
        properties = new Properties();
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(resourceAsStream);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 获取配置文件中所有的key
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String beanPath = properties.getProperty(key);
                Object bean = Class.forName(beanPath).newInstance();
                beans.put(key, bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){

        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     */
    /*public static Object getBean(String beanName){

        Object bean = null;
        String beanPath = properties.getProperty(beanName);

        try {
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/
}
```

> 出现的问题：class.getClassLoader().getResourceAsStream("path") 和class.getResourceAsStream("path")的区别
>
> https://blog.csdn.net/weixin_42373448/article/details/80834185
>
> https://blog.csdn.net/feeltouch/article/details/83796764

```java
/**
 * @author: huhao
 * @time: 2020/4/26 12:11
 * @desc: 账户业务层的接口实现类
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl();

    /**
     * 使用工厂类的方法获取实体类
     */
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {

        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}

/**
 * @author: huhao
 * @time: 2020/4/26 12:16
 * @desc:
 */
public class Client {

    public static void main(String[] args) {

        // AccountService accountService = new AccountServiceImpl();

        for (int i = 0; i < 5; i++) {
            AccountService accountService = (AccountService) BeanFactory.getBean("accountService");

            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
```

**控制反转（IoC，Inverse of Control）**

之前我们获取对象时，是采用 new 方式，**是主动的**；

![Ioc1.png](/img/Ioc1.png) 

现在是通过工厂来查找或创建对象，**是被动的**。

![Ioc2.png](/img/Ioc2.png)

控制反转是把创建对象的权利交给框架，是框架的重要特征，他包括依赖注入（DI）和依赖查找。IoC的作用就是削减程序的耦合。


#### 3.1 建立 Spring 项目步骤

1. 新建 maven 工程

2. 编写持久层和业务层逻辑代码

3. pom 文件导入 spring 依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.0.2.RELEASE</version>
</dependency>
```

4. xml 文件添加约束 xmlns -- 在文档中查找 "xmlns"

   4.1. 把对象的创建交给 spring 管理 id class

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="accountDao" class="com.hh.www.dao.impl.AccountDaoImpl">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="accountService" class="com.hh.www.service.impl.AccountServiceImpl">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->
</beans>
```

5. 获取 spring 的 Ioc 核心容器，并根据 id 获取对象

```java
public class Client {

    public static void main(String[] args) {

        // 1. 获取核心容器对象
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 根据 id 获取 Bean 对象
        IAccountDao accountDao = classPathXmlApplicationContext.getBean("accountDao", IAccountDao.class);
        IAccountService accountService = classPathXmlApplicationContext.getBean("accountService", IAccountService.class);

        System.out.println(accountDao);
        System.out.println(accountService);
    }
}
```

**ApplicationContext的三个常用实现类：**

1. ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。(更常用)
2. FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）
3. AnnotationConfigApplicationContext：它是用于读取注解创建容器的。

**核心容器的两个接口引发出的问题：**

![ApplicationContext.png](/img/ApplicationContext.png)

```markdown
ApplicationContext:     单例对象适用              实际开发中采用此接口
	它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。

BeanFactory:            多例对象使用
	它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
```

出现的问题：IDEA_maven依赖错误 包下面红色波浪线（ https://blog.csdn.net/u010003835/article/details/78480626 ）

#### 3.2 spring 对 bean 的管理细节

**创建bean的三种方式**

1. 使用默认构造函数创建。

   在spring的配置文件中使用bean标签，配以id和class属性之后，且没有其他属性和标签时。采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数，则对象无法创建。
   
```java
public class AccountServiceImpl implements IAccountService{

    // 构造方法
    public  AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    public void saveAccount() {

        System.out.println("service中的saveAccount方法执行了。。。");

    }
}
```

```xml
<bean id="accountService" class="com.hh.www.service.impl.AccountServiceImpl"></bean>
```

2. 使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器 -- 该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）

```java
// 假设该类是 jar 包中的类，我们不能修改源码，只能通过普通工厂中的方法来创建对象
public class InstanceFactory {
    
    public IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
```

```xml
<bean id="instanceFactory" class="com.hh.www.factory.InstanceFactory"></bean>
<bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"></bean>
```

3. 使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器 -- 同上)

```java
// 静态工厂类
public  class staticFactory {

    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
```

```xml
<bean id="accountService" class="com.hh.www.factory.staticFactory" factory-method="getAccountService"></bean>
```

#### 3.3 Bean 的作用范围

```xml
bean的作用范围调整
    bean标签的scope属性：
        作用：用于指定bean的作用范围
        取值： 常用的就是单例的和多例的
            singleton：单例的（默认值）
            prototype：多例的
            request：作用于web应用的请求范围
            session：作用于web应用的会话范围
            global-session：作用于集群环境的会话范围（全局会话范围），当不是集群环境时，它就是session

<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl" scope="prototype"></bean>
```

扩展：负载均衡

#### 3.4 Bean 对象的生命周期

```markdown
bean对象的生命周期
    单例对象
        出生：当容器创建时对象出生
        活着：只要容器还在，对象一直活着
        死亡：容器销毁，对象消亡
        总结：单例对象的生命周期和容器相同
    多例对象
        出生：当我们使用对象时spring框架为我们创建
        活着：对象只要是在使用过程中就一直活着。
        死亡：当对象长时间不用，且没有别的对象引用时，由Java的垃圾回收器回收
```

#### 3.5 Spring 中的依赖注入

```markdown
依赖注入：
    Dependency Injection
    IOC的作用：
        降低程序间的耦合（依赖关系）
    依赖关系的管理：
    	以后都交给spring来维护
    	在当前类需要用到其他类的对象，由spring为我们提供，我们只需要在配置文件中说明
    依赖关系的维护：
    	就称之为依赖注入。
    依赖注入：
    	能注入的数据：有三类
            基本类型和String
            其他bean类型（在配置文件中或者注解配置过的bean）
            复杂类型/集合类型
        注入的方式：有三种
            第一种：使用构造函数提供
            第二种：使用set方法提供
            第三种：使用注解提供（明天的内容）
```

```java
public class AccountServiceImpl implements IAccountService {

    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name,Integer age,Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void  saveAccount(){
        System.out.println("service中的saveAccount方法执行了。。。"+name+","+age+","+birthday);
    }


}
```



1. 构造函数注入：

```xml
使用的标签:constructor-arg
	标签出现的位置：bean标签的内部
	标签中的属性
        type：用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型
        index：用于指定要注入的数据给构造函数中指定索引位置的参数赋值。索引的位置是从0开始
        name：用于指定给构造函数中指定名称的参数赋值                                        常用的
		=============以上三个用于指定给构造函数中哪个参数赋值===============================
        value：用于提供基本类型和String类型的数据
        ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象

	优势：
		在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。
	弊端：
		改变了bean对象的实例化方式，使我们在创建对象时，如果用不到这些数据，也必须提供。

<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
    <constructor-arg name="name" value="泰斯特"></constructor-arg>
    <constructor-arg name="age" value="18"></constructor-arg>
    <constructor-arg name="birthday" ref="now"></constructor-arg>
</bean>

<!-- 配置一个日期对象 -->
<bean id="now" class="java.util.Date"></bean>
```

2. set方法注入(更常用)

```xml
涉及的标签：property
出现的位置：bean标签的内部
标签的属性
    name：用于指定注入时所调用的set方法名称
    value：用于提供基本类型和String类型的数据
    ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象
优势：
	创建对象时没有明确的限制，可以直接使用默认构造函数
弊端：
	如果有某个成员必须有值，则获取对象是有可能set方法没有执行。

<bean id="accountService2" class="com.itheima.service.impl.AccountServiceImpl2">
    <property name="name" value="TEST" ></property>
    <property name="age" value="21"></property>
    <property name="birthday" ref="now"></property>
</bean>
```

3. 复杂类型的注入/集合类型的注入

```xml
<!-- 复杂类型的注入/集合类型的注入
        用于给List结构集合注入的标签：
            list array set
        用于个Map结构集合注入的标签:
            map  props
        结构相同，标签可以互换
    -->
<bean id="accountService3" class="com.itheima.service.impl.AccountServiceImpl3">
    <property name="myStrs">
        <set>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </set>
    </property>

    <property name="myList">
        <array>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </array>
    </property>

    <property name="mySet">
        <list>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </list>
    </property>

    <property name="myMap">
        <props>
            <prop key="testC">ccc</prop>
            <prop key="testD">ddd</prop>
        </props>
    </property>

    <property name="myProps">
        <map>
            <entry key="testA" value="aaa"></entry>
            <entry key="testB">
                <value>BBB</value>
            </entry>
        </map>
    </property>
</bean>
```





### 参考：

https://yq.aliyun.com/articles/390874

https://www.jianshu.com/p/bb6f1b7ea7ec

《spring5第一天》