package Lab6;

import java.util.Scanner;

public class FindLargestInX {

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
        int size;

        void add(int data) {
            Node toAdd = new Node(data);
            if(head == null) {
                head = toAdd;
            }
            else {
                tail.next = toAdd;
            }
            tail = toAdd;
            size++;
        }

        int remove() {
            Node temp = head;
            head = head.next;
            temp.next = null;
            size--;
            return temp.data;
        }

        void printQueue() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println("\r");
        }

    }
    static class LinkedList {
        Node head;
        Node tail;

        void addEnd(int num) {
            Node toAdd = new Node(num);
            if(head == null) {
                head = toAdd;
            }
            else {
                tail.next = toAdd;
            }
            tail = toAdd;
        }

        void printList() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println("\r");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        for(int i=0; i<n; i++) {
            queue.add(scanner.nextInt());
        }
        for(int j=0; j<x; j++) {
            LinkedList list = new LinkedList();
            if(queue.size < x) {
                while(queue.head != null) {
                    list.addEnd(queue.remove());
                }
            }
            else {
                for(int i=0; i<x; i++) {
                    list.addEnd(queue.remove());
                }
            }
            Node temp = list.head;
            Node maxNode = temp;
            int maxValue = temp.data;
            while(temp != null) {
                if(temp.data > maxValue) {
                    maxValue = temp.data;
                    maxNode = temp;
                }
                temp = temp.next;
            }
            temp = list.head;
            while(temp != null) {
                if(temp != maxNode) {
                    if(temp.data !=0) {
                        temp.data--;
                    }
                }
                temp = temp.next;
            }
            temp = list.head;
            while(temp != null) {
                if(temp != maxNode) {
                    queue.add(temp.data);
                }
                temp = temp.next;
            }
            System.out.print(maxValue + " ");
        }
    }
}
