package Lab6;

import java.util.Scanner;

public class QueueQueries {

    static class Node {
        long data;
        Node next;
        Node prev;

        public Node(long data) {
            this.data = data;
        }
    }

    static class Queue {
        Node head;
        Node tail;
        int size;

        void add(long num) {
            Node node = new Node(num);
            if(head == null) {
                head = node;
            }
            else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            size++;
        }

        void remove() {
            if(size == 0) {
                return;
            }
            if(size == 1) {
                head = null;
                tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            size--;
        }

        void inc(long x, long d) {
            Node temp = tail;
            for(int i=0; i<x; i++) {
                temp.data += d;
                temp = temp.prev;
            }
        }

        boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        int n = scanner.nextInt();
        for(int i=0; i<n; i++) {
            String string = scanner.next();
            if(string.equals("enqueue")) {
                queue.add(scanner.nextLong());
            }
            else if(string.equals("dequeue")) {
                queue.remove();
            }
            else {
                queue.inc(scanner.nextLong(), scanner.nextLong());
            }
            if(queue.isEmpty()) {
                System.out.println("EMPTY");
            }
            else {
                System.out.println(queue.head.data);
            }
        }
    }
}
