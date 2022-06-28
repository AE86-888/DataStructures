package com.anguigu.stack;

public class NiCal {
    public static void main(String[] args) {
        //String expression = "30 4 + 5 * 6 -";
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        ArrayStack3 numStack = new ArrayStack3(10);
        ArrayStack3 operStack = new ArrayStack3(10);
        char c;
        int i = 0;
        while (true) {
            if (i >= expression.length()) {
                break;
            }
            c = expression.charAt(i);
            if (c == ' ') {//遇到空格，跳过，同时i++
                i++;
                continue;
            }
            String nums = "";
            if (c >= '0' && c <= '9') {
                nums += c;
                while (true) {
                    if (i + 1 >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(++i);
                    if (c >= '0' && c <= '9') {
                        nums += c;
                    } else {
                        break;
                    }
                }
                numStack.push(Integer.parseInt(nums));
            }
            if (operStack.isOper(c)) {//如果是运算符
                int res = operStack.cal(numStack.pop(), numStack.pop(), c);
                numStack.push(res);
            }
            i++;
        }
        System.out.println("计算结果：" + numStack.pop());
    }
}

//在之前栈的基础上进行扩展
class ArrayStack3 {
    private int maxSize;
    private int[] stack;
    private int top = -1;//表示栈底

    public ArrayStack3(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek() {
        return stack[top];
    }

    //返回运算符的优先级,数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//目前假定所有的表达式，只有 + - * /
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法

    /**
     * @param num1 第 1 个出栈的数
     * @param num2 第 2 个出栈的数
     * @param oper 运算符
     * @return 计算结果
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        if (oper == '+') {
            res = num1 + num2;
        } else if (oper == '-') {
            res = num2 - num1;
        } else if (oper == '*') {
            res = num1 * num2;
        } else if (oper == '/') {
            res = num2 / num1;
        }
        return res;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满了，入栈失败");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据~");
            return;
        }
        int i = top;
        while (i > -1) {
            System.out.print(stack[i] + " ");
            i--;
        }
        System.out.println();
    }
}