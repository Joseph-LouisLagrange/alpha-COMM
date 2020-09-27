import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 杨能
 * @create 2020/9/24
 */
public class MyTypeAdapterFactory implements TypeAdapterFactory {

    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\ASUS\\Desktop\\new 1.txt");
        StringBuffer stringBuffer = new StringBuffer();
        char[] buf = new char[100];
        int l;
        while ((l = fileReader.read(buf)) != -1) {
            stringBuffer.append(buf, 0, l);
        }
        FileWriter fileWriter = new FileWriter("C:\\Users\\ASUS\\Desktop\\新建文本文档.txt");

        String s = stringBuffer.toString();
        String[] q = s.split("\\s+");
        System.out.println(q[q.length - 1]);
        Arrays.stream(q)
                .distinct()
                .map(s1 -> "public static String " + s1.replaceAll("[^\\w]", "_").toUpperCase() + " = \"" + s1 + "\";\n")
                .forEach(s1 -> {
                    try {
                        System.out.println(s1);
                        fileWriter.append(s1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
    }
}
