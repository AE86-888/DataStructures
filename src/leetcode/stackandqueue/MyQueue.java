package leetcode.stackandqueue;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 */
public class MyQueue {
    Stack<Integer> stackIn;//进栈
    Stack<Integer> stackOut;//出栈

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.add(x);
    }

    public int pop() {
        dumpStackIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpStackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    //当stackOut为空的时候，将stackIn中所有元素放入 stackOut中
    public void dumpStackIn() {
        if (stackIn.isEmpty()) {
            return;
        }
        if (stackOut.isEmpty()){
            while (!stackIn.isEmpty()) {
                stackOut.add(stackIn.pop());
            }
        }
    }
}
