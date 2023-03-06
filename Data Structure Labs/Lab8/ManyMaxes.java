package Lab8;

import java.util.Scanner;

public class ManyMaxes {

    static class BinaryHeap {
        int[] heap;
        int size;

        BinaryHeap(int capacity) {
            heap = new int[capacity];
        }

        int getParent(int index) {
            return (index - 1) / 2;
        }

        int getChild(int index, boolean left) {
            return 2 * index + (left ? 1 : 2);
        }

        boolean isFull() {
            return size == heap.length;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void insert(int value) {
            if (isFull()) {
                throw new IndexOutOfBoundsException("Heap is full");
            }
            heap[size] = value;
            fixHeapAbove(size);
            size++;
        }

        void delete() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("Heap is Empty");
            }
            int parent = getParent(0);
            int deletedValue = heap[0];

            heap[0] = heap[size - 1];

            fixHeapBelow(0, size - 1);
            size--;
            // return deleted value for int function
        }

        void delete(int index) {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("Heap is Empty");
            }
            int parent = getParent(index);
            int deletedValue = heap[index];

            heap[index] = heap[size - 1];

            if (index == 0 || heap[index] < heap[parent]) {
                fixHeapBelow(index, size - 1);
            } else {
                fixHeapAbove(index);
            }
            size--;
            // return deleted value for int function
        }

        void fixHeapAbove(int index) {
            int newValue = heap[index];
            while (index > 0 && newValue > heap[getParent(index)]) {
                heap[index] = heap[getParent(index)];
                index = getParent(index);
            }
            heap[index] = newValue;
        }

        void fixHeapBelow(int index, int lastHeapIndex) {
            int childToSwap;

            while (index <= lastHeapIndex) {
                int leftChild = getChild(index, true);
                int rightChild = getChild(index, false);

                if (leftChild <= lastHeapIndex) {
                    if (rightChild > lastHeapIndex) {
                        childToSwap = leftChild;
                    } else {
                        childToSwap = heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
                    }
                    if (heap[index] < heap[childToSwap]) {
                        int tmp = heap[index];
                        heap[index] = heap[childToSwap];
                        heap[childToSwap] = tmp;
                    } else {
                        break;
                    }

                    index = childToSwap;
                } else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        BinaryHeap heap = new BinaryHeap(q);
        heap.insert(1);
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt();
            if (x == 1) {
                int num = scanner.nextInt();
                heap.insert(num);
            } else if (x == 2) {
                System.out.println(heap.heap[0]);
            } else {
                heap.delete();
            }
        }
    }
}
