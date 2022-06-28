package com.anguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        //String expression = "7*2*2-5+1-5+3-4";
        String expression = "10+11+12*2";//45
        //String expression = "1+2+3";

        ArrayStack2 numStack = new ArrayStack2(10);//存放数字
        ArrayStack2 operStack = new ArrayStack2(10);//存放操作符
        int i = 0;
        while (true) {
            if (i >= expression.length()) {
                break;
            }
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9') {
                //完成计算 多位数  的计算
                String num = c + "";
                while (true) {
                    if (i + 1 >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(++i);
                    if (c >= '0' && c <= '9') {
                        num += c;
                    } else {
                        break;
                    }
                }
                numStack.push(Integer.parseInt(num));

            }
            if (operStack.isOper(c)) {
                if (operStack.isEmpty() || operStack.priority(c) > operStack.priority(operStack.peek())) {
                    operStack.push(c);
                } else {
                    //当前操作符的优先级小于或等于栈顶的操作符，进行计算
                    int num1 = numStack.pop();
                    int num2 = numStack.pop();

                    int oper = operStack.pop();
                    int res = numStack.cal(num1, num2, oper);
                    //将计算结果入数栈
                    // + 48 将 数字 转化成 字符
                    numStack.push(res);
                    //当前操作符  入 栈
                    operStack.push(c);
                }
            }
            i++;
        }
        //计算结束后数栈中最后一个数据，就是计算结果
        while (!operStack.isEmpty()) {
            int curRes = numStack.cal(numStack.pop(), numStack.pop(), operStack.pop());
            numStack.push(curRes);
        }
        System.out.println("计算结果：" + numStack.pop());
    }
}

//在之前栈的基础上进行扩展
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;//表示栈底

    public ArrayStack2(int maxSize) {
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

