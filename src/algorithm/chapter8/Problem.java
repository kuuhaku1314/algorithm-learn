package algorithm.chapter8;

/**
 * @author by kuuhaku
 * @Date 2021/4/5 19:19
 * @Description
 */
public class Problem {

    /**
     * 计数排序，稳定，时间复杂度n
     * @param array
     * @param n 数组内元素大于等于0且小于n
     * @return
     */
    public int[] countSorted(int[] array, int n) {
        int[] c = new int[n];
        int length = array.length;
        int[] b = new int[length];
        for (int i = 0; i < length; i++) {
            c[array[i]]++;
        }
        for (int i = 1; i < n; i++) {
            c[i] += c[i - 1];
        }
        for (int i = length - 1; i >= 0 ; i--) {
            b[c[array[i]]--] = array[i];
        }
        return b;
    }

    /**
     * 基数排序，从最低位开始，以计数排序进行比较，共需要比较d轮计数排序
     * @param array
     * @param d
     * @return
     */
    public int[] radixSorted(int[] array, int d) {
        return null;
    }

    /**
     * 桶排序，把数所在的范围分为n个区间，把数放入这些区间里，对每个区间进行插入排序
     * 然后遍历出数组元素，即已排好，平均时间复杂度n
     * @param array
     */
    public void bucketSorted(int[] array) {

    }
}
