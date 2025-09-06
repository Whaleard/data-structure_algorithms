package sort;

/**
 * 冒泡排序
 *
 * @author Mr.MC
 */
public class BubbleSort {

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
        bubbleSort(arr);

        // 排序结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("冒泡排序共花费：" + (endTime - startTime) + "毫秒");
    }

    /**
     * 冒泡排序算法
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 临时变量，用于交换的中间变量
        int temp;
        // 标识是否做过交换
        boolean flag = false;
        // 第一趟排序，将最大的数排在最后
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            // 在一趟排序中，一次交换都没有发生过
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
