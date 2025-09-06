package sort;

/**
 * 选择排序
 *
 * @author Mr.MC
 */
public class SelectSort {

    public static void main(String[] args) {
        // 测试一下冒泡排序的时间复杂度O(n^2)
        // 创建一个长度为80000的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        // 排序开始时间
        long startTime = System.currentTimeMillis();

        // 测试冒泡排序
        selectSort(arr);

        // 排序结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("选择排序共花费：" + (endTime - startTime) / 1000 + "秒");
    }

    /**
     * 选择排序算法
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
