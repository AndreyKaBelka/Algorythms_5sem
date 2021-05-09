package algs.eleventh;

import algs.tenth.Sampling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Petr {

    public static void main(String[] args) {
        HashMap<String, Integer> table = new HashMap<>() {
            {
                put("Pencil", 12);
                put("Sheet", 20);
                put("Eraser", 1);
                put("Pen", 10);
                put("Lala", 13);
            }
        };
        ArrayList<Map.Entry<String, Integer>> buyList = new ArrayList<>() {
            {
                add(Map.entry("Pencil", 2));
                add(Map.entry("Pen", 1));
                add(Map.entry("Sheet", 1));
                add(Map.entry("Eraser", 2));
            }
        };

        List<String> indexes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : buyList) {
            for (int i = 0; i < entry.getValue(); i++) {
                indexes.add(entry.getKey());
            }
        }
        List<List<String>> ar = new Sampling().subsets(indexes.toArray(String[]::new));
        int target = 40;
        List<String> toBuy = new ArrayList<>();
        int max = 0;
        for (List<String> a : ar) {
            int val = 0;
            for (String name : a) {
                val += table.get(name);
            }
            if (val <= target) {
                if (val > max) {
                    max = val;
                    toBuy = a;
                } else if (val == max) {
                    if (a.size() > toBuy.size()) {
                        toBuy = a;
                        max = val;
                    }
                }
            }
        }
        System.out.println(toBuy);
    }
}
