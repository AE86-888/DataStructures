package com.anguigu.huffmancode;

import java.util.*;

public class HuffmanTree {
    public static void main(String[] args) {
        //有bug  还未解决
        String content = "i like like like java do you like a java";
        //String content = "hello wold";
        byte[] bytes = content.getBytes();
//        List<Node> nodes = getNodes(bytes);
//        Node root = creatHuffmanTree(nodes);
//        //preOrder(root);
//        getCodes(root, "", new StringBuilder());
//        //System.out.println("赫夫曼编码：" + huffmanCode);
//        byte[] huffmanCodeByte = huffmanCodeByte(bytes, huffmanCode);
//        System.out.println(Arrays.toString(huffmanCodeByte));
//        System.out.println("赫夫曼编码字节数组的长度：" + huffmanCodeByte.length);
        byte[] huffmanByte = huffmanByte(bytes);
        System.out.println("赫夫曼编码字节数组的长度：" + Arrays.toString(huffmanByte) + "数组长度：" + huffmanByte.length);

        //解码
        byte[] bytes1 = deCode(huffmanByte, huffmanCode);
        System.out.println("解码后：" + new String(bytes1));
    }

    //将byte转换成二进制字符串

    /**
     * 将byte类型的数，转换成二进制字符串
     *
     * @param by   byte 类型
     * @param flag 标志是否需要补充高位
     * @return by 对应的二进制字符串
     */
    private static String byteToString(byte by, boolean flag) {
        int temp = by;// 将 byte 转换成 int

        //如果是正数，存在补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {//如果最后一个byte类型的数，对应的二进制字符串不满8位,直接返回全部；
            return str;
        }
    }

    //赫夫曼编码解码

    /**
     * 根据赫夫曼编码，解码
     *
     * @param bytes       赫夫曼编码对应的字节数组
     * @param huffmanCode 赫夫曼编码 map，（key 是原本字符的字节，value 是对应的赫夫曼码）
     * @return 解码后的字节数组
     */
    private static byte[] deCode(byte[] bytes, HashMap<Byte, String> huffmanCode) {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, Byte> map = new HashMap<>();//存放huffmanCode 中 key与value反转后的结果
        ArrayList<Byte> list = new ArrayList<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i != bytes.length - 1);
            stringBuilder.append(byteToString(bytes[i], flag));
        }
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte by = null;
            boolean flag = true;
            while (flag) {
                String str = stringBuilder.substring(i, i + count);
                by = map.get(str);
                if (by == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(by);
            i += count;
        }
        byte[] bytes1 = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes1[i] = list.get(i);
        }
        return bytes1;
    }

    private static HashMap<Byte, String> huffmanCode = new HashMap<>();

    //封装几个方法到一起
    private static byte[] huffmanByte(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node root = creatHuffmanTree(nodes);
        getCodes(root, "", new StringBuilder());
        byte[] huffmanCodeByte = huffmanCodeByte(bytes, huffmanCode);
        return huffmanCodeByte;
    }

    /**
     * 得到的赫夫曼编码的对应的字节数组，8位对应一个byte
     *
     * @param bytes       原字符串对应的字节数组
     * @param huffmanCode 赫夫曼编码
     * @return
     */
    private static byte[] huffmanCodeByte(byte[] bytes, HashMap<Byte, String> huffmanCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte by : bytes) {
            stringBuilder.append(huffmanCode.get(by));
        }
        int len = 0;
        //等价于 len = stringBuilder.length() + 7 / 8;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//huffmanCodeBytes 数组的索引
        for (int i = 0; i < stringBuilder.length(); i = i + 8) {
            if (i + 8 < stringBuilder.length()) {
                huffmanCodeBytes[index++] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            } else {
                huffmanCodeBytes[index++] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            }
        }
        return huffmanCodeBytes;
    }


    /**
     * 根据赫夫曼树，生成赫夫曼编码
     *
     * @param node          传入赫夫曼树根节点
     * @param code          0 表示左子节点，1 表示右子节点
     * @param stringBuilder 之前生成的部分赫夫曼编码
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {//非叶子节点
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCode.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root == null) {
            System.out.println("赫夫曼树为空，不可遍历");
        } else {
            root.preOrder();
        }
    }

    /**
     * 创建赫夫曼树
     *
     * @param nodes
     * @return 返回赫夫曼树的根节点
     */
    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * @param bytes 字节数组
     * @return 返回字节数组生成的 List<Node>
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> hashmap = new HashMap<Byte, Integer>();
        for (byte aByte : bytes) {
            Integer count = hashmap.get(aByte);
            if (count == null) {
                hashmap.put(aByte, 1);
            } else {
                hashmap.put(aByte, count + 1);
            }
        }
        Set<Map.Entry<Byte, Integer>> entries = hashmap.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    public Byte data;
    public int weight;//权重，表示出现的次数
    public Node left;
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}