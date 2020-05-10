import com.hh.dao.IEmpDao;
import com.hh.dao.impl.EmpDaoImpl;
import com.hh.domaim.Emp;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: huhao
 * @time: 2020/5/10 11:05
 * @desc:
 */
public class TestEmp {

    @Test
    public void getAll(){

        IEmpDao empDao = new EmpDaoImpl();
        ArrayList<Emp> ret = empDao.getAll();
        for (Emp emp : ret) {
            emp.toString();
        }

    }

}
