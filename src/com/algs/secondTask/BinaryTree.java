package com.algs.secondTask;

import com.algs.utils.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryTree {
    private TreeNode root;

    private BinaryTree(TreeNode root) {
        this.root = root;
    }

    private static class TreeNode {

        TreeNode right = null;
        TreeNode left = null;
        Pair pair = null;

        TreeNode(int key, Object value) {
            pair = new Pair(key, value);
        }

        private static int compare(TreeNode n1, TreeNode n2) {
            return Integer.compare(n1.pair.getKey(), n2.pair.getKey());
        }

        private boolean greater(TreeNode node) {
            return compare(this, node) > 0;
        }

        public TreeNode getDescByNodeKey(int nodeKey) {
            if (this.pair.getKey() > nodeKey) return this.left;
            else return this.right;
        }

        void addPetal(TreeNode treeNode) {
            if (this.greater(treeNode)) {
                if (this.left == null) {
                    this.left = treeNode;
                } else {
                    this.left.addPetal(treeNode);
                }
            } else {
                if (this.right == null) {
                    this.right = treeNode;
                } else {
                    this.right.addPetal(treeNode);
                }
            }
        }

        public TreeNode findNode(int nodeKey) {
            if (this.pair.getKey() == nodeKey) return this;
            if (this.left != null && this.pair.getKey() > nodeKey) return this.left.findNode(nodeKey);
            else if (this.right != null && this.pair.getKey() < nodeKey) return this.right.findNode(nodeKey);
            throw new IllegalArgumentException("Put correct nodeKey");
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "key=" + pair.getKey() +
                    ", name='" + pair.getValue() + '\'' +
                    '}';
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void addNode(int nodeKey, Object nodeName) {
        TreeNode treeNode = new TreeNode(nodeKey, nodeName);
        root.addPetal(treeNode);
    }

    public static BinaryTree createTree(int rootKey, Object rootName) {
        return new BinaryTree(new TreeNode(rootKey, rootName));
    }

    public void deleteNode(int nodeKey) {
        TreeNode parentNode = null;
        TreeNode currentNode = root;
        while (currentNode.pair.getKey() != nodeKey) {
            parentNode = currentNode;
            currentNode = currentNode.getDescByNodeKey(nodeKey);
        }
        assert parentNode != null;
        if (currentNode.left == null && currentNode.right == null) {
            if (parentNode.greater(currentNode)) parentNode.left = null;
            else parentNode.right = null;
        } else if (currentNode.right == null || currentNode.left == null) {
            if (currentNode.right == null) {
                if (parentNode.greater(currentNode)) parentNode.left = currentNode.left;
                else parentNode.right = currentNode.left;
            }
            if (currentNode.left == null) {
                if (parentNode.greater(currentNode)) parentNode.left = currentNode.right;
                else parentNode.right = currentNode.right;
            }
        } else {
            TreeNode leftMostNode = currentNode.right;
            TreeNode parentOfLeftMostNode = null;
            while (leftMostNode.left != null) {
                parentOfLeftMostNode = leftMostNode;
                leftMostNode = leftMostNode.left;
            }

            if (parentOfLeftMostNode != null) {
                parentOfLeftMostNode.left = leftMostNode.right;
            }

            leftMostNode.right = currentNode.right;
            leftMostNode.left = currentNode.left;

            if (parentNode.greater(currentNode)) parentNode.left = leftMostNode;
            else parentNode.right = leftMostNode;

        }
    }

    public void deleteNode(TreeNode node) {
        deleteNode(node.pair.getKey());
    }

    public void replaceNode(int key, Object value) {
        TreeNode node = this.findNode(key);
        node.pair.setValue(value);
    }


    private void incrementValue(int num) {
        TreeNode node = this.findNode(num);
        replaceNode(num, ((int) node.pair.getValue()) + 1);
    }

    public TreeNode findNode(int nodeKey) {
        return root.findNode(nodeKey);
    }

    public static BinaryTree createTree(ArrayList<Integer> data) {
        int i = 0;
        BinaryTree binaryTree = null;
        for (Integer datum : data) {
            int num = datum;
            if (num == 0) {
                break;
            }
            if (i == 0) {
                binaryTree = createTree(num, 1);
                i++;
            } else {
                try {
                    binaryTree.incrementValue(num);
                } catch (IllegalArgumentException e) {
                    binaryTree.addNode(num, 1);
                }
            }
        }
        return binaryTree;
    }

    private List<Pair> buildTable() {
        List<Pair> table = new ArrayList<>();
        traverse_tree(getRoot(), table);
        return table;
    }

    private void traverse_tree(TreeNode node, List<Pair> table) {
        if (node != null) {
            traverse_tree(node.left, table);
            table.add(new Pair(node.pair.getKey(), node.pair.getValue()));
            traverse_tree(node.right, table);
        }
    }

    public static List<Pair> tableFromTree(BinaryTree tree, SortingParams params) {
        List<Pair> table = tree.buildTable();
        table = sortTableByParams(table, params);
        return table;
    }

    private static List<Pair> sortTableByParams(List<Pair> table, SortingParams params) {
        Comparator<Integer> orderClause = params.getComparator();

        if (params.isByKey()) {
            table = table.stream().sorted((obj1, obj2) -> orderClause.compare(obj1.getKey(), obj2.getKey())).collect(Collectors.toList());
        } else {
            table = table.stream().sorted((obj1, obj2) -> orderClause.compare((Integer) obj1.getValue(), ((Integer) obj2.getValue()))).collect(Collectors.toList());
        }
        return table;
    }

    public List<Pair> tableFromTree(SortingParams params) {
        return BinaryTree.tableFromTree(this, params);
    }
}