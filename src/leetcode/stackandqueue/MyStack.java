package leetcode.stackandqueue;

import java.util.ArrayList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 */
public class MyStack {
    ArrayList<Integer> listIn;//进队
    ArrayList<Integer> listOut;//出队

    public MyStack() {
        listIn = new ArrayList<>();
        listOut = new ArrayList<>();
    }

    public void push(int x) {
        listIn.add(x);
    }

    public int pop() {
        dumpListIn();
        return listOut.remove(0);
    }

    public int top() {
        dumpListIn();
        return listOut.get(0);
    }

    public boolean empty() {
        return listIn.isEmpty() && listOut.isEmpty();
    }

    public void dumpListIn() {
        while (!listIn.isEmpty()) {
            listOut.add(0, listIn.remove(0));
        }
    }
}
