package queue;

/**
 * 队列特点
 *     1、队列是一个有序列表，可以用数组或是链表来实现
 *     2、遵循先入先出原则。即：先存入队列的数据，要先取出；后存入的要后取出
 *
 * 使用数组模拟队列
 *
 * @author Mr.MC
 */
class ArrayQueue {
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
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[this.maxSize];
        // 指向队列头部，初始化时指向队列的前一个位置
        this.front = -1;
        // 指向队列尾部，直接指向队列的最后一个数据
        this.rear = -1;
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull() {
        return this.rear == this.maxSize - 1;
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
        // 尾指针后移
        // 尾指针指向队列最后一个数据，故先将尾指针后移一位再将数据入队列
        this.rear++;
        this.arr[this.rear] = n;
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
        // 头指针后移
        // 头指针指向队列第一个数据的前一个位置，故先将头指针后移一位再将数据出队列
        this.front++;
        return this.arr[this.front];
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
        for (int i = 0; i < this.arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, this.arr[i]);
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
        return this.arr[this.front + 1];
    }
}
