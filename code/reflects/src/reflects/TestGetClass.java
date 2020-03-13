package reflects;

/**
 * @author: huhao
 * @time: 2020/3/13 18:05
 * @desc: 获取class对象的多种方式
 */
public class TestGetClass {

    public static void main(String[] args) {

        // 1. 通过类的对象获取class对象
        // 类的对象
        Student s = new Student();
        // 类对象（Class对象，保存了Student.class这个文件的所有信息）
        Class c1 = s.getClass();

        Teacher t = new Teacher();
        Class c2 = t.getClass();

        System.out.println(c1.getName());    // reflects.Student
        System.out.println(c2.getName());    // reflects.Teacher

        // 2. 通过类名获取类对象
        Class c3 = Student.class;
        System.out.println(c3.getName());    //reflects.Student

        // 3. 通过静态方法获取类对象
        try {
            Class c4 = Class.forName("reflects.Teacher");
            System.out.println(c4.getName());    // reflects.Teacher

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类对象方法
     * @param className
     */
    public static void getClassObject(String className){
        try {
            Class c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Student{

    private String name;
    private int age;
    private String sex;

    public Student() {

    }

    public Student(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void study(){

    }

    public double exam(){
        return 0.0;
    }
}

class Teacher{
    private String name;
    private int age;
    private String sex;

    public Teacher() {
    }

    public Teacher(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void teach(){

    }
}