package leetcode.backtrack;


import java.util.*;

// 回溯算法
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
    }

    //131. 分割回文串3
    List<List<String>> res;
    List<String> path;

    public List<List<String>> partition(String s) {
        res = new LinkedList<>();
        path = new LinkedList<>();
        if (s.length() == 1) {
            path.add(s);
            res.add(path);
            return res;
        }
        back(s, 0);
        return res;
    }

    public void back(String s, int start) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isHuiWen(s, start, i)) {
                continue;
            }
            path.add(s.substring(start, i + 1));
            back(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isHuiWen(String s, int i, int j) {
        if (i == j) return false;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    /*//40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        res = new LinkedList<>();
        path = new LinkedList<>();
        if (candidates[0] > target) return res;
        backTrack(candidates, target, 0, 0);
        return res;
    }

    public void backTrack(int[] candidates, int target, int sum, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            path.add(candidates[i]);
            backTrack(candidates, target, sum + candidates[i], i + 1);
            path.remove(path.size() - 1);//回溯
        }
    }
*/
    //39. 组合总和
   /* List<List<Integer>> res;
    List<Integer> path;*/

    /*public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<List<Integer>>();
        path = new LinkedList<>();
        int min = candidates[0];
        for (int i = 1; i < candidates.length; i++) {
            if (min > candidates[i]) min = candidates[i];
        }
        if (target < min) return res;
        combination(candidates, target, 0, 0,min);
        return res;
    }

    public void combination(int[] candidates, int target, int sum, int start,int min) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum < target && sum + min > target) break;
            if (sum + candidates[i] > target) continue;
            path.add(candidates[i]);
            combination(candidates, target, sum + candidates[i], i,min);
            path.remove(path.size() - 1);//回溯
        }
    }*/

    //17. 电话号码的字母组合
    /*List<String> res = new LinkedList<>();//存放结果
    StringBuilder path = new StringBuilder();//存放单一结果

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        //存放数字和字母的映射关系
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterTrack(0, digits, map);
        return res;
    }

    public void letterTrack(int index, String digits, String[] map) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }
        for (int i = index; i < digits.length(); i++) {
            String str = map[digits.charAt(i)-'0'];
            for (int j = 0; j < str.length(); j++) {
                path.append(str.charAt(j));
                letterTrack(i + 1, digits, map);
                path.deleteCharAt(path.length() - 1);//回溯
            }
        }
    }*/

    //216. 组合总和 III
    /*public List<List<Integer>> combinationSum3(int k, int n) {
        int min = (1 + k) * k / 2;
        if (min > n) return res;
        sumK(k, n, 1, 0);
        return res;
    }

    public void sumK(int k, int n, int startIndex, int sum) {
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            if (n - i + 1 < k - path.size()) break;//剪枝
            if (sum + i > n) break;
            path.add(i);
            sumK(k, n, i + 1, sum + i);
            path.remove(path.size() - 1);//回溯
        }
    }*/

    //77. 组合
    /*List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, 1);
        return res;
    }

    public void backTracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            // path.size() 还需要多少个元素
            if (n - i + 1 < k - path.size()) break;
            path.add(i);
            backTracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }*/
}
