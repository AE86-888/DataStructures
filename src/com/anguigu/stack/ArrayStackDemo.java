package com.anguigu.stack;


public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
        System.out.println( stack.pop());

        stack.list();
    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;//表示栈底

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
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