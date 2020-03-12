package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: huhao
 * @time: 2020/3/12 11:38
 * @desc:
 */
public class testPropertiesForTake {

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();

        FileInputStream is = new FileInputStream("info\\userInfo.properties");

        prop.load(is);

        for(Object obj : prop.entrySet()) {
            System.out.println(obj.toString());
        }

    }
}
