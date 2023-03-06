package Lab6;

import java.util.Scanner;

public class DarenDoesMath {

    static class Node {
        long data;
        Node next;

        public Node(long data) {
            this.data = data;
        }
    }

    static class Stack {
        Node top;

        void push(long num) {
            Node node = new Node(num);
            node.next = top;
            top = node;
        }

        long pop() {
            Node temp = top;
            top = top.next;
            return temp.data;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(" ");
        Stack stack = new Stack();
        for (int i = 0; i < strings.length; i++) {
            if(!strings[i].equals("*") && !strings[i].equals("+") && !strings[i].equals("-")) {
                stack.push(Integer.parseInt(strings[i]));
            }
            else {
                long val1 = stack.pop();
                long val2 = stack.pop();

                if (strings[i].equals("*")) {
                    stack.push(val1*val2);
                }
                else if (strings[i].equals("+")) {
                    stack.push(val1+val2);
                }
                else {
                    stack.push(val2-val1);
                }
            }
        }
        System.out.println(stack.pop());
    }
}
