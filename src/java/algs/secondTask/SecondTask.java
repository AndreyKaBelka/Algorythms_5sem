package algs.secondTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SecondTask {
    public static void main(String[] args) {
        //first part
//        BinaryTree binaryTree = BinaryTree.createTree(getDataFromTerminal());
//        List table = binaryTree.tableFromTree(new SortingParams().sortByValue().ascSorting());
//        table.forEach(System.out::println);

        //second part
        ArrayList<Client> data = getDataFromFile("telephones.txt");
        System.out.println(data);
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

    private static ArrayList<Client> getDataFromFile(String resourceName){
        ArrayList<Client> data = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream(resourceName);
        new BufferedReader(new InputStreamReader(stream)).lines().forEach(line -> {
            String[] clientData = line.split(" "); //0 - surname, 1 - name, 2 - lastname, 3 - phone, 4 - plan
            data.add(Client.buildClient(clientData[3], clientData[2], clientData[1], clientData[0], Client.Plan.valueOf(clientData[4])));
        });

        return data;
    }
}
