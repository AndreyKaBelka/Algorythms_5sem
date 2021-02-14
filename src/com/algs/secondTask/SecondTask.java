package com.algs.secondTask;

import java.util.*;

public class SecondTask {
    public static void main(String[] args) {
        BinaryTree binaryTree = BinaryTree.createTree(getDataFromTerminal());
        List table = binaryTree.tableFromTree(new SortingParams().sortByValue().ascSorting());
        table.forEach(System.out::println);
    }

    private static ArrayList<Integer> getDataFromTerminal() {
        ArrayList<Integer> data = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int num = scanner.nextInt();
            if (num == 0) {
                break;
            }
            data.add(num);
        }
        return data;
    }
}
