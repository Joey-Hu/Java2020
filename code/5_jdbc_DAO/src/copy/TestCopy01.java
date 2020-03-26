package copy;

import java.util.ArrayList;

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

