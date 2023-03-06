package Lab6;

import java.util.Scanner;

public class ProcessManagement {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

    }

    static class Queue {
        Node head;
        Node tail;

        void push(int data) {
            Node node = new Node(data);
            if(head == null) {
                head = node;
            }
            else {
                tail.next = node;
            }
            tail = node;
        }

        void pop() {
//            Node temp = top;
            if(head != tail) {
                head = head.next;
            }
            else  {
                head = null;
                tail = null;
            }
//            System.out.println("Item Popped = " + temp.data);
        }

        void printQ() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println("\r");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int[] nums = new int[p];
        for(int i=0; i<q; i++) {
            int testcase = scanner.nextInt();
            int processor = scanner.nextInt();
            if (testcase == 1) {
                int time = scanner.nextInt();
                queue.push(time);
            }
            if(testcase == 2) {
                while(queue.head != null) {
                    if (queue.head.data <= i) {
                        queue.pop();
                        continue;
                    }
                    nums[processor]++;
//                    System.out.println("Processor " + processor + " = " + nums[processor]);
                    queue.pop();
                    break;
                }
            }
//            queue.printQ();
        }
        for(int value : nums) {
            System.out.println(value);
        }
    }
}
