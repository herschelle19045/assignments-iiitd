package MidSem;

import java.util.Scanner;

public class AynanaAndDecorate {

    static class Node {
        int data;
        Node next;

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
                tail = toAdd;
            }
            else {
                tail.next = toAdd;
                tail = toAdd;
            }
            size++;
        }

//        void printList() {
//            Node temp = head;
//            while(temp != null) {
//                System.out.println(temp.getData());
//                temp = temp.getNext();
//            }
//        }

        boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        LinkedList list = new LinkedList();
        for(int i=0; i<n; i++) {
            list.addEnd(scanner.nextInt());
        }
//        list.printList();
//        System.out.println(list.getHead().getData());
        Node cursor = list.head;
        for(int i=0; i<q; i++) {
            int t = scanner.nextInt();
            if(t==1) {
                System.out.println(cursor.data);
            }
            if(t==2) {
                if(cursor.next != null) {
                    cursor = cursor.next;
                }
            }
            if(t==3) {
                int x = scanner.nextInt();
                Node toAdd = new Node(x);
                toAdd.next = cursor.next;
                cursor.next = toAdd;
            }
        }
    }
}
