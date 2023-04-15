import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class Utils {


    public static Long[] getArrayFromFile(String path) {
        ArrayList array = new ArrayList<Long>();

        Scanner sc = null;
        try{
            sc = new Scanner(new FileInputStream(path));
            long value;
            while(sc.hasNextLine()){
                array.add(parseLong(sc.nextLine())) ;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Long[] data = (Long[]) array.toArray(new Long[0]);
        return data;
    }
}
