package leetcode.string_;

/**
 * 字符串相关题目
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "mississippi";
        String str2 = "issi";
        int i = solution.strStr(str, str2);
        System.out.println(i);
    }

    /**
     * 459. 重复的子字符串
     * KMP算法
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0) {
            return false;
        }
        int[] next = kmpNext(s);
        if (next[s.length() - 1] != 0 && s.length() % (s.length() - next[s.length() - 1]) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 28. 实现 strStr()
     * kmp算法
     *
     * @param haystack
     * @param needle
     * @return
     */

    //方法二：KMP算法
    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int[] next = kmpNext(needle);
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int[] kmpNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //方法一：暴力法
    public int strStr(String haystack, String needle) {
        if (needle == null) {
            return 0;
        }
        if (haystack == null) {
            return -1;
        }
        int i = 0;//指向 haystack
        int j = 0;//指向 needle
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == needle.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */

    //方法二：
    public String reverseLeftWords2(String s, int n) {
        StringBuilder str = new StringBuilder(s);
        //先反转前n个字符
        reverSubString(str, 0, n - 1);
        //反转n到最后所有字符串
        reverSubString(str, n, s.length() - 1);
        //反转整个字符串
        str.reverse();
        return str.toString();
    }

    public void reverSubString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start++;
            end--;
        }
    }

    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        res.append(s.substring(n));
        res.append(s.substring(0, n));
        return res.toString();
    }

    /**
     * 151. 颠倒字符串中的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] s1 = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (s1[i].length() > 0) {
                res.append(s1[i]);
                res.append(" ");
            }
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * 剑指 Offer 05. 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < str.length(); ) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, "%20");
                i += 3;
                continue;
            }
            i++;
        }
        return str.toString();
    }

    /**
     * 541. 反转字符串 II
     *
     * @param s
     * @param k
     * @return
     */
    //改进版本
    public String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i += 2 * k) {
            if (i + k - 1 < length - 1) {
                reverseSubStr(chars, i, i + k - 1);
                continue;
            }
            //剩下的字符串不到K个，全部反转
            reverseSubStr(chars, i, length - 1);
        }
        return new String(chars);
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int count = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            count++;
            if (count == 2 * k) {
                //反转前k个字符串
                reverseSubStr(chars, i - 2 * k + 1, i - k);
                count = 0;
            }
        }
        if (count == 0) {
            return new String(chars);
        } else if (count <= k) {
            //反转剩下的所有字符串
            reverseSubStr(chars, length - 1 - (count - 1), length - 1);
        } else {
            //反转前k个字符串
            reverseSubStr(chars, length - 1 - (count - 1), length - 1 - (count - 1) + (k - 1));
        }
        return new String(chars);
    }

    //反转部分字符串
    public void reverseSubStr(char[] chars, int i, int j) {
        int left = i;
        int right = j;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }

    /**
     * 344. 反转字符串
     *
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 1) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
