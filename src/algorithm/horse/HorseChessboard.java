package algorithm.horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    //马踏棋盘算法
    private static int[][] chessBoard;//表示棋盘
    private static int X;//表示列
    private static int Y;//表示行
    private static boolean[] isVististed;//表示是否访问
    private static boolean finished;//表示是否完成任务

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        isVististed = new boolean[X * Y];
        chessBoard = new int[X][Y];
        int row = 6;
        int col = 1;
        long start = System.currentTimeMillis();
        chess(chessBoard, row - 1, col - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("时间：" + (end - start) + "毫秒");

        //查看最后结果
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @param chessBoard 棋盘
     * @param row        起点所在行
     * @param col        起点所在列
     * @param step       步数，开始默认为 1
     */
    public static void chess(int[][] chessBoard, int row, int col, int step) {
        isVististed[row * X + col] = true;
        chessBoard[row][col] = step;
        Point p1 = new Point(col, row);
        ArrayList<Point> ps = next(p1);
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//下一步走的位置
            if (!isVististed[p.y * X + p.x]) {
//                step++;
//                isVististed[p.y * X + p.x] = true;
                chess(chessBoard, p.y, p.x, step + 1);
            }
        }
        if (!finished && step < X * Y) {
            isVististed[row * X + col] = false;
            chessBoard[row][col] = 0;
        } else {
            finished = true;
        }
    }

    //排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return count1 - count2;
            }
        });
    }

    //根据当前位置，获取下一步可以走的位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断0位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断1位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断2位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断3位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断4位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断5位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断6位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断7位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}
