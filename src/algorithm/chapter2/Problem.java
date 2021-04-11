package algorithm.chapter2;

/**
 * author:   by kuuhaku
 * Date:     2021/2/6 23:28
 * Description: 第二章习题
 */
public class Problem {

    private final static int NIL = -1;

    /**
     * 2.1.2 插入排序且以非升序方式
     * 时间复杂度n2
     * @param array 排序数组
     */
    public void insertionSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int length = array.length;
        for (int j = 1; j < length; ++j) {
            int i = j - 1;
            int temp = array[j];
            while (i >= 0 && array[i] < temp) {
                array[i + 1] = array[i];
                --i;
            }
            array[i + 1] = temp;
        }
    }

    /**
     * 2.1.3 查找数字下标
     * 时间复杂度n
     * @param array 查找数组
     * @param num 查找数
     * @return 返回该数的下标，不存在则返回-1
     */
    public int findNumIndex(int[] array, int num) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == num) {
                return i;
            }
        }
        return NIL;
    }

    /**
     * 2.1.4 返回两个大小为n的二进制数组相加得出的大小为n+1的二进制数组
     * 小端
     * 时间复杂度n
     * @param a 数组a
     * @param b 数组b
     * @param n 数组length
     * @return 相加后得出的新数组
     */
    public byte[] numberAddNumber (byte[] a, byte[] b, int n) {
        byte[] c = new byte[n + 1];
        byte v = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] + b[i] + v > 1) {
                c[i] = (byte) (a[i] + b[i] + v - 2);
                v = 1;
            } else {
                c[i] = (byte) (a[i] + b[i] + v);
                v = 0;
            }
        }
        c[n] = v;
        return c;
    }

    /**
     * 2.2.2 选择排序
     * 时间复杂度n2
     * @param array 排序数组
     */
    public void selectionSort(int[] array) {
        int length = array.length;
        int min, index, i, k, j;
        for (i = 0; i < length - 1; ++i) {
            min = array[i];
            index = i;
            for (j = i + 1; j < length; ++j) {
                if (array[j] < min) {
                    min = array[j];
                    index = j;
                }
            }
            for (k = index; k > i; --k) {
                array[k] = array[k - 1];
            }
            array[i] = min;
        }
    }

    /**
     * 2.3.2 归并排序
     * @param array 待排序数组
     * @param p 排序部分左下标
     * @param r 排序部分右下标
     */
    public void mergeSort(int[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(array, p, q);
            mergeSort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    /**
     * 合并两个有序数组，非哨兵版
     * 时间复杂度n
     * @param array 待排序数组
     * @param p 左堆的左边界下标
     * @param q 左堆的右边界下标
     * @param r 右堆的右边界下标
     * @return 逆序对数量
     */
    private int merge(int[] array, int p, int q, int r) {
        int size = r - p + 1;
        int arrayOneIndex = p;
        int arrayTwoIndex = q + 1;
        int inversionNum = 0;
        int[] tempArray = new int[size];
        for (int i = 0; i < size; ++i) {
            if (arrayOneIndex > q) {
                tempArray[i] = array[arrayTwoIndex];
                ++arrayTwoIndex;
                continue;
            }
            if (arrayTwoIndex > r) {
                tempArray[i] = array[arrayOneIndex];
                ++arrayOneIndex;
                continue;
            }
            if (array[arrayOneIndex] <= array[arrayTwoIndex]) {
                tempArray[i] = array[arrayOneIndex];
                ++arrayOneIndex;
            } else {
                tempArray[i] = array[arrayTwoIndex];
                ++arrayTwoIndex;
                inversionNum = inversionNum + q - arrayOneIndex + 1;
            }
        }
        if (size >= 0) {
            System.arraycopy(tempArray, 0, array, p, size);
        }
        return inversionNum;
    }

    /**
     * 2.3.5 有序数组查找某数字下标
     * 迭代版
     * 时间复杂度lgn
     * @param array 有序数组
     * @param num 待查找数字
     * @return 下标，为负数则说明数字未找到，排序应插入在-(result + 1)的位置
     */
    public int findNumber(int[] array, int num) {
        int start = 0, end = array.length - 1;
        int medium;
        while (start <= end) {
            medium = (start + end) >> 1;
            if (array[medium] > num) {
                end = medium - 1;
            } else if (array[medium] < num) {
                start = medium + 1;
            } else {
                return medium;
            }
        }
        return -(start + 1);
    }

    /**
     * 2.3.5 有序数组查找某数字下标
     * 递归版
     * 时间复杂度lgn
     * @param array 有序数组
     * @param num 待查找数字
     * @param start 左边界下标
     * @param end 右边界下标
     * @return 下标，为负数则说明数字为找到，排序应插入在-(result + 1)的位置
     */
    public int findNumber(int[] array, int num, int start, int end) {
        if (start <= end) {
            int medium = (start + end) >> 1;
            if (array[medium] > num) {
                return findNumber(array, num, start, medium - 1);
            } else if (array[medium] < num) {
                return findNumber(array, num, medium + 1, end);
            } else {
                return medium;
            }
        }
        return -(start + 1);
    }

    /**
     * 2.3.6 插入排序二分法版本
     * 时间复杂度n2
     * @return
     */
    public void insertionSortByBinary(int[] array, int left, int right) {
        for (int j = left + 1; j <= right; ++j) {
            int end = j - 1;
            int temp = array[j];
            int index = findNumber(array, temp, left, end);
            index = index >= left ? index : -index - 1;
            while (end >= index) {
                array[end + 1] = array[end];
                --end;
            }
            array[index] = temp;
        }
    }

    /**
     * 2.3.7 数组中是否存在两个数之和等于第三个数
     * 时间复杂度nlgn
     * @param array 数组
     * @param num 寻找的数
     * @return 是否存在
     */
    public boolean haveTwoNumber(int[] array, int num) {
        mergeSort(array, 0, array.length - 1);
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start] + array[end];
            if (temp == num) {
                return true;
            } else if (temp > num) {
                --end;
            } else {
                ++start;
            }
        }
        return false;
    }

    /**
     * 2.1 归并排序转插入折半排序
     * @param array 待排序数组
     * @param p 左边界
     * @param r 右边界
     */
    public void mergeAndInsertionSort(int[] array, int p, int r) {
        final int k = 5;
        if (r - p < k) {
            insertionSortByBinary(array, p, r);
        }
        if (p < r) {
            int q = (p + r) / 2;
            mergeAndInsertionSort(array, p, q);
            mergeAndInsertionSort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    /**
     * 2.2 冒泡排序
     * 时间复杂度 n2
     * @param array 待排序数组
     */
    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = array.length - 1; j > i; --j) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 2.3 多项式求和
     * 时间复杂度为n
     * @param x 值
     * @param array 项的系数数组，从0开始
     * @return
     */
    public int polynome(int x, int[] array) {
        int y = array[0];
        for (int i = 1; i < array.length; ++i) {
            y = y + array[i] * x;
            x = x * x;
        }
        return y;
    }

    /**
     * 2.3 多项式求和horner版本
     * 时间复杂度为n
     * @param x 值
     * @param array 项的系数数组，从0开始
     * @return
     */
    public int hornerPolynome(int x, int[] array) {
        int y = 0;
        for (int i = array.length - 1; i >= 0; --i) {
            y = y * x + array[i];
        }
        return y;
    }

    /**
     * 2.4 求逆序对个数
     * 时间复杂度nlgn
     * @param array 待排序数组
     * @param p 排序部分左下标
     * @param r 排序部分右下标
     */
    public int inversionNum(int[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            return inversionNum(array, p, q) + inversionNum(array, q + 1, r) + merge(array, p, (p + r) / 2, r);
        }
        return 0;
    }

    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int max = 0;
        while(start < height.length && end >= 0 && start < end) {
            int temp = (end - start) * Math.min(height[end], height[start]);
            if(temp > max) {
                max = temp;
            }
            int startNext = (end - start - 1) * Math.min(height[end], height[start + 1]);
            int endNext = (end - start - 1) * Math.min(height[end - 1], height[start]);
            if(startNext >= endNext) {
                start++;
            } else {
                end++;
            }
        }
        return max;
    }




    public static void main(String[] args) {
    }
}
