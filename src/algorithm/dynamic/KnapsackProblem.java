package algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//表示物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4;//背包容量
        int n = val.length;//物品个数
        int[][] dp = new int[n + 1][m + 1];

        int[][] path = new int[n + 1][m + 1];//存储放入了哪些物品
        //初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j < w[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    if (dp[i - 1][j - w[i - 1]] + val[i - 1] > dp[i - 1][j]) {
                        dp[i][j] = dp[i - 1][j - w[i - 1]] + val[i - 1];
                        path[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        //输出查看放入了哪些物品
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品放入背包");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
