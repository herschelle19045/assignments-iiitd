package Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TanmayAndWhatsapp {

    static class Heap {
    long[] array;
    int size;

    Heap(int capacity) {
        array = new long[capacity];
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

    void insert(long value) {
        if(isFull()) {
            return;
        }
        array[size] = value;
        int index = size;
        long newValue = array[index];
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
        if(left < size && array[left] < array[index]) {
            smallest = left;
        }
        if(right < size && array[right] < array[smallest]) {
            smallest = right;
        }
        if(smallest != index) {
            long tmp = array[index];
            array[index] = array[smallest];
            array[smallest] = tmp;
            minHeapify(smallest);
        }
    }

    long extractMin() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        if (size == 1) {
            return array[--size];
        }
        long temp = array[0];
        array[0] = array[size-1];
        array[size-1] = temp;
        size--;
        minHeapify(0);
        return array[size];
    }

    long peek() {
        return array[0];
    }
}


    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int k = Reader.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            heap.insert(Reader.nextLong());
        }
        System.out.println(combine(heap, k));
    }

    static int combine(Heap heap, int k) {
        int count = 0;
        while (heap.peek() < k) {
            if(heap.size == 1) {
                return -1;
            }
            long value1 = heap.extractMin();
            long value2 = heap.extractMin();
            heap.insert(value1 + value2);
            count++;
        }
        return count;
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /**
     * call this method to initialize reader for InputStream
     */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /**
     * get next word
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
