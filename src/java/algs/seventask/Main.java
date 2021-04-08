package algs.seventask;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("Книга", 1, 600));
        items.add(new Item("Бинокль", 2, 5000));
        items.add(new Item("Аптечка", 4, 1500));
        items.add(new Item("Ноутбук", 2, 40000));
        items.add(new Item("Котелок", 1, 500));

        Decision bp = new Decision(8);

        bp.MakeAllSets(items);

        ArrayList<Item> solve = bp.GetBestSet();

        if(solve == null)
        {
            System.out.println("Нет решения!");
        }
        else
        {
            System.out.println(Arrays.toString(solve.toArray()));
            System.out.println("Решение приведено в таблице");
        }

        double[] weight = new double[]{18,15,10};
        double[] value = new double[]{25,24,15};
        double result = Decision.normalPackage(weight.length, weight, value, 20);
        System.out.println("Конечная выгода " + result);

    }
}
//    O(N!).
