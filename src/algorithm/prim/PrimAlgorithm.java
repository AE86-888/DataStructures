package algorithm.prim;

import com.anguigu.graph.Graph;
import com.sun.org.apache.xml.internal.security.Init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{{10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph mGraph = new MGraph(data.length, data, weight);
        mGraph.showWeight();
        int prim = mGraph.prim(mGraph, 5);
        System.out.println("==========");
        System.out.println("最小权值：" + prim);
    }
}

class MGraph {
    int verxs;//图中节点个数
    char[] data;//顶点的表示符号
    int[][] weight;//邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
    }

    public MGraph(int verxs, char[] data, int[][] weight) {
        this.verxs = verxs;
        this.data = data;
        this.weight = weight;
    }

    //遍历weight
    public void showWeight() {
        for (int[] w : weight) {
            System.out.println(Arrays.toString(w));
        }
    }
    //编写prim算法，得到最小生成树

    /**
     * @param graph 图对象
     * @param v     从顶点v开始创建最小生成树
     * @return 返回最小最小权重之和
     */
    public int prim(MGraph graph, int v) {
        boolean[] isVisited = new boolean[graph.verxs];//标记是否访问了某节点
        ArrayList<Integer> uList = new ArrayList<>(graph.verxs);
        isVisited[v] = true;
        uList.add(v);
        int allWeight = 0;//最终的最小权值之和
        while (uList.size() < graph.verxs) {
            int minWeight = 10000;//表示一个很大的数
            int min_i = -1;//记录最小权值对应的索引
            int min_j = -1;//记录最小权值对应的索引
            for (int i = 0; i < uList.size(); i++) {
                for (int j = 0; j < graph.weight[i].length; j++) {
                    if (isVisited[uList.get(i)] && !isVisited[j] && graph.weight[uList.get(i)][j] < minWeight) {
                        minWeight = graph.weight[uList.get(i)][j];
                        min_i = uList.get(i);
                        min_j = j;
                    }
                }
            }
            if (minWeight < 10000) {
                isVisited[min_j] = true;
                uList.add(min_j);
                allWeight += minWeight;
                System.out.println("路径" + graph.data[min_i] + "<->" + graph.data[min_j] + "权重：" + graph.weight[min_i][min_j]);
            }
        }
        return allWeight;
    }
}