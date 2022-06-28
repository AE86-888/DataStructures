package leetcode;

import com.anguigu.stack.Queue8;
import netscape.security.UserTarget;

import java.util.Stack;


public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        cQueue.appendTail(4);

        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());


    }

    public CQueue() {
        stack1 = new Stack<>();//入栈
        stack2 = new Stack<>();//出栈
    }

    public void appendTail(int value) {
        stack1.add(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()){
                return -1;
            }
            while (!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
