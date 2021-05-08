package algs.fifth;

public class RBTmain {
    static RBTree<Integer> tree;
    private static final int a[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};

    public static void main(String[] args) {
        int i, ilen = a.length;
        tree = new RBTree<Integer>();
        for (i = 0; i < ilen; i++) {
            tree.insert(a[i]);
        }

        tree.print();
    }
}
