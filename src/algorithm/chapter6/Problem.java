package algorithm.chapter6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * author:   by kuuhaku
 * Date:     2021/2/8 21:17
 * Description: todo
 */
public class Problem {

    /**
     * 6.2.2 维护最小堆 递归版
     * 时间复杂度lgn
     * @param array 最小堆
     * @param index 需要维护的下标
     */
    public void minHeapify(int[] array, int index) {
        int min = index;
        if (index * 2 + 1 < array.length && array[index * 2 + 1] < array[index]) {
            min = index * 2 + 1;
        }
        if (index * 2 + 2 < array.length && array[index * 2 + 2] < array[min]) {
            min = index * 2 + 2;
        }
        if (min != index) {
            int temp = array[index];
            array[index] = array[min];
            array[min] = temp;
            minHeapify(array, min);
        }
    }


    /**
     * 6.2.5 维护最大堆 循环版
     * 时间复杂度lgh, h为树高，即lgn
     * @param array 最小堆
     * @param index 需要维护的下标
     */
    public void maxHeapify(int[] array, int index) {
        while (true) {
            int min = index;
            if (index * 2 + 1 < array.length && array[index * 2 + 1] < array[index]) {
                min = index * 2 + 1;
            }
            if (index * 2 + 2 < array.length && array[index * 2 + 2] < array[min]) {
                min = index * 2 + 2;
            }
            if (min != index) {
                int temp = array[index];
                array[index] = array[min];
                array[min] = temp;
                index = min;
                continue;
            }
            break;
        }
    }

    /**
     * 建立最大堆
     * 时间复杂度 n
     * @param array 待建数组
     */
    public void buildMaxHeap(int[] array) {
        for (int i = array.length / 2 + 1; i >= 0; ++i) {
            maxHeapify(array, i);
        }
    }

    static class MinHeap<T extends Comparable<T>> {
        private int capacity;
        private int size;
        private T[] array;

        private void minHeapify(int index) {
            int min = index;
            if (index << 1 + 1 < size && array[index << 1 + 1].compareTo(array[index]) < 0) {
                min = index * 2 + 1;
            }
            if (index << 1 + 2 < size && array[index << 1 + 2].compareTo(array[min]) < 0) {
                min = index * 2 + 2;
            }
            if (min != index) {
                T temp = array[index];
                array[index] = array[min];
                array[min] = temp;
                minHeapify(min);
            }
        }

        public void buildMinHeap(T[] array) {
            this.array = Arrays.copyOf(array, array.length);
            size = array.length;
            for (int i = size >> 1 + 1; i >= 0; ++i) {
                minHeapify(i);
            }
            capacity = size;
        }

        public void insert(T t) {
            if (capacity <= size) {
                array = Arrays.copyOf(this.array, capacity << 1);
                capacity = array.length;
            }
            size++;
            array[size - 1] = t;
            int index = size - 1;
            T value = array[index];
            while (index >= 0 && array[(index - 1) >> 1].compareTo(array[index]) > 0) {
                array[index] = array[(index - 1) >> 1];
                index = (index - 1) >> 1;
            }
            array[index] = value;
        }

        private T extractMin() {
            T t = array[0];
            array[0] = array[size - 1];
            size--;
            minHeapify(0);
            return t;
        }

        private boolean delete(T t) {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (t.equals(array[i])) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return false;
            }
            if (index == size - 1) {
                size--;
                return true;
            }
            array[index] = array[size - 1];
            size--;
            minHeapify(index);
            return true;
        }

    }


    public static void main(String[] args) {

    }


}
