package MidSem;

import java.util.Scanner;

public class DroppingCourses {

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

        void addEnd(int data) {
            Node toAdd = new Node(data);
            if(isEmpty()) {
                head = toAdd;
            }
            else {
                tail.next = toAdd;
                toAdd.prev = tail;
            }
            tail = toAdd;
            size++;
        }

        void remove(int index) {
            Node temp = head;
            for(int i=0; i<index; i++) {
                temp = temp.next;
            }
            if(size == 1) {
                head = null;
                tail = null;
            }
            else if(temp == head) {
                head = temp.next;
            }
            else if(temp == tail) {
                tail = temp.prev;
                tail.next = null;
            }
            else {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;  //
            }
        }

        void printList() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }

        boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList list = new LinkedList();
        int n = scanner.nextInt();
        for(int i=0; i<n; i++) {
            list.addEnd(scanner.nextInt());
        }
        int q = scanner.nextInt();
        for(int i=0; i<q; i++) {
            list.remove(scanner.nextInt());
//            list.printList();
        }
        list.printList();
    }
}
