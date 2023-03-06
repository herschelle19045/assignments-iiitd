package Lab7;

import java.util.Scanner;

public class AvyaktAndDarenCookie {

	static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }


    static class BinarySearchTree {
        Node root;

        void insert(int value) {
            root = insertRecur(root, value);
        }

        private Node insertRecur(Node root, int value) {
            if (root == null) {
                root = new Node(value);
            } else {
                if (value <= root.data) {
                    root.left = insertRecur(root.left, value);
                } else {
                    root.right = insertRecur(root.right, value);
                }
            }
            return root;
        }

        void inOrder() {
            inOrderRecur(root);
        }

        private void inOrderRecur(Node root) {
            if (root != null) {
                inOrderRecur(root.left);
                System.out.println(sumOfValuesBelow(root));
                inOrderRecur(root.right);
            }
        }


        private int sumOfValuesBelow(Node root) {
            if (root.left == null && root.right == null) {
                return 0;
            }
            if (root.left == null) {
                return root.right.data + sumOfValuesBelow(root.right);
            }
            if (root.right == null) {
                return root.left.data + sumOfValuesBelow(root.left);
            }
            return root.left.data + root.right.data + sumOfValuesBelow(root.left) + sumOfValuesBelow(root.right);
        }
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tree.insert(scanner.nextInt());
        }
        tree.inOrder();
    }
}
