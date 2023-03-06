package Lab6;

import java.util.Scanner;

public class BanmayAndDeadline {

    static class Node {
        long data;
        Node next;

        public Node(long data) {
            this.data = data;
        }
    }

    static class Stack {
        Node top;
        int size;

        void push(long num) {
            Node node = new Node(num);
            node.next = top;
            top = node;
            size++;
        }

        void pop() {
            Node temp = top;
            top = top.next;
            temp.next = null;
            size--;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            boolean flag = true;
            if(stack.top != null) {
                while(flag) {
                    if(stack.top == null) {
                        break;
                    }
                    flag = false;
                    if(stack.top.data < t) {
                        stack.pop();
                        flag = true;
                    }
                }
            }
            stack.push(t);
            System.out.println(stack.size);
        }
    }
}
