package algs.sixth;

public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> mRoot;    // корень

    private static final boolean RED   = false;
    private static final boolean BLACK = true;


    public RBTree() {
        mRoot=null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node!=null ? node.parent : null;
    }
    private boolean colorOf(RBTNode<T> node) {
        return node!=null ? node.color : BLACK;
    }
    private boolean isRed(RBTNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }
    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }
    private void setBlack(RBTNode<T> node) {
        if (node!=null)
            node.color = BLACK;
    }
    private void setRed(RBTNode<T> node) {
        if (node!=null)
            node.color = RED;
    }
    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node!=null)
            node.parent = parent;
    }
    private void setColor(RBTNode<T> node, boolean color) {
        if (node!=null)
            node.color = color;
    }

    private RBTNode<T> search(RBTNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public RBTNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \                            / \
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     */
    private void leftRotate(RBTNode<T> x) {
        RBTNode<T> y = x.right;


        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null) {
            this.mRoot = y;
        } else {
            if (x.parent.left == x)
                x.parent.left = y;
            else
                x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    /*
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \                             /  \
     *        x   ry                           lx   y
     *       / \                                   / \
     *      lx  rx                                rx  ry
     */
    private void rightRotate(RBTNode<T> y) {
        RBTNode<T> x = y.left;

        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null) {
            this.mRoot = x;
        } else {
            if (y == y.parent.right)
                y.parent.right = x;
            else
                y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    /*
     * ребаланс дерева при вставке элемента
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        // пока родительский узел существует и он красный
        while (((parent = parentOf(node))!=null) && isRed(parent)) {
            gparent = parentOf(parent);

            //если родительский узел - левый
            if (parent == gparent.left) {
                // сосед родительского узла (дядя) - красный
                RBTNode<T> uncle = gparent.right;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // дядя черный, а текущий узел - правый
                if (parent.right == node) {
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // дядя черный, а текущий узел - левый
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {    //если родительский узел z является правым дочерним элементом родительского узла z
                // дядя - красный
                RBTNode<T> uncle = gparent.left;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // дядя черный, а текущий узел - левый
                if (parent.left == node) {
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // дядя черный, а текущий узел - правый
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        setBlack(this.mRoot);
    }

    private void insert(RBTNode<T> node) {
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = this.mRoot;

        // рассматрение красно-черного дерево как двоичного дерева поиска и добавление узлов к двоичному дереву поиска
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        node.parent = y;
        if (y!=null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            this.mRoot = node;
        }

        node.color = RED;

        insertFixUp(node);
    }


    public void insert(T key) {
        RBTNode<T> node=new RBTNode<T>(key,BLACK,null,null,null);

        if (node != null)
            insert(node);
    }


    /*
     * ребаланс дерева при удалении элемента
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        while ((node==null || isBlack(node)) && (node != this.mRoot)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // сосед x и w красный
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // сосед x w черный, и двое детей w тоже черные
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right==null || isBlack(other.right)) {
                        // сосед w элемента x черный, левый дочерний элемент w - красный, а правый ребенок - черный
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // сосед w элемента x черный, правый дочерний элемент w красный, а левый дочерний элемент любого цвета
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // сосед x и w красный
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // сосед x w черный, и двое детей w тоже черные
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // сосед w элемента x черный, левый дочерний элемент w - красный, а правый ребенок - черный
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // сосед w элемента x черный, правый дочерний элемент w красный, а левый дочерний элемент любого цвета
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node!=null)
            setBlack(node);
    }

    private void remove(RBTNode<T> node) {
        RBTNode<T> child, parent;
        boolean color;

        // если есть левый и правый узел
        if ( (node.left!=null) && (node.right!=null) ) {
            RBTNode<T> replace = node;

            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // узел узла не корень
            if (parentOf(node)!=null) {
                if (parentOf(node).left == node)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else {
                // узел узла - корень
                this.mRoot = replace;
            }

            // дочерний элемент является правым дочерним элементом replace и узла, который необходимо настроить
            // в replace не должно быть левого потомка т.к. узел-преемник
            child = replace.right;
            parent = parentOf(replace);
            // цвет сохраняется
            color = colorOf(replace);

            // удаленный узел - это родительский узел его узла-преемника
            if (parent == node) {
                parent = replace;
            } else {
                if (child!=null)
                    setParent(child, parent);
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return ;
        }

        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // сохранение цвета
        color = node.color;

        if (child!=null)
            child.parent = parent;

        // узел узла - не корень
        if (parent!=null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.mRoot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    public void remove(T key) {
        RBTNode<T> node;

        if ((node = search(mRoot, key)) != null)
            remove(node);
    }

    /*
     * key - ключ
     *
     * direction:
     * 0 - корень;
     * -1 - узел это левая ветка;
     * 1 - узел это правая ветка
     */
    private void print(RBTNode<T> tree, T key, int direction, int level) {

        if(tree != null) {
            print(tree.right,tree.key,  1, level + 1);
            for (int i=0;i<level;i++) {
                System.out.print("\t");
            }
            if(direction==0)
                System.out.printf("%2d(B)\n", tree.key);
            else
                System.out.printf("%2d(%s)\n", tree.key, isRed(tree)?"R":"B");
            print(tree.left, tree.key, -1, level + 1);

        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0, 0);
    }
}
