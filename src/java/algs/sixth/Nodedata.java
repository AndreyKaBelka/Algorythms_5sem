package algs.sixth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class HaffNode {// Класс узла дерева Хаффмана
    public char name; // Имя
    public int weight; // Вес
    public int parent; // Родитель
    public int flag; // Признак листового узла
    public int leftChild; // Левое дитя
    public int rightChild; // Правое дитя

    public HaffNode() {

    }
}

class Code {// Класс кодирования Хаффмана
    public int[] bit; // Кодированный массив
    public int start; // Начальный индекс кода
    public int weight; // Вес
    public char name; // Имя

    public Code(int n) {
        bit = new int[n];
        start = n - 1;
    }
}

class Compress_file {
    public char[] names;
    public int[] weights;

    public static void main(String[] args) throws Exception {
        Compress_file test = new Compress_file();

        String s = test.readfile();// Читаем строку в тексте
        Map<Character, Integer> map = test.getCharMaps(s);// Подсчитаем частоту появления разных символов в тексте
        test.names = new char[map.size()];        // Создаем массив для хранения символов и их частоты
        test.weights = new int[map.size()];
        int i = 0;
        Set set = map.keySet();// Преобразуем map в set и помещаем символы в массив
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            char key = (Character) iter.next();
            test.names[i] = key;
            test.weights[i] = map.get(key);
            i++;
        }
        System.out.println("Частота появления символов в тексте：");
        for (int j = 0; j < test.names.length; j++) {// Буквы в тексте
            System.out.print(test.names[j] + " ");
        }
        System.out.println();
        for (int j = 0; j < test.weights.length; j++) {// Количество повторений букв
            System.out.print(test.weights[j] + " ");
        }
        System.out.println();

        HaffmanTree haffTree = new HaffmanTree(map.size());// Инициализируем дерево Хаффмана
        HaffNode[] nodes = new HaffNode[2 * map.size() - 1];
        Code[] codes = new Code[map.size()];
        haffTree.haffman(test.names, test.weights, nodes);// Строим дерево Хаффмана
        haffTree.haffmanCode(nodes, codes);// Генерируем коды Хаффмана
        System.out.println("Кодирование：");// Печатает код Хаффмана
        for (int k = 0; k < map.size(); k++) {
            System.out.print("Имя=" + codes[k].name + " Вес=" + codes[k].weight + " Код=");
            for (int j = codes[k].start + 1; j < map.size(); j++) {
                System.out.print(codes[k].bit[j]);
            }
            System.out.println();
        }
        System.out.print("Исходный текст：");
        System.out.println(s);
        System.out.print("Кодированная последовательность Хаффмана：");
        String res = s;
        String bit = "";
        // Заменяет соответствующие символы в соответствии с таблицей кодирования Хаффмана.
        for (int k = 0; k < test.names.length; k++) {
            for (int j = codes[k].start + 1; j < map.size(); j++) {
                bit += codes[k].bit[j];
            }
            res = res.replace(String.valueOf(test.names[k]), bit);
            bit = "";
        }
        System.out.println(res);
        System.out.print("Декодированный текст：");
        haffTree.decode(res, nodes);
        System.out.println();

    }

    public String readfile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("src/java/algs/sixth/data"));
        return (br.readLine());
    }

    public Map<Character, Integer> getCharMaps(String s) {// Подсчитываем частоту появления разных символов в тексте
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        return map;
    }
}
