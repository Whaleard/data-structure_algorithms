package linkedlist;

import java.util.Arrays;

/**
 * Josephus(约瑟夫、约瑟夫环)问题
 *  设编号为1,2,...n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，数到m的那个人出列，
 *  它的下一位又从1开始报数，数到m的那个人又出列，以此类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * @author Mr.Mc
 */
public class Josephus {
    public static void main(String[] args) {
        // 测试构建和遍历环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();

        // 测试节点出环形链表
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first节点，指向环形单向链表的第一个节点
     */
    private Boy first = null;

    /**
     * 添加节点，构建成一个环形链表
     * @param n
     */
    public void addBoy(int n) {
        // 数据校验
        if (n < 1) {
            System.out.println("num的值不正确");
            return;
        }

        // 辅助指针，帮助构建环形链表
        Boy cur = null;
        // 使用for循环创建环形链表
        for (int i = 1; i <= n; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            // 如果是第一个节点
            if (i == 1) {
                this.first = boy;
                // 构成环
                this.first.next = this.first;
                // 让cur指向第一个节点
                cur = this.first;
            } else {
                cur.next = boy;
                boy.next = this.first;
                cur = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void show() {
        // 判断链表是否为空
        if (this.first == null) {
            System.out.println("没有任何节点");
            return;
        }
        // 因为first不能动，因此我们使用一个辅助指针完成遍历
        Boy cur = this.first;
        while (true) {
            System.out.printf("节点的编号%d \n", cur.no);
            // 遍历结束
            if (cur.next == this.first) {
                break;
            }
            // 辅助节点后移
            cur = cur.next;
        }
    }

    /**
     * 计算节点出圈顺序
     *
     * @param k 表示从第几个节点开始数数
     * @param m 表示数几下
     * @param n 表示最初有多少个节点在环形链表中
     */
    public void countBoy(int k, int m, int n) {
        // 先对数据进行校验
        if (this.first == null || k < 1 || k > n) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        // 创建辅助指针，帮助完成节点出环
        Boy helper = this.first;
        // 创建一个辅助指针（变量）helper，指向环形链表的最后一个节点
        while (true) {
            if (helper.next == this.first) {
                break;
            }
            helper = helper.next;
        }
        // 将first和helper移动到环链表真正开始的地方，需要移动k-1次
        for (int j = 0; j < k - 1; j++) {
            this.first = this.first.next;
            helper = helper.next;
        }
        // 当节点遍历时，让first和helper指针同时移动m-1次，然后出环链表
        // 循环操作，直到环链表中只有一个节点
        while (true) {
            if (helper == this.first) {
                break;
            }
            // 让first和helper指针同时移动m-1
            for (int j = 0; j < m - 1; j++) {
                this.first = this.first.next;
                helper = helper.next;
            }
            // 这时first指向的节点，就是要出环链表的节点
            System.out.printf("节点%d出环链表\n", this.first.no);
            // 将first指向的节点出环链表
            this.first = this.first.next;
            helper.next = this.first;
        }
        System.out.printf("最后留在环链表中的节点编号%d \n", this.first.no);
    }

    // /**
    //  * 根据人数动态初始化链表
    //  * @param n
    //  */
    // public void init(int n, int k) {
    //     Node temp = this.head;
    //     Node first = null;
    //     for (int i = 1; i <= n; i++) {
    //         Node node = new Node(i);
    //         temp.next = node;
    //         temp = temp.next;
    //         if (i == 1) {
    //             first = node;
    //         }
    //         if (i == n) {
    //             temp.next = first;
    //         }
    //         if (k == 1) {
    //
    //         }
    //         if (i == k) {
    //             this.head.next = node;
    //         }
    //     }
    // }
    //
    // public int[] getNo(int n, int k, int m) {
    //     int[] arr = new int[n];
    //     int i = 0;
    //     int t = m;
    //     init(n, k);
    //     Node temp = this.head;
    //     while (temp.next != temp) {
    //         t--;
    //         if (t == 0) {
    //             arr[i++] = temp.next.no;
    //             temp.next = temp.next.next;
    //             t = m;
    //         } else {
    //             temp = temp.next;
    //         }
    //     }
    //     arr[i] = temp.no;
    //     return arr;
    // }
    //
    // public void list() {
    //     Node temp = this.head;
    //     while (temp.next != null) {
    //         System.out.println(temp.next);
    //         temp = temp.next;
    //     }
    // }
}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    /**
     * 编号
     */
    public int no;
    /**
     * 指向下一个节点，默认null
     */
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
