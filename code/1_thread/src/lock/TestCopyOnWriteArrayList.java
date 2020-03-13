package lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: huhao
 * @time: 2020/3/11 15:51
 * @desc:
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Map<String, String> map = new ConcurrentHashMap<String, String>();

        list.add("A");

        System.out.println(list.toString());
    }
}
