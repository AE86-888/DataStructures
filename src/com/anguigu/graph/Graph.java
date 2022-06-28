package com.anguigu.graph;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private static ArrayList<String> vertexList;//存储顶点集合；
    private int[][] edges;//邻接矩阵
    private int numOfEdge;//边的数目
    private boolean[] isVisited;//记录节点是否被访问

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        //String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : vertexs) {
            vertexList.add(vertex);
        }

        //添加连边
//        graph.addEdge(0, 1, 1);
//        graph.addEdge(0, 2, 1);
//        graph.addEdge(1, 2, 1);
//        graph.addEdge(1, 3, 1);
//        graph.addEdge(1, 4, 1);

        graph.addEdge(0,1,1);
        graph.addEdge(0,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);
        graph.addEdge(3,7,1);
        graph.addEdge(4,7,1);
        graph.addEdge(2,5,1);
        graph.addEdge(2,6,1);
        graph.addEdge(5,6,1);

        graph.list();
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先搜索");
        graph.bfs();
    }

    public Graph(int n) {
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdge = 0;
    }

    //得到当前节点的第一个邻居节点
    public int getFistNode(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0 && !isVisited[i]) {
                return i;
            }
        }
        return -1;
    }


    //得到当前节点的第一邻居节点的邻居节点
    public int getNextNode(int index, int first) {
        for (int i = first + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
    }

    //广度优先搜索
    public void bfs(int index) {
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getVertexByIndex(index) + "->");
        isVisited[index] = true;
        queue.addLast(index);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            int w = getFistNode(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getVertexByIndex(w) + "->");
                    queue.addLast(w);
                    isVisited[w] = true;
                }
                w = getFistNode(u);
            }
        }
    }

    //深度优先搜索
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    //深度优先搜索
    public void dfs(int index) {
        System.out.print(getVertexByIndex(index) + ", ");
        isVisited[index] = true;
        //找到当前节点的第一个邻居节点
        int w = getFistNode(index);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(w);
            }
            w = getFistNode(w);
        }
    }

    //根据索引获取顶点
    public String getVertexByIndex(int i) {
        return vertexList.get(i);
    }

    //得到连边数目
    public int getNumOfEdge() {
        return numOfEdge;
    }

    //遍历图
    public void list() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //添加节点
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加连边
     *
     * @param vertex1 节点1
     * @param vertex2 节点2
     * @param weight  权重
     */
    public void addEdge(int vertex1, int vertex2, int weight) {
        edges[vertex1][vertex2] = weight;
        edges[vertex2][vertex1] = weight;
        numOfEdge++;
    }
}
