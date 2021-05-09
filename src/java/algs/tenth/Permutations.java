package algs.tenth;

import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    public static void main(String[] args) {
        int[] mass = new int[]{1, 2, 1};
        System.out.println(new Permutations().permute(mass));
    }

    public Set<List<Integer>> permute (int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> curList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(curList,ans,visited,nums);
        return ans;
    }

    /**
     * @param curList Обрабатываемая строка
     * @param permutations Набор результатов перестановок и комбинаций
     * @param visited Отмечаем элементы, которые были пройдены текущей рекурсивной цепочкой
     * @param nums Массив чисел
     */
    private void backtrack (List<Integer> curList,
                            Set<List<Integer>> permutations,
                            boolean[] visited,
                            int[] nums) {
        if (curList.size() == nums.length) {
            permutations.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                curList.add(nums[i]);
                backtrack(curList,permutations,visited,nums);
                curList.remove(curList.size() - 1);
                visited[i] = false;
            }
        }
    }
}
