package EndSem;

import java.util.EmptyStackException;
import java.util.Scanner;

public class Tangeled {

    static class Node {
        char data;
        Node next;

        public Node(char data) {
            this.data = data;
        }
    }

    static class Stack {
        Node top;

        void push(char num) {
            Node toAdd = new Node(num);
            if(!(top == null)) {
                toAdd.next = top;
            }
            top = toAdd;
        }

        void pop() {
            if(isEmpty()) {
                throw new EmptyStackException();
            }
            top = top.next;
        }

        boolean isEmpty() {
            return top == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if(stack.isEmpty()) {
                stack.push(s.charAt(i));
            }
            else if(stack.top.data == s.charAt(i)) {
                stack.pop();
            }
            else {
                stack.push(s.charAt(i));
            }
        }
        if(stack.isEmpty()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
