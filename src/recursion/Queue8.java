package recursion;

/**
 * 8皇后问题
 *  在一个8*8的棋盘上放8个皇后，任意2个皇后不能处于同一行、同一列以及同一条斜线上，输出所有可能的摆放情况
 *
 * @author Mr.MC
 */
public class Queue8 {

    /**
     * 有8个皇后
     */
    int max = 8;

    /**
     * 定义一个数组，保存皇后放置位置的结果，数组下标表示第几个皇后（第几个皇后就放在第几行）
     */
    int[] array = new int[this.max];

    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法\n", Queue8.count);
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n) {
        if (n == this.max) {
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < this.max; i++) {
            // 先把当前皇后n，放到该行的第1列
            this.array[n] = i;
            // 判断当放置第n个皇后到第i列时，是否冲突
            if (judge(n)) {
                // 接着放第n+1个皇后，即开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行array[n]=i，即将第n个皇后，放置在本行的后移一个位置
        }
    }

    /**
     * 查看当前放置的第n个皇后，是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 1、this.array[i] == this.array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            // 2、Math.abs(n - i) == Math.abs(this.array[n] - this.array[i]) 表示判断第n个皇后是否和前面的n-1个皇后在同一斜线
            if (this.array[i] == this.array[n] || Math.abs(n - i) == Math.abs(this.array[n] - this.array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }
}
