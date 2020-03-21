package entity;

import java.util.Date;

/**
 * @author: huhao
 * @time: 2020/3/21 17:06
 * @desc:
 */
public class Student {

    private int sid;
    private String sname;
    private int sage;
    private String ssex;
    private Date birthday;
    private double score;

    public Student() {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Student(int sid, String sname, int sage, String ssex, Date birthday, double score) {
        this.sid = sid;
        this.sname = sname;

        this.sage = sage;
        this.ssex = ssex;
        this.birthday = birthday;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", ssex='" + ssex + '\'' +
                ", birthday=" + birthday +
                ", score=" + score +
                '}';
    }
}
