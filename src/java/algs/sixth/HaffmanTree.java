package algs.sixth;

public class HaffmanTree {
    public static final int MAXVALUE = 1000;// Максимальный вес
    public int nodeNum; // Количество листовых узлов

    public HaffmanTree(int n) {
        this.nodeNum = n;
    }

    public void haffman(char[] names, int[] weight, HaffNode[] nodes) {//Строим дерево Хаффмана, вес, листовые узлы
        int n = this.nodeNum;
        int m1, m2, x1, x2;// m1, m2 представляют два наименьших веса, x1, x2 представляют числа, соответствующие двум наименьшим весам, m1 представляет наименьший вес, m2 представляет второй наименьший вес
        for (int i = 0; i < 2 * n - 1; i++) {// Инициализируем все узлы, соответствующие дереву Хаффмана с n листовыми узлами, имеется 2n-1 узлов
            HaffNode temp = new HaffNode();
            // Инициализируем n листовых узлов, которые являются входными узлами. 0, 1, 2, 3 - листовые узлы и входные узлы
            if (i < n) {
                temp.name = names[i];
                temp.weight = weight[i];
            } else {
                temp.name = ' ';
                temp.weight = 0;
            }
            temp.parent = 0;
            temp.flag = 0;
            temp.leftChild = -1;
            temp.rightChild = -1;
            nodes[i] = temp;
        }

        for (int i = 0; i < n - 1; i++) {//Инициализируем n-1 нелистовых узлов, n-1 представляет количество n-1, которые нужно зациклить n-1 раз
            m1 = m2 = MAXVALUE;
            x1 = x2 = 0;
            // При вычислении числа n-1 каждый раз оно составляет от 0 до n + i-1, а flag = 0, flag = 1 означает, что он был добавлен в двоичное дерево
            //Далее - ищем вторую с наименьшим весом.
            for (int j = 0; j < n + i; j++) {
                if (nodes[j].weight < m1 && nodes[j].flag == 0) {
                    //Начальное значение m1, x1 - первый элемент. Если позже он будет меньше m1, m1 указывает на меньший. На исходную точку, на которую указывает m1, теперь указывает m2.，
                    //Если последний больше m1 и меньше m2, то m2 указывает на тот, который больше m1 и меньше m2,
                    //То есть m1 указывает на наименьшее, а m2 указывает на второе наименьшее.
                    m2 = m1;
                    x2 = x1;
                    m1 = nodes[j].weight;
                    x1 = j;
                } else if (nodes[j].weight < m2 && nodes[j].flag == 0) {
                    m2 = nodes[j].weight;
                    x2 = j;
                }
            }
            // Объединяем второй с наименьшим весом в двоичное дерево
            nodes[x1].parent = n + i;
            nodes[x2].parent = n + i;
            nodes[x1].flag = 1;
            nodes[x2].flag = 1;
            nodes[n + i].weight = nodes[x1].weight + nodes[x2].weight;
            nodes[n + i].leftChild = x1;
            nodes[n + i].rightChild = x2;
        }
    }


    public void haffmanCode(HaffNode[] nodes, Code[] haffCode) {//Кодирование Хавермана
        int n = this.nodeNum;
        Code code = new Code(n);
        int child, parent;

        for (int i = 0; i < n; i++) {// Кодируем первые n входных узлов
            code.start = n - 1;
            code.weight = nodes[i].weight;
            code.name = nodes[i].name;
            child = i;
            parent = nodes[child].parent;
            while (parent != 0) {// Поднимаемся от листового узла, для генерации кодов
                if (nodes[parent].leftChild == child) {
                    code.bit[code.start] = 0;
                } else {
                    code.bit[code.start] = 1;
                }

                code.start--;
                child = parent;
                parent = nodes[child].parent;
            }

            Code temp = new Code(n);
            for (int j = code.start + 1; j < n; j++) {
                temp.bit[j] = code.bit[j];
            }
            temp.weight = code.weight;
            temp.name = code.name;
            temp.start = code.start;
            haffCode[i] = temp;
        }
    }

    public void decode(String res, HaffNode[] nodes) {
        int index = 2 * this.nodeNum - 2;//Начинаем с корневого узла
        for (int k = 0; k < res.length(); k++) {// Проходим по коду Хаффмана по очереди и проходим по левому дочернему элементу текущего узла, до того, когда он встретит 0
            if (res.charAt(k) == '1') {//В случае единицы пройдем правый дочерний элемент текущего узла
                index = nodes[index].rightChild;
                // Если правый дочерний элемент текущего узла равен -1, это доказывает, что это листовой узел для прямого вывода символов (поскольку дерево Хаффмана имеет только узлы с исходной степенью 0 или 2, поэтому можно судить только о правильном дочернем узле)
                if (nodes[index].rightChild == -1) {
                    System.out.print(nodes[index].name);
                    index = 2 * this.nodeNum - 2;// Начинаем снова с корневого узла
                }
            } else {// В случае 1 пройти правый дочерний элемент текущего узла
                index = nodes[index].leftChild;
                // Если правый дочерний элемент текущего узла равен -1, это доказывает, что это листовой узел для прямого вывода символов (поскольку дерево Хаффмана имеет только узлы с исходной степенью 0 или 2, поэтому можно судить только о правильном дочернем узле)
                if (nodes[index].rightChild == -1) {
                    System.out.print(nodes[index].name);
                    index = 2 * this.nodeNum - 2;//Начинаем снова с корневого узла
                }
            }
        }
    }
}
