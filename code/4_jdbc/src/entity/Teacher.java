package entity;


/**
 * @author: huhao
 * @time: 2020/3/21 15:29
 * @desc:
 */
public class Teacher {

    private int Tno;
    private String Tname;

    public Teacher() {
    }

    public Teacher(int tno, String tname) {
        Tno = tno;
        Tname = tname;
    }

    public int getTno() {
        return Tno;
    }

    public void setTno(int tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Tno=" + Tno +
                ", Tname='" + Tname + '\'' +
                '}';
    }
}
