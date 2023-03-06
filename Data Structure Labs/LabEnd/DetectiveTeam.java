package EndSem;

import java.util.Arrays;
import java.util.Scanner;

public class DetectiveTeam {

    static class Heap {
        double[] array;
        int size;

        Heap(int capacity) {
            array = new double[capacity];
        }

        int getChild(int index, boolean left) {
            return 2 * index + (left ? 1 : 2);
        }

        int getParent(int index) {
            return (index - 1) / 2;
        }

        boolean isFull() {
            return size == array.length;
        }

        void insert(double value) {
            if (isFull()) {
                return;
            }
            array[size] = value;
            int index = size;
            double newValue = array[index];
            while (index > 0 && newValue < array[getParent(index)]) {
                array[index] = array[getParent(index)];
                index = getParent(index);
            }
            array[index] = newValue;
            size++;
        }

        void minHeapify(int index) {
            int left = getChild(index, true);
            int right = getChild(index, false);
            int smallest = index;
            if (left < size && array[left] < array[index]) {
                smallest = left;
            }
            if (right < size && array[right] < array[smallest]) {
                smallest = right;
            }
            if (smallest != index) {
                double tmp = array[index];
                array[index] = array[smallest];
                array[smallest] = tmp;
                minHeapify(smallest);
            }
        }

        double extractMin() {
            if (size == 0) {
                throw new IndexOutOfBoundsException("Heap is empty");
            }
            if (size == 1) {
                return array[--size];
            }
            double temp = array[0];
            array[0] = array[size - 1];
            array[size - 1] = temp;
            size--;
            minHeapify(0);
            return array[size];
        }

        double peek() {
            return array[0];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] skills = new int[n];
        int[] wages = new int[n];
        for (int i = 0; i < n; i++) {
            skills[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            wages[i] = scanner.nextInt();
        }
        double[][] ratio = new double[n][2];
        for (int i = 0; i < n; i++) {
            ratio[i] = new double[]{(double) wages[i] / (double) skills[i], (double) skills[i]};
        }
        mergeSort(ratio);
        double result = Double.MAX_VALUE, sum = 0;
        Heap pq = new Heap(n);
        for (double[] detective : ratio) {
            sum += detective[1];
            pq.insert(-detective[1]);
            if (pq.size > k) {
                sum += pq.extractMin();
            }
            if (pq.size == k) {
                result = Math.min(result, sum * detective[0]);
            }
        }
        System.out.println((int) Math.ceil(result));
    }

    public static void mergeSort(double[][] array) {
        int l = array.length;
        if (l < 2) {
            return;
        }
        int mid = l / 2;
        double[][] left = Arrays.copyOf(array, mid);
        double[][] right = Arrays.copyOfRange(array, mid, l);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, array);
    }

    public static void merge(double[][] left, double[][] right, double[][] arr) {
        double l = left.length;
        double r = right.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l && j < r) {
            if (left[i][0] <= right[j][0]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < l) {
            arr[k++] = left[i++];
        }
        while (j < r) {
            arr[k++] = right[j++];
        }
    }
}
