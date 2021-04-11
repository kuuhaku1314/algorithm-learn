package algorithm.chapter7;


/**
 * @author by kuuhaku
 * @Date 2021/4/5 16:27
 * @Description
 */
public class Problem {

    /**
     * 原址划分，时间复杂度n
     * @param array
     * @param p
     * @param r
     * @return
     */
    private int partition(int[] array, int p, int r) {
        int i = p - 1;
        int x = array[r];
        int temp;
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        temp = array[r];
        array[r] = array[i + 1];
        array[i + 1] = temp;
        return i + 1;
    }

    /**
     * 快排，时间复杂度nlgn
     * @param array
     * @param q
     * @param r
     */
    public void quickSorted(int[] array, int q, int r) {
        if (q < r) {
            int partition = partition(array, q, r);
            quickSorted(array, q, partition - 1);
            quickSorted(array, partition + 1, r);
        }
    }

    /**
     * 尾递归
     * @param array
     * @param q
     * @param r
     */
    public void tailQuickSorted(int[] array, int q, int r) {
        while (q < r) {
            int partition = partition(array, q, r);
            quickSorted(array, q, partition - 1);
            q = partition + 1;
        }
    }

}
