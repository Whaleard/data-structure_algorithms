package stack;

/**
 * @author Mr.MC
 */
public class Calculator {
    public static void main(String[] args) {
        // 表达式
        String expression = "3+2*6-2";
        // 创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operateStack = new ArrayStack2(10);
        // 定义需要的相关变量
        // 用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operate = 0;
        int result = 0;
        // 将每次扫描得到的char保存到ch
        char ch = ' ';
        // 用于拼接多位数
        String keepNum = "";
        // 开始循环扫描expression表达式
        while (true) {
            // 依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是数字还是符号，然后做相应的处理
            if (operateStack.isOperate(ch)) {
                // 判断当前符号栈是否为空
                if (!operateStack.isEmpty()) {
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算
                    if (operateStack.priority(ch) <= operateStack.priority(operateStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operate = operateStack.pop();
                        result = numStack.calculate(num1, num2, operate);
                        // 把运算的结果入数栈
                        numStack.push(result);
                        // 然后将当前操作符入符号栈
                        operateStack.push(ch);
                    } else {
                        // 如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
                        operateStack.push(ch);
                    }
                } else {
                    // 如果为空，直接入栈
                    operateStack.push(ch);
                }
            } else {
                // 如果是数，则直接入数栈
                // numStack.push(ch - 48);

                // 分析思路：如果表达式中的数据是多位
                // 1、当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                // 2、在处理时，需要向expression的表达式的索引后再看一位，如果是数就进行扫描，如果是符号才入栈
                // 3、因此我们需要定义一个字符串变量用于拼接

                // 处理多位数
                keepNum += ch;

                // 如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.valueOf(keepNum));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，继续扫描，如果是运算符，则入栈
                    if (operateStack.isOperate(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，则入栈keepNum=“1”或者"123"
                        numStack.push(Integer.valueOf(keepNum));
                        // keepNum清空
                        keepNum = "";
                    }
                }
            }
            // 让遍历索引加1，并判断是否扫描到表达式最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 当表达式扫描完毕，就顺序地从数栈和符号栈中pop出相应的数和符号，并运算
        while (true) {
            // 如果符号栈为空，则已经计算到最后的结果，数栈中只有一个数字【结果】
            if (operateStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operate = operateStack.pop();
            result = numStack.calculate(num1, num2, operate);
            numStack.push(result);
        }

        System.out.printf("表达式：%s=%d\n", expression, numStack.pop());
    }
}

/**
 * 使用栈完成表达式计算的思路
 *  1、通过一个index索引，遍历表达式
 *  2、如果扫描的是一个数字，直接入数字栈
 *  3、如果扫描的是一个符号，就分如下情况
 *      3.1、如果当前的符号栈为空，直接入栈
 *      3.2、如果符号栈内有数据，就需要进行比较
 *           如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，
 *           将得到的结果，入数栈，再与符号栈中的操作符进行比较，如果当前的操作符优先级大于栈中操作符，直接入栈，否则重复上述操作
 *  4、当表达式扫描完毕，就顺序地从数栈和符号栈中pop出相应的数和符号，并运行
 *  5、最后在数栈中只有一个数字，就是表达式的结果
 */
class ArrayStack2 {
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
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    /**
     * 获取当前栈顶的值
     * @return
     */
    public int peek() {
        return this.stack[this.top];
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

    /**
     * 返回运算符的优先级，优先级使用数字表示
     * 数字越大，优先级越高
     * @param operate
     * @return
     */
    public int priority(int operate) {
        if (operate == '*' || operate == '/') {
            return 1;
        } else if (operate == '+' || operate == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     * @param value
     * @return
     */
    public boolean isOperate(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    public int calculate(int num1, int num2, int operate) {
        // 用于存放计算结果
        int result = 0;
        switch (operate) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}
