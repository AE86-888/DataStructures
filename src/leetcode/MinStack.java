package leetcode;

import java.util.ArrayList;

public class MinStack {
    ArrayList<Integer> arrayList;
    //int min_num;

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.min());
    }

    public MinStack() {
        arrayList = new ArrayList<Integer>();
    }

    public void push(int x) {

        arrayList.add(x);
    }

    public void pop() {
        if (arrayList.size() > 0) {
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public int top() {
        return arrayList.get(arrayList.size() - 1);
    }

    public int min() {
        int min = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++) {
            if (min > arrayList.get(i)) {
                min = arrayList.get(i);
            }
        }
        return min;
    }
}
