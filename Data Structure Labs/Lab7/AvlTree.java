package Lab7;

import java.util.Scanner;

public class AvlTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            String string = scanner.next();
            if(string.equals("ADD")) {
                int x = scanner.nextInt();
                tree.insert(x);
            }
            else if(string.equals("COUNT")) {
                String s = scanner.next();
                if(s.equals("L")) {
                    System.out.println(tree.leftRotations);
                }
                else {
                    System.out.println(tree.rightRotations);
                }
            }
            else {
                System.out.println(tree.findParent(scanner.nextInt()));
            }
        }
    }

    static class Node {
        int data, height;
        Node left, right;

        public Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    static class AVLTree {
        Node root;

        int leftRotations=0;
        int rightRotations=0;

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
            if(node == null) {
                return new Node(value);
            }

            if(value < node.data) {
                node.left = insertRecur(node.left, value);
            }
            else if(value > node.data) {
                node.right = insertRecur(node.right, value);
            }
            else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            if(balance > 1 && value < node.left.data) {
                rightRotations++;
                return rightRotate(node);
            }

            if(balance < -1 && value > node.right.data) {
                leftRotations++;
                return leftRotate(node);
            }

            if(balance > 1 && value > node.left.data) {
                leftRotations++;
                node.left = leftRotate(node.left);
                rightRotations++;
                return rightRotate(node);
            }

            if(balance < -1 && value < node.right.data) {
                rightRotations++;
                node.right = rightRotate(node.right);
                leftRotations++;
                return leftRotate(node);
            }
            return node;
        }

        int findParent(int value) {
            return findParentRecur(root, value, 0);

        }

        int findParentRecur(Node node, int value, int par) {
            if (node == null)
                return -1;

            if (value < node.data)
                return findParentRecur(node.left, value, node.data);

            else if (value > node.data)
                return findParentRecur(node.right, value, node.data);

            else
                return par;
        }
    }
}

