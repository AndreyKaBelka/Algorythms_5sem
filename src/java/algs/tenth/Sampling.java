package algs.tenth;

import java.util.ArrayList;
import java.util.List;

public class Sampling {

    public static void main(String[] args) {
        String[] mass = new String[]{"стол", "стул", "шкаф"};
        System.out.println(new Sampling().subsets(mass));
    }

    public List<List<String>> subsets (String[] nums) {
        List<List<String>> ans = new ArrayList<>();
        for (int size = 0; size <= nums.length; size++) {
            backtrack(0,size,new ArrayList<>(),ans,nums);
        }
        return ans;
    }

    private void backtrack (int start, int size, ArrayList<String> curList, List<List<String>> subsets, String[] nums) {
        if (curList.size() == size) {
            subsets.add(new ArrayList<>(curList));
        } else {
            for (int i = start; i < nums.length; i++) {
                curList.add(nums[i]);
                backtrack(i + 1, size, curList, subsets, nums);
                curList.remove(curList.size() - 1);
            }
        }
    }
}
