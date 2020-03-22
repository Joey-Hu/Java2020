## ArrayList 源码分析

### 1. 深拷贝和浅拷贝(Cloneable 标记性接口)

浅拷贝：只是拷贝了基本类型的数据，**而引用类型数据，复制后也是会发生引用**，我们把这种拷贝叫做“（浅复制）浅拷贝”，换句话说，浅复制仅仅是指向被复制的内存地址，如果原地址中对象被改变了，那么浅复制出来的对象也会相应改变。

深拷贝：在计算机中开辟了一块新的内存地址用于存放复制的对象。

### 2. Serializable 标记性接口

### 3. RandomAccess 标记性接口

此接口的目的是允许通用算法更改其行为，以便在应用于**随机访问列表**和**顺序访问列表**是提供良好的性能。

通常**实现该接口的列表（如 ArrayList）**在使用随机访问时要比使用顺序访问的效率要高。

在实际开发中，遇到要访问列表时，可以检查列表是否实现了 RandomAccessable 接口（instanceof），在必要时更改其行为以保证可接受的性能。

随机访问：

```java
for(int i=0; i<list.size(); i++){
    list.get(i);
}
```

顺序访问：

```java
for(Iterator i=list.iterator(); i.hasNext();){
    i.next();
}

```
instanceof：

```java
if(list instanceof RandomAccessable){
    // 随机访问
}else{
    // 顺序访问
}
```

### 4. AbstractLIst 抽象类

### 5. ArratList 源码分析

#### 5.1 构造方法


|             Constructor              |                             描述                             |
| :----------------------------------: | :----------------------------------------------------------: |
|             ArrayList()              |                构造一个初始容量为十的空列表。                |
|    ArrayList(int initialCapacity)    |                构造具有指定初始容量的空列表。                |
| ArrayList(Collection<? extends E> c) | 构造一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序。 |


#### 5.2 添加方法

|                        方法名                        |                             描述                             |
| :--------------------------------------------------: | :----------------------------------------------------------: |
|                   boolean add(E e)                   |               将指定的元素追加到此列表的末尾。               |
|            void add(int index, E element)            |             在此列表中的指定位置插入指定的元素。             |
|      boolean addAll(Collection<? extends E> c)       | 按指定集合的Iterator返回的顺序将指定集合中的所有元素追加到此列表的末尾。 |
| boolean addAll(int index, Collection<? extends E> c) |   将指定集合中的所有元素插入到此列表中，从指定的位置开始。   |













代码示例 -- 浅拷贝

```java
package copy;

/**
 * @author: huhao
 * @time: 2020/3/22 8:46
 * @desc: 浅拷贝
 */
public class TestCopy01 {

    public static void main(String[] args) throws CloneNotSupportedException {

        Skill skill = new Skill("天地会香主");

        Student student1 = new Student(1, "韦小宝", 89.0, skill);

        Object student2 = student1.clone();

        System.out.println(student1 == student2);
        System.out.println(student1);
        System.out.println(student2);

        System.out.println("========修改student1内容=========");
        student1.setsScore(90.5);
        skill.setSkillName("一品鹿鼎公");
        System.out.println(student1);
        System.out.println(student2);

        /**
         * 代码运行结果：
         * 
         false
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='天地会香主'}}
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='天地会香主'}}
         ========修改student1内容=========
         Student{sNo=1, sName='韦小宝', sScore=90.5, sSkill=Skill{skillName='一品鹿鼎公'}}
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='一品鹿鼎公'}}
         */

    }
}

class Student implements Cloneable {

    private int sNo;
    private String sName;
    private double sScore;
    private Skill sSkill;

    public Student() {
    }

    public Student(int sNo, String sName, double sScore, Skill sSkill) {
        this.sNo = sNo;
        this.sName = sName;
        this.sScore = sScore;
        this.sSkill = sSkill;
    }

    /**
     * 修改方法访问修饰符为public
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public double getsScore() {
        return sScore;
    }

    public void setsScore(double sScore) {
        this.sScore = sScore;
    }

    public Skill getsSkill() {
        return sSkill;
    }

    public void setsSkill(Skill sSkill) {
        this.sSkill = sSkill;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sNo=" + sNo +
                ", sName='" + sName + '\'' +
                ", sScore=" + sScore +
                ", sSkill=" + sSkill +
                '}';
    }
}

class Skill {

    private String skillName;

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                '}';
    }
}
```

代码示例 -- 深拷贝

```java
package copy;

/**
 * @author: huhao
 * @time: 2020/3/22 8:46
 * @desc: 深拷贝
 */
public class TestCopy01 {

    public static void main(String[] args) throws CloneNotSupportedException {

        Skill skill = new Skill("天地会香主");

        Student student1 = new Student(1, "韦小宝", 89.0, skill);

        Object student2 = student1.clone();

        System.out.println(student1 == student2);
        System.out.println(student1);
        System.out.println(student2);

        System.out.println("========修改student1内容=========");
        student1.setsScore(90.5);
        skill.setSkillName("一品鹿鼎公");
        System.out.println(student1);
        System.out.println(student2);

        /**
         * 代码运行结果：
         *
         false
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='天地会香主'}}
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='天地会香主'}}
         ========修改student1内容=========
         Student{sNo=1, sName='韦小宝', sScore=90.5, sSkill=Skill{skillName='一品鹿鼎公'}}
         Student{sNo=1, sName='韦小宝', sScore=89.0, sSkill=Skill{skillName='天地会香主'}}
         */

    }
}

class Student implements Cloneable {

    private int sNo;
    private String sName;
    private double sScore;
    private Skill sSkill;

    public Student() {
    }

    public Student(int sNo, String sName, double sScore, Skill sSkill) {
        this.sNo = sNo;
        this.sName = sName;
        this.sScore = sScore;
        this.sSkill = sSkill;
    }

    /**
     * 修改方法访问修饰符为public
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // return super.clone();    // 深拷贝不能简单调用父类方法

        Student stu = (Student) super.clone();

        Skill clone = (Skill) this.sSkill.clone();

        stu.setsSkill(clone);

        return stu;
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public double getsScore() {
        return sScore;
    }

    public void setsScore(double sScore) {
        this.sScore = sScore;
    }

    public Skill getsSkill() {
        return sSkill;
    }

    public void setsSkill(Skill sSkill) {
        this.sSkill = sSkill;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sNo=" + sNo +
                ", sName='" + sName + '\'' +
                ", sScore=" + sScore +
                ", sSkill=" + sSkill +
                '}';
    }
}

class Skill implements Cloneable{

    private String skillName;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                '}';
    }
}
```


### 参考：

https://blog.csdn.net/m0_37631322/article/details/81634512