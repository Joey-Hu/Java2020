package invokes;

import java.lang.reflect.Method;

/**
 * @author: huhao
 * @time: 2020/3/13 21:00
 * @desc:
 */
public class TestInvokeMethods {

    public static void main(String[] args) throws Exception {

//        Object o = objectFactory("invokes.Student");
//
//        Student s = (Student)o;
//
//        // 调用方法
//        s.exam();
//        s.study();

        // 反射机制实现方法调用
        // 通过工厂类创建一个类（Student）对象
        Object stu = objectFactory("invokes.Student");

        // 通过学生对象获得了所属的类对象
        Class c = stu.getClass();

        // 通过类对象获得其中一个方法
//        Method m = c.getMethod("study", Integer.class);
        Method m = c.getMethod("study", null);

        // 通过 invoke，让 study 实例方法执行起来，所需的对象作为第一个参数，所需的方法调用实参作为第二个参数
        m.invoke(stu,null);     // syu.study();

    }

    public static Object objectFactory(String className){

        try {
            Class c = Class.forName(className);
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

class Student{

    private String name;
    private Integer age;
    private String sex;
    private Double score;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public void study(){
        System.out.println("studying ...");
    }

    public void study(int a){
        System.out.println("studying ...");
    }

    public Double exam(){
        System.out.println("examing ...");
        calc();

        return score;
    }

    private void calc() {
        System.out.println("calc ...");
    }
}