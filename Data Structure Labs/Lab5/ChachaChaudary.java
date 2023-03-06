package Lab5;

import java.util.Scanner;

public class ChachaChaudary {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    static class LinkedList {
        Node head;
        Node tail;
        int size;

        void add(int num) {
            Node toAdd = new Node(num);
            if(head == null) {
                tail = toAdd;
            }
            else {
                toAdd.next = head;
                head.prev = toAdd;   // missed this IMP
            }
            head = toAdd;
            size++;
        }

        void addEnd(int num) {
            Node toAdd = new Node(num);
            if(head == null) {
                head = toAdd;
            }
            else {
                tail.next = toAdd;
                toAdd.prev = tail;
            }
            tail = toAdd;
            size++;
        }

        Node removeEnd() {
            Node temp = tail;
            if(size == 1) {
                head = null;
                tail = null;
            }
            else {
                temp.prev.next = null;
                tail = tail.prev;
                temp.prev = null;     // missed IMP
            }
            size--;
            return temp;

        }

        void printList() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList list = new LinkedList();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        for(int i=0; i<n; i++) {
            list.addEnd(scanner.nextInt());
        }
        for(int i=0; i<k; i++) {
            Node temp = list.removeEnd();
            list.add(temp.data);
        }
        list.printList();
    }
}
