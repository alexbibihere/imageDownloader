package org.example;

import java.util.*;
import java.util.stream.Stream;

/**
 * description:Lintcode 练习题
 *
 * @author yandg
 * @date 2025年5月1日14:42:52
 */
public class practiceClass {
    public static void main(String[] args) {
        containsDuplicate(new int[]{1,2,3,4,5,6,7,8,9,10});
    }

    public static int add(int number) {
        int i;
        int sum = 0;
        // write your code here
        for (i = 1; i <= number; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 对数组正整数个位数四舍五入   123%10 =3 123/10 = 12 12%10 = 2 12/10 = 1 1%10 = 1 1/10 = 0
     * @param array
     * @return
     */
    public static int[] fourinfiveout(int[] array)
    {
        int length = array.length;
        // 使用 for 循环遍历数组中的元素
        for (int i = 0; i < length; i++) {
            if (array[i] < 0) {
                continue;
            }
            // 判断数组中正整数的个位数是否大于等于 5
            if ((array[i] % 10) >= 5) {
                // 如果个位大于等于 5 则个位变零，十位进一
                array[i] = (array[i] + 10) - (array[i] % 10);
                System.out.println(array[i]);
            } else {
                // 如果个位小于 5 则个位变零，十位不变
                array[i] = array[i] - (array[i] % 10);
                System.out.println(array[i]);
            }
        }
        return array;
    }

    public static int findMaxNumbInArray(int[] array) {
        // 找到数组中的最大值， 用第一个元素作为最大值，然后遍历后面的元素，如果比第一个元素大，则更新最大值
//        int max = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) {
//                max = array[i];
//            }
//        }
//        return max;
        // 冒泡排序 通过两层循环依次比较相邻的两个元素，将大的元素放到前面 这样第一个元素就是最大的
        for(int i=0;i<array.length;i++){
            for(int j = i+1;j<array.length;j++){
                if(array[i]< array[j]){
                    int m = array[i];
                    array[i] = array[j];
                    array[j] = m;
                }
            }
        }
        System.out.println(Arrays.toString(array));
        return array[0];
    }

    public static String getScoresBylevel(String level){
        switch (level) {
            case "A":
                return "90-100";
            case "B":
                return "80-89";
            case "C":
                return "70-79";
            case "D":
                return "60-69";
            case "E":
                return "0-59";
            default:
                return "Invalid level";
        }
    }
    // Square 和 Circle 中实现 area 方法分别计算正方形和圆形的面积。


    /**
     * 去除字符串的空格然后返回字符串的长度
     * @param str
     * @return
     */
    public static int removeSpaces(String str) {
        return str.replaceAll("\\s", "").length();
    }

    /**
     * 打印求和
     */
    public static void printSum() {
    int n =3;
                int i=0;
        int sum =0;
        while(i<n){
            i++;
            sum+=i;
            System.out.println(sum);
        }
        System.out.println(sum);
    }

    /*
    交换a b的值
     */
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + " b = " + b);
    }
    // 从标准输入中读取两个整数，打印商保留整数部分。
    public static void sd(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a/b);
    }

    /**
     * 复制前五位元素到新的数组中
     */
    public static void copyFirstFive(int[] arr) {
        int[] newarr = new int[5];
        for (int i = 0; i < 5; i++) {
            newarr[i] = arr[i];
        }
        System.out.println(Arrays.toString(newarr));
        }
    /**
     * 复制前五位元素到新的数组中 ,打印 1 2 3 4 5 不带括号
     */
    public static void sdf() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // write your code here
        int[] newarr = new int[5];

        for (int i = 0; i < 5; i++) {
                newarr[i] = arr[i];
        }
        System.out.println(Arrays.toString(newarr));
    }
    /**
     * 数组求和 但是重复的元素只算一次 stream流去重然后求和
     */
    public static int sumArray(int[] arr) {
        int sum = 0;
        // write your code here
        sum = Arrays.stream(arr).distinct().sum();
        return sum;
    }

    /**
     * 筛选集合中偶数到新的集合中
     */
    public static ArrayList<Integer> filterEven(ArrayList<Integer> arr) {
        ArrayList<Integer> newarr = new ArrayList<>();
        // write your code here
        for (int i : arr) {
            if (i % 2 == 0) {
                newarr.add(i);
            }
        }
        System.out.println(newarr.toString());
        return newarr;
    }

    /**
     * 存在重复数字的数组,不用stream
     */
    public static boolean containsDuplicate(int[] nums) {
        // write your code here
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 频率排序,然后返回map
     */
    public static HashMap<Integer, List<Integer>> frequencySort(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // write your code here
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.get(i).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(i, list);
            }
        // 排序
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (int a : list) {
            Collections.sort(map.get(a));
        }
        }
        return map;
    }
}
