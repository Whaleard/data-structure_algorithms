package queue;

/**
 * 使用数组模拟环形队列思路分析：
 *      1、front变量的含义：front指向队列的第一个元素， 也就是说arr[front]就是队列的第一个元素，front的初始值为0
 *      2、rear变量的含义：rear指向队列的最后一个元素的后一个位置，rear的初始值为0
 *      3、当队列满时，条件是(rear + 1) % maxSize == front
 *      4、队列为空的条件，rear == front
 *      5、队列中有效数据的个数(rear + maxSize - front) % maxSize
 *
 * @author Mr.MC
 */
public class CircleArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 该数组用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     * @param arrMaxSize
     */
    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[this.maxSize];
        // front指向队列头部，初始化为0
        // rear指向队列尾部，初始化为0
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * 添加数据到队列
     * @param n
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据！");
            return;
        }
        // 直接将数据加入
        arr[this.rear] = n;
        // 将rear后移，这里必须考虑取模
        this.rear = (this.rear + 1) % this.maxSize;
    }

    /**
     * 获取队列的数据，出队列
     */
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空，不能取数据！");
        }
        // 这里需要分析出front指向队列的第一个元素
        // 1、先把front对应的值保留到一个临时变量
        // 2、将front后移，考虑取模
        // 3、将临时保存的变量返回
        int value = this.arr[this.front];
        this.front = (this.front + 1) % this.maxSize;
        return value;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            System.out.println("队列空，没有数据！");
        }
        // 遍历
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = this.front; i < (this.front + this.size()); i++) {
            System.out.printf("arr[%d]=%d\n", i % this.maxSize, this.arr[i % this.maxSize]);
        }
    }

    /**
     * 显示队列的头数据，注意不是取数据
     */
    public int headQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据！");
        }
        return this.arr[this.front];
    }

    /**
     * 求出当前队列有效数据的个数
      * @return
     */
    public int size() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }
}
