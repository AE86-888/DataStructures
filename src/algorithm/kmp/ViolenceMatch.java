package algorithm.kmp;


public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        //str2 表示子串
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = str1.length();
        int s2Len = str2.length();
        int i = 0;//指向s1的索引
        int j = 0;//指向s2的索引

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
