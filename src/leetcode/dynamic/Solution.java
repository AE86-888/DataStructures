package leetcode.dynamic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 动态规划
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2};
        int target = 3;
        int i = solution.coinChange(nums, 3);
        System.out.println(i);
    }

    //279. 完全平方数
    public int numSquares(int n) {
        //dp[i]容量为i的背包，最少需要完全平方数的个数
        int[] dp = new int[n + 1];
        //初始化
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j < n + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n] == Integer.MAX_VALUE ? 0 : dp[n];
    }


    //322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        //dp[i]表示装满容量为i的背包，需要的最少硬币数量
        int[] dp = new int[amount + 1];
        dp[0] = 0;//初始化
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {//遍历背包
            for (int j = 0; j < coins.length; j++) {//遍历物品
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //377. 组合总和 Ⅳ
    public int combinationSum4(int[] nums, int target) {
        //dp[j]表示装满容量为j的背包，有dp[j]中组合数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j < target + 1; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j < nums[i]) continue;
                dp[j] += dp[j - nums[i]];
            }
            for (int i = 0; i < dp.length; i++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[target];
    }

    //474. 一和零
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j] 最多有i个0，对多有j个1的背包的最大子集的数目
        int[][] dp = new int[m + 1][n + 1];
        for (int k = 0; k < strs.length; k++) {
            List<Integer> res = getZeroAndOneNum(strs[k]);
            for (int i = m; i >= res.get(0); i--) {
                for (int j = n; j >= res.get(1); j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - res.get(0)][j - res.get(1)] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public List<Integer> getZeroAndOneNum(String str) {
        ArrayList<Integer> res = new ArrayList<>();
        int zeroNum = 0;
        int oneNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                oneNum++;
            } else {
                zeroNum++;
            }
        }
        res.add(zeroNum);
        res.add(oneNum);
        return res;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) return 0;

        int bagSize = (sum + target) / 2;
        //dp[i]表示装满容量为i的背包有dp[i]中方法
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[bagSize];
    }

    //1049. 最后一块石头的重量 II
    public int lastStoneWeightII(int[] stones) {
        if (stones.length == 1) return stones[0];
        //dp[i] 表示容量为i的背包中物品的最大价值
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {//遍历物品（物品的重量和价值都是stones[i]）
            for (int j = target; j >= stones[i]; j--) {//遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return (sum - dp[target]) - dp[target];
    }

    //416. 分割等和子集
    //使用滚动数组
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 1) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;//奇数不可以均分
        int target = sum / 2;
        //dp[i] 表示：背包容量为i时，背包中物品的最大价值
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {//遍历物品
            for (int j = target; j >= nums[i]; j--) {//遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }

    public boolean canPartition(int[] nums) {
        if (nums.length < 2) return false;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        //dp[i][j] 表示容量为j的背包，放入编号为[0,i]的物品的最大价值
        int[][] dp = new int[nums.length][target + 1];
        //初始化，将0号物品放入背包的所得价值
        for (int i = nums[0]; i < target + 1; i++) {
            dp[0][i] = nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        //System.out.println(dp[nums.length -1][target]);
        return dp[nums.length - 1][target] == target;
    }

    //343. 整数拆分
    public int integerBreak(int n) {
        if (n == 2) return 1;
        //dp[i] 表示拆分i得到最大乘积
        int[] dp = new int[n + 1];
        //初始化
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                int max = Math.max(j * (i - j), dp[i - j] * j);
                dp[i] = Math.max(max, dp[i]);
            }
        }
        return dp[n];
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        for (int i = 1; i < m; i++) {
            dp[0][i] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int[] dp = new int[cost.length + 1];
        //dp[i] 表示爬到下标为 i 的台阶，所需最小花费
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);
        for (int i = 3; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2, sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    //509. 斐波那契数
    public int fib(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
