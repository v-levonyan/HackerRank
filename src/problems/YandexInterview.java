package problems;

import java.util.LinkedList;

public class YandexInterview {

    static class Node {
        String value;
        Node left;
        Node right;

        public String toString() {
            return this.value;
        }
    }

    static void print(Node node) {
        //some code
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node newNode = queue.remove();
            System.out.print(newNode + " ");

            if (newNode.left != null) queue.add(newNode.left);
            if (newNode.right != null) queue.add(newNode.right);
        }
    }

    public static void main(String[] args) {
        Node head = new Node();
        head.value = "A";
        head.left = new Node();
        head.left.value = "B";
        head.right = new Node();
        head.right.value = "C";
        Node left = head.left;
        Node right = head.right;
        left.left = new Node();
        left.left.value = "D";
        left.right = new Node();
        left.right.value = "E";

        right.right = new Node();
        right.right.value = "F";

        print(head);
    }
}
