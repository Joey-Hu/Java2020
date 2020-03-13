package used;

import java.nio.channels.ClosedSelectorException;

/**
 * @author: huhao
 * @time: 2020/3/13 20:09
 * @desc:
 */
public class TestReflectNewInstance {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // 1. 使用手工的方式new对象
        Student s1 = new Student("Tom");

        // 2. 使用反射创建类的对象
        Class<?> c = Class.forName("used.Student");

//        Object o = c.newInstance();
        Student s2 = (Student) c.newInstance();

        Object o = createObject("used.Lamp");
        System.out.println(o);


    }

    public static Object createObject(String className){
        try {
            Class<?> c = Class.forName(className);
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

    public Double exam(){
        System.out.println("examing ...");
        calc();

        return score;
    }

    private void calc() {
        System.out.println("calc ...");
    }
}