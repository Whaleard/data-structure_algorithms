package stack;

import java.util.Scanner;

/**
 * 使用数组模拟栈
 *
 * @author Mr.MC
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 创建ArrayStack对象
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        // 控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈中取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是：%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

/**
 * 栈的英文为：stack
 * 栈是一个先入后出(FILO-First In Last Out)的有序列表
 * 栈是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。允许插入和删除的一端，为变化的一端，称为栈顶(Top)，另一端为固定的一端，称为栈底(Bottom)
 * 根据栈的定义可知，最先放入栈中的元素在栈底，最后放入的元素在栈顶，而删除的元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除
 *
 * 栈的应用场景：
 *  1、子程序的调用：在跳往子程序前，会先将下一个指令的地址存到堆栈中，直到子程序执行完成后再将地址取出，以回到原来的程序中
 *  2、处理递归调用：和子程序的调用类似，只是除了存储下一个指令的地址外，也将参数、区域变量等数据存入堆栈中
 *  3、表达式的转换[中缀表达式转后缀表达式]与求值
 *  4、二叉树的遍历
 *  5、图形的深度优先(depth-first)搜索法
 */
class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组，模拟栈，数据就存放在该数组
     */
    private int[] stack;

    /**
     * top表示栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 构造器
     * @param maxSize
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    /**
     * 栈满
     * @return
     */
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    /**
     * 栈空
     * @return
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        this.top++;
        this.stack[this.top] = value;
    }

    /**
     * 出栈，将栈顶的数据返回
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空");
        }
        int value = this.stack[this.top];
        this.top--;
        return value;
    }

    /**
     * 显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }

        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, this.stack[i]);
        }
    }
}