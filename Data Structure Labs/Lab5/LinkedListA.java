package Lab5;

import java.util.Scanner;

public class LinkedListA {

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

        public void setNext(Node next) {
            this.next = next;
        }
    }

    static class LinkedList {

        private Node head;
        private int size;

        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public void add(int data) {
            Node node = new Node(data);
            node.setNext(head);
            setHead(node);
            size++;
        }

        public void printList() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.getData()+ " ");
                temp = temp.getNext();
            }
            System.out.println("\r");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList list = new LinkedList();
        list.add(1);
        Node cursor = list.getHead();
        int q = scanner.nextInt();
        for(int i=0; i<q; i++) {
            int t = scanner.nextInt();
            if(t==1) {
                int num = scanner.nextInt();
                Node toAdd = new Node(num);
                toAdd.setNext(cursor.getNext());
                cursor.setNext(toAdd);
            }
            if(t==2) {
                if(cursor.getNext() == null) {
                    cursor = list.getHead();
                }
                else {
                    cursor = cursor.getNext();
                }
            }
            if(t==3) {
                System.out.println(cursor.getData());
            }
        }
    }
}






