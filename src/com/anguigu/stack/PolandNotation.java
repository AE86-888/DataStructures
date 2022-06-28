package com.anguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String s = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(s);
        System.out.println(list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式：" + list1);
    }

    // 将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;//用于遍历 中缀表达式字符串
        String str = "";// 对多位数的拼接
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c > '0' && c < '9') {
                str += c;
                while (i + 1 < s.length()) {
                    c = s.charAt(++i);
                    if (c > '0' && c < '9') {
                        str += c;
                    } else {
                        break;
                    }
                }
                ls.add(str);
                str = "";//将str清空
            }
            if (!(c > '0' && c < '9')) {
                ls.add(c + "");
            }
            i++;
        }
        return ls;
    }

    //中缀表达式 转换成 后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        ArrayList<String> suffixList = new ArrayList<>();
        Stack<String> s2 = new Stack<>();//存储中间结果
        Stack<String> s1 = new Stack<>();//存储符号
        int i = 0;
        String str;
        while (i < ls.size()) {
            str = ls.get(i);
            char c = str.charAt(0);
            if (c >= '0' && c <= '9') {
                s2.add(str);
            } else if (c == '(') {
                s1.add(str);
            } else if (c == ')') {
                while (true) {
                    if ("(".equals(s1.peek())) {
                        s1.pop();//弹出 (
                        break;
                    }
                    s2.add(s1.pop());
                }
            } else {//遇到运算符
                while (true) {
                    if (s1.isEmpty() || s1.peek() == "(" || priority(c) > priority(s1.peek().charAt(0))) {
                        s1.add(str);
                        break;
                    } else {
                        s2.add(s1.pop());
                    }
                }
            }
            i++;
        }
        // 7.将s1中剩余的运算符依次弹出并压入s2
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        suffixList.addAll(s2);
        return suffixList;
    }

    //运算符的优先级 返回的数字越大，优先级越高
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//目前假定所有的表达式，只有 + - * /
        }
    }
}

