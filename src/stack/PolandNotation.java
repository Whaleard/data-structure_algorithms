package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰
 * @author Mr.MC
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // 为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        // 思路
        // 1、先将"3 4 + 5 * 6 -"放到ArrayList中
        // 2、将ArrayList传递给一个方法，遍历ArrayList，配合栈，完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list=" + list);
        int result = calculate(list);
        System.out.println("计算结果是：" + result);
    }

    /**
     * 使用逆波兰表达式，依次将数据和运算符放入到ArrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item : list) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // 出栈两个数，运算后再将结果入栈
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int result = 0;
                if ("+".equals(item)) {
                    result = num1 + num2;
                } else if ("-".equals(item)) {
                    result = num1 - num2;
                } else if ("*".equals(item)) {
                    result = num1 * num2;
                } else if ("/".equals(item)) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把result入栈
                stack.push("" + result);
            }
        }
        // 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
