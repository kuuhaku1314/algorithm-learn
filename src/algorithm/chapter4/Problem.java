package algorithm.chapter4;

/**
 * author:   by kuuhaku
 * Date:     2021/2/7 14:44
 * Description: todo
 */
public class Problem {

    /**
     * 4.1.2 寻找最大子序列，暴力法
     * 时间复杂度 n2
     * @param array 待求数组
     * @return 子序列最大值
     */
    public int findMaximumSubArray(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            int temp = 0;
            for (int j = i; j < array.length; j++) {
                temp = temp + array[j];
                max = Math.max(max, temp);
            }
        }
        return max;
    }


    /**
     * 4.1.3 寻找最大子序列，分治法
     * 时间复杂度 nlgn
     * @param array 待求数组
     * @param left 左边界
     * @param right 右边界
     * @return 子序列最大值
     */
    public int findMaximumSubArray(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        } else {
            int mid = (right + left) / 2;
            int maxTemp = Math.max(findMaximumSubArray(array, left, mid), findMaximumSubArray(array, mid + 1, right));
            return Math.max(maxTemp, findMaximumCrossSubArray(array, left, mid, right));
        }
    }

    private int findMaximumCrossSubArray(int[] array, int left, int mid, int right) {
        int maxLeftValue = array[mid];
        int leftTempValue = 0;
        int maxRightValue = array[mid + 1];
        int rightTempValue = 0;
        for (int i = mid; i >= left ; --i) {
            leftTempValue = leftTempValue + array[i];
            if (leftTempValue > maxLeftValue) {
                maxLeftValue = leftTempValue;
            }
        }
        for (int j = mid + 1; j <= right ; ++j) {
            rightTempValue = rightTempValue + array[j];
            if (rightTempValue > maxRightValue) {
                maxRightValue = rightTempValue;
            }
        }
        return maxLeftValue + maxRightValue;
    }

    /**
     * 4.1.5 寻找最大子序列，动态规划
     * 时间复杂度 n
     * @param array 待求数组
     * @return 子序列最大值
     */
    public int findMaxSubArray(int[] array) {
        int max = array[0];
        int temp = 0;
        for (int i = 0; i < array.length ; i++) {
            temp = temp + array[i];
            if (temp > max) {
                max = temp;
            } else {
                temp = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1,5,-2,-8,26,12,-18,0,2,-1,5,-2,17};
        int[] array1 = {5};
        Problem problem = new Problem();
        System.out.println(problem.findMaxSubArray(array));
    }

}
