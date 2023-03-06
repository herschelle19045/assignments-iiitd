package Lab5;

import java.util.Scanner;

public class LinkedListB {

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            this.next = node;
        }
    }

    static class LinkedList {

        private Node head;
        private Node tail;
        private int size;

        public Node getHead() {
            return head;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void add(int data) {
            Node node = new Node(data);
            if(isEmpty()) {
                head = node;
                tail = node;
            }
            else {
                node.setNext(head);
                head = node;
            }
            size++;
        }

        public void addEnd(int data) {
            Node node = new Node(data);
            if(isEmpty()) {
                head = node;
            }
            else {
                tail.setNext(node);
            }
            tail = node;
            size++;

        }

        public void remove() {
            head = head.getNext();
            size--;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        LinkedList list = new LinkedList();
        list.add(1);
        Node cursor = list.getHead();
        for(int i=0; i<q; i++) {
            int t = scanner.nextInt();
            if(t==1) {
                int num = scanner.nextInt();
                list.addEnd(num);
                if(list.getSize() % 2 != 0) {
                    cursor = cursor.getNext();
                }
            }
            if(t==2) {
                if(list.getSize() == 2) {
                    cursor = cursor.getNext();
                }
                list.remove();
                if(list.getSize() % 2 != 0 && list.getSize() != 1) {
                    cursor = cursor.getNext();
                }
            }
            if(t==3) {
                if(list.getSize() % 2 == 0) {
                    System.out.print(cursor.getData() + " " + cursor.getNext().getData());
                    System.out.println("\r");
                }
                else {
                    System.out.println(cursor.getData());
                }
            }
        }
    }
}





