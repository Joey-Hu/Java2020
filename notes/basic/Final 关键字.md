## final 关键字

### 1.final 关键字的使用

* final 变量
final变量有成员变量或者是本地变量(方法内的局部变量)，在类成员中final经常和static一起使用，作为类常量使用。其中类常量必须在声明时初始化，final成员常量可以在构造函数初始化。

```java
public static void main(String[] args){
	public static final int i = 2;
    ...
}
```

* final 修饰基本数据类型变量和引用
final方法表示该方法不能被子类的方法重写，将方法声明为final，在编译的时候就已经静态绑定了，不需要在运行时动态绑定。final方法调用时使用的是invokespecial指令

```java
class PersonalLoan{
    public final String getName(){
        return"personal loan”;
    }
}

class CheapPersonalLoan extends PersonalLoan{
    @Override
    public final String getName(){
        return"cheap personal loan";//编译错误，无法被重载
    }

    public String test() {
        return getName(); //可以调用，因为是public方法
    }
}
```
* final 类
final类不能被继承，final类中的方法默认也会是final类型的，java中的String类和Integer类都是final类型的。

### 2. final 优点
1. 提高了性能，JVM在常量池中会缓存final变量

2. final变量在多线程中并发安全，无需额外的同步开销

3. final方法是静态编译的，提高了调用速度

4. final类创建的对象是只可读的，在多线程可以安全共享

### 3. final方法的三条规则
规则1：final修饰的方法不可以被重写。

规则2：final修饰的方法仅仅是不能重写，但它完全可以被重载。

规则3：父类中private final方法，子类可以重新定义，这种情况不是重写。

```java
规则1代码

public class FinalMethodTest
{
	public final void test(){}
}
class Sub extends FinalMethodTest
{
	// 下面方法定义将出现编译错误，不能重写final方法
	public void test(){}
}

规则2代码

public class Finaloverload {
	//final 修饰的方法只是不能重写，完全可以重载
	public final void test(){}
	public final void test(String arg){}
}

规则3代码

public class PrivateFinalMethodTest
{
	private final void test(){}
}
class Sub extends PrivateFinalMethodTest
{
	// 下面方法定义将不会出现问题
	public void test(){}
}
```






参考：
https://github.com/Joey-Hu/Java-Tutorial/blob/master/docs/java/basic/4%E3%80%81final%E5%85%B3%E9%94%AE%E5%AD%97%E7%89%B9%E6%80%A7.md