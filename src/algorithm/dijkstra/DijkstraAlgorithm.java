package algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static final int N = 65535;
    static int[] dist;//记录开始节点到各节点的最短距离
    static int[] pre;//记录当前下标对应的节点的前驱节点
    static int[] already;//记录已经访问过的节点

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex, matrix);
        dj(graph, 6);
        System.out.println("dist=" + Arrays.toString(dist));
        System.out.println("pre=" + Arrays.toString(pre));
        System.out.println("already=" + Arrays.toString(already));
    }

    /**
     * 计算开始节点 v 到剩下各顶点的最短路径
     *
     * @param graph 图对象
     * @param v     开始节点
     */
    public static void dj(Graph graph, int v) {
        already = new int[graph.n];
        dist = new int[graph.n];
        pre = new int[graph.n];
        //1、初始化
        already[v] = 1;//标记节点v 已经访问
        for (int i = 0; i < graph.n; i++) {
            dist[i] = graph.matrix[v][i];
            if (graph.matrix[v][i] < N) {
                pre[i] = v;
            } else {
                pre[i] = -1;//-1表示没有前驱节点
            }
        }
        pre[v] = -1;

        //循环n-1次，访问其余n-1个节点
        for (int i = 0; i < graph.n - 1; i++) {
            int min = N;
            int min_index = 0;//记录最小值的索引

            //找到dist 数组中的最小值
            for (int j = 0; j < graph.n; j++) {
                if (already[j] == 0 && dist[j] < min) {
                    min = dist[j];
                    min_index = j;
                }
            }
            already[min_index] = 1;

            //更细最短路径
            for (int j = 0; j < graph.n; j++) {
                if (already[j] == 0 && dist[j] > dist[min_index] + graph.matrix[min_index][j]) {
                    dist[j] = dist[min_index] + graph.matrix[min_index][j];
                    pre[j] = min_index;//更新前驱节点
                }
            }
        }
    }

    //输出最短路径

    /**
     *
     * @param v 开始节点
     */
    public void djShow(int v,Graph graph) {
        for (int i = 0; i < graph.n; i++) {
            if (pre[i] == 0){}
        }
    }
}

class Graph {
    char[] vertex;
    int[][] matrix;//邻接矩阵
    int n;//表示节点的个数

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.n = vertex.length;
    }

    public void showGraph() {
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }
}