package algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    int[][] dist;//存储距离矩阵
    int[][] pre;//记录节点的前驱节点
    public static final int N = 65535;

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

        FloydAlgorithm floydAlgorithm = new FloydAlgorithm();
        floydAlgorithm.floy(graph);
        floydAlgorithm.show();
    }

    public void floy(Graph graph) {
        dist = new int[graph.n][graph.n];
        pre = new int[graph.n][graph.n];
        //初始化
        for (int i = 0; i < graph.n; i++) {
            for (int j = 0; j < graph.n; j++) {
                dist[i][j] = graph.matrix[i][j];
            }
            Arrays.fill(pre[i], i);
        }

        for (int k = 0; k < graph.n; k++) {//中间节点 k
            for (int i = 0; i < graph.n; i++) {//起始节点 i
                for (int j = 0; j < graph.n; j++) {//终点 j
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

    //打印 矩阵dist 和 pre
    public void show() {
        for (int[] d : dist) {
            System.out.println(Arrays.toString(d));
        }
        System.out.println("===========");
        for (int[] p : pre) {
            System.out.println(Arrays.toString(p));
        }
    }
}

class Graph {
    char[] vertex;//节点
    int n;//节点数
    int[][] matrix;//邻接矩阵

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.n = vertex.length;
    }
}
