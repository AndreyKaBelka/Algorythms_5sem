package algs.twelfth;

import algs.MD5;

import java.lang.reflect.Array;
import java.util.*;

public class Table<K, T> extends AbstractMap<K, T> implements RandomAccess, Map<K, T> {
    static class Node<K, T> implements Entry<K, T> {
        private final K key;
        private T val;
        private Node<K, T> next;

        private Node(K key, T val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public T getValue() {
            return val;
        }

        @Override
        public T setValue(T value) {
            return this.val = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private int size;
    private final int capacity = 1000;
    private Node[] table = new Node[capacity];


    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(Object key) {
        Optional<Node<K, T>> optionalNode = Optional.ofNullable(getNode((K) key));
        return optionalNode.orElse(new Node<>(null, null)).val;
    }

    private Node<K, T> getNode(K key) {
        if (size == 0) {
            return null;
        }
        Node<K, T> head = table[getIndex(key)];
        while (head != null) {
            if (head.getKey().equals(key))
                return head;
            head = head.next;
        }
        return null;
    }

    @Override
    public T put(K key, T value) {
        Node<K, T> node = getNode(key);
        Node<K, T> newNode;
        if (node != null) {
            newNode = new Node<>(key, value);
            while (node != null) {
                if (node.getKey().equals(key)) {
                    newNode.next = node.next;
                    break;
                }
                node = node.next;
            }
        } else {
            resize();
            newNode = new Node<>(key, value);
        }
        table[getIndex(key)] = newNode;
        return null;
    }

    @Override
    public T remove(Object key) {
        Node<K, T> head = table[getIndex((K) key)];
        Node<K, T> top = head;
        Node<K, T> parent = null;
        while (head != null) {
            if (head.getKey().equals(key)) {
                if (parent != null) {
                    parent.next = head.next;
                } else {
                    top = head.next;
                }
                break;
            }
            parent = head;
            head = head.next;
        }
        table[getIndex((K) key)] = top;
        return head == null ? null : head.val;
    }

    @Override
    public Set<Entry<K, T>> entrySet() {
        Set<Entry<K, T>> res = new HashSet<>();
        for (Node<K, T> node : table) {
            if (node != null) {
                res.add(node);
            }
        }
        return res;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    private void resize() {
        Node<K, T>[] oldTable = table;
        Node<K, T>[] newArray = (Node<K, T>[]) Array.newInstance(Node.class, Math.max(++size, capacity));
        if (oldTable != null)
            System.arraycopy(oldTable, 0, newArray, 0, Math.max(size - 1, capacity));
        table = newArray;
    }

    public static void main(String[] args) {
        Map<String, Object> ta = new Table<>();
        ta.put("1", 223);
        ta.put("1", 2);
        ta.put("12", 23123);
        System.out.println(ta.get("1"));
        System.out.println(ta.get("12"));
        System.out.println(ta.get("2"));
        System.out.println(ta.entrySet());
        System.out.println(ta.remove("1"));
        System.out.println(ta.remove("1"));
    }
}
