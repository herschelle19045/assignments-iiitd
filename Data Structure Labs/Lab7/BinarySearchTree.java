package Lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BinarySearchTree {

    static class Node {

        int data, lCount, height;
        Node left, right;


        public Node(int data) {
            this.data = data;
        }
    }

    static class Tree {
        Node root;

        int height(Node node) {
            if (node == null) {
                return 0;
            }
            return node.height;
        }

        Node rightRotate(Node unbalancedNode) {
            Node newNode = unbalancedNode.left;
            Node temp = newNode.right;

            newNode.right = unbalancedNode;
            unbalancedNode.left = temp;

            unbalancedNode.height = 1 + Math.max(height(unbalancedNode.left), height(unbalancedNode.right));
            newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));

            return newNode;
        }

        Node leftRotate(Node unbalancedNode) {
            Node newNode = unbalancedNode.right;
            Node temp = newNode.left;

            newNode.left = unbalancedNode;
            unbalancedNode.right = temp;

            unbalancedNode.height = 1 + Math.max(height(unbalancedNode.left), height(unbalancedNode.right));
            newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));

            return newNode;
        }

        int getBalance(Node node) {
            if (node == null) {
                return 0;
            }
            return height(node.left) - height(node.right);
        }

        void insert(int value) {
            root = insertRecur(root, value);
        }

        private Node insertRecur(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }

            if (value < node.data) {
                node.left = insertRecur(node.left, value);
                node.lCount++;
            } else if (value > node.data) {
                node.right = insertRecur(node.right, value);
            } else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            if (balance > 1 && value < node.left.data) {
                return rightRotate(node);
            }

            if (balance < -1 && value > node.right.data) {
                return leftRotate(node);
            }

            if (balance > 1 && value > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            if (balance < -1 && value < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void delete(int value) {
            root = deleteRecur(root, value);
        }

        private Node deleteRecur(Node node, int value) {
            if (root == null) {
                return root;
            }

            if (value < root.data) {
                root.left = deleteRecur(root.left, value);
                node.lCount--;
            } else if (value > root.data) {
                root.right = deleteRecur(root.right, value);
            } else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp = null;
                    if (temp == root.left) {
                        temp = root.right;
                    } else {
                        temp = root.left;
                    }

                    root = temp;
                } else {
                    Node temp = minValueNode(root.right);
                    root.data = temp.data;
                    root.right = deleteRecur(root.right, temp.data);
                }
            }

            if (root == null) {
                return root;
            }

            root.height = Math.max(height(root.left), height(root.right)) + 1;

            int balance = getBalance(root);

            if (balance > 1 && getBalance(root.left) >= 0) {
                return rightRotate(root);
            }

            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

            if (balance < -1 && getBalance(root.right) <= 0) {
                return leftRotate(root);
            }

            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null)
                current = current.left;

            return current;
        }
    }


    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.delete(1);
        System.out.println("Helo");
        System.out.println(tree.root);
//        Reader.init(System.in);
//        Tree tree = new Tree();
//        int n = Reader.nextInt();
//        for (int i = 0; i < n; i++) {
//            String string = Reader.next();
//            if(string.equals("INSERT")) {
//                int x = Reader.nextInt();
//                tree.insert(x);
//            }
//            else if(string.equals("DELETE")) {
//                int x = Reader.nextInt();
//                tree.delete(x);
//            }
//            else if(string.equals("COUNT")) {
//                int x = Reader.nextInt();
//                tree.count(x);
//            }
//            else {
//                int k = Reader.nextInt();
//
//            }
//        }
    }

    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /**
         * call this method to initialize reader for InputStream
         */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input));
            tokenizer = new StringTokenizer("");
        }

        /**
         * get next word
         */
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                        reader.readLine());
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        static long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
