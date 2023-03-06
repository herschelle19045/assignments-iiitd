package Lab6;

import java.util.Scanner;

public class SimpleStack {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    static class Stack {
        Node top;

        void push(int data) {
            Node toAdd = new Node(data);
            toAdd.next = top;
            top = toAdd;
        }

        int peek() {
            return top.data;
        }

        int pop() {
            Node temp = top;
            top = top.next;
            return temp.data;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack();
        int q = scanner.nextInt();
        for(int i=0; i<q; i++) {
            int t = scanner.nextInt();
            if(t==1) {
                stack.push(scanner.nextInt());
            }
            if(t==2) {
                System.out.println(stack.pop());
            }
            if(t==3) {
                System.out.println(stack.peek());
            }
        }
    }
}
