package algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;//边的数目
    private char[] vertexs;//顶点的数组
    private int[][] matrix;//邻接矩阵
    //最大值表示不存在连边
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex_1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix_1 = {{0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};
        KruskalCase kruskalCase = new KruskalCase(vertex_1, matrix_1);
        //kruskalCase.print();
//        Edge[] edges = kruskalCase.getEdges();
//        System.out.println("排序前：" + Arrays.toString(edges));
//        Arrays.sort(edges);
//        System.out.println("排序后：" + Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    private KruskalCase(char[] vertex, int[][] matrix) {
        //采用复制的方式，初始化。防止改变函数外的数组
        int n = vertex.length;//节点数目
        this.vertexs = new char[n];
        for (int i = 0; i < n; i++) {
            this.vertexs[i] = vertex[i];
        }
        this.matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计连边的数目
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {//上三角形式遍历
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //遍历邻接矩阵
    private void print() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                System.out.printf("%12d", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void kruskal(){
        int index = 0;
        int[] ends = new int[vertexs.length];//存放节点对应的终点
        Edge[] rets = new Edge[edgeNum];//存放最小生成图中的路径
        Edge[] edges = getEdges();
        System.out.println("排序前:" + Arrays.toString(edges));
        Arrays.sort(edges);//升序
        System.out.println("排序后:" + Arrays.toString(edges));
        for (int i = 0; i < edges.length; i++) {
            //获取节点对应的索引
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取节点的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n){//终点不同，不可以形成回路
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
    /**
     * @param ch 传入的节点， 如：'A','B'等
     * @return 返回节点的下标。如果没找到返回下标
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取最小生成图中，节点i的终点
     *
     * @param ends 数组记录各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i    表示传入节点的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //获取图中的所有连边
    private Edge[] getEdges() {
        Edge[] edges = new Edge[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (this.matrix[i][j] != INF) {
                    edges[index++] = new Edge(vertexs[i], vertexs[j], this.matrix[i][j]);
                }
            }
        }
        return edges;
    }

}

class Edge implements Comparable<Edge> {
    char start;//起始节点
    char end;//结束 节点
    int weight;//连边权重

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //方便后续排序
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
