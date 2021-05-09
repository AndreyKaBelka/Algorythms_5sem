package algs.twelfth;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task {
    private static float pricePerMinute = 1.5f;
    public static void main(String[] args) {
        Map<String, Object> hashTable = new Table<>();
        try (FileReader file = new FileReader("src/java/algs/twelfth/data.txt")) {
            BufferedReader reader = new BufferedReader(file);
            List<String> data = reader.lines().collect(Collectors.toList());
            for (String line: data) {
                List<Object> res = new ArrayList<>();
                List<String> da = Arrays.asList(line.split(" "));
                float price = Integer.parseInt(da.get(4)) * pricePerMinute;
                String fio = da.get(1) + " " +
                        da.get(2) + " " +
                        da.get(3);
                res.add(fio);
                res.add(Integer.parseInt(da.get(4)));
                res.add(price);
                hashTable.put(da.get(0), res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(hashTable);
    }
}
