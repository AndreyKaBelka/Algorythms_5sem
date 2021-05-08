package algs.fourth;

public class AVLmain {
    public static void main(String[] args) {
        AVL<Integer, Integer> tree = new AVL<>();
        tree.add(10, 10);
        //tree.print();
        tree.add(11, 11);
        //tree.print();
        tree.add(15, 12);
       // tree.print();
        tree.add(13, 12);
        //tree.print();
        tree.add(16, 12);
       // tree.print();
        tree.add(12, 12);
       // tree.print();
        tree.add(3, 2);
        //tree.print();
        tree.add(1, 1);
        //tree.print();
       // tree.add(0, 1);
        //tree.print();
       // tree.add(2, 1);
        //tree.print();
       // tree.delete(13);
        //tree.print();
       // tree.delete(10);
        tree.print();
    }
}
