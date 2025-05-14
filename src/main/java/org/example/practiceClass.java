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
        System.out.println(frequencySortMap(new int[]{ 4, 6, 2, 2, 6, 4, 4, 3, 3, 4, 6, 6 }));
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
    /**
     * 给无序的map排序 按key的字典排序
     */
    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        // write your code here
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 频率排序,返回map， key为出现的次数，value为对应次数的集合
     */
    public static Map<Integer, List<Integer>> frequencySortMap(int[] nums) {
//        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        for (int num : nums) {
//            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
//        }
//
//        // 2. 按出现次数分组
//        Map<Integer, List<Integer>> result = new HashMap<>();
//        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
//            int num = entry.getKey();
//            int frequency = entry.getValue();
//
//            if (!result.containsKey(frequency)) {
//                result.put(frequency, new ArrayList<>());
//            }
//            result.get(frequency).add(num);
//        }
//
//        // 3. 对每组内的数字进行排序
//        for (List<Integer> numbers : result.values()) {
//            Collections.sort(numbers);
//        }
//
//        // 4. 按出现次数从大到小排序
//        List<Map.Entry<Integer, List<Integer>>> sortedEntries =
//                new ArrayList<>(result.entrySet());
//
//        Collections.sort(sortedEntries, new Comparator<Map.Entry<Integer, List<Integer>>>() {
//            @Override
//            public int compare(Map.Entry<Integer, List<Integer>> e1,
//                               Map.Entry<Integer, List<Integer>> e2) {
//                return e2.getKey().compareTo(e1.getKey()); // 降序排序
//            }
//        });
//
//        // 5. 构建有序的结果Map
//        Map<Integer, List<Integer>> finalResult = new LinkedHashMap<>();
//        for (Map.Entry<Integer, List<Integer>> entry : sortedEntries) {
//            finalResult.put(entry.getKey(), entry.getValue());
//        }
//
//        return finalResult;
        // write your code here
        //排序
        Arrays.sort(nums);
        //计算出现次数
        //计数出现次数表key为数字，value为出现次数
        Map<Integer, Integer> countMap = new LinkedHashMap<>();
        Map<Integer, List<Integer>> resultMap=new TreeMap<>(Comparator.reverseOrder());
        for (int i : nums) {
            countMap.compute(i, (k, num) -> num == null ? 1 : num + 1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            //判断map中是否存在对应key,不存在创建，存在直接根据key值添加
            if(resultMap.containsKey(entry.getValue())){
                resultMap.get(entry.getValue()).add(entry.getKey());
            }else{
                List<Integer> result=new ArrayList<>();
                resultMap.put(entry.getValue(),result);
                result.add(entry.getKey());
            }
        }
        return resultMap;
    }

    /*
    * 字符串去重保持原来顺序,用set实现
     */
    public static String removeDuplicates(String str) {
        Set<Character> set = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        for (char c : set) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 两个数组合并成一个数组，要求合并后的数组是按顺序排列的 ,treeset 实现
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        Set<Integer> set = new TreeSet<>();
        for (int i : arr1) {
            set.add(i);
        }
        for (int i : arr2) {
            set.add(i);
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (int i : set) {
            result[index++] = i;
        }
        return result;
    }

    /**
     * 写一个线程
     */
    public static void thread(){
        Thread thhread = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("hello world");
            }
        });
        thhread.start();
    }

    /*
    寻找数组最大值
     */
    public int maxNum(List<Integer> nums) {
        // write your code here 取第一个数 循环遍历比对 得到最大值
        int index = nums.get(0);
        for(int i : nums){
            if(index<i){
                index = i;
            }
        }
        return index;
    }

    /*
    * 数组中出现次数最多的数字
     */
    public int maxNum2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int max = 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    /*
    * 斐波那契数列 0 1 1 2 3 5
     */
    public int fibonacci(int n) { // 0 1 1 2 3 5
        if (n == 0) {    // 0        a b c
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * 最高频率的ip ["192.168.1.1","192.118.2.1","192.168.1.1"]
     */
    public static String highestFrequencyIP(String[] ips) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String ip : ips) {
            String[] arr = ip.split("\\.");
            int sum = 0;
            for (String s : arr) {
                sum = sum * 256 + Integer.parseInt(s);
            }
            map.put(ip, map.getOrDefault(ip, 0) + 1);
        }
        int max = 0;
        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;  next = null; }
    }
    /**
     * 删除排序链中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }

        ListNode p1 = head;


        while(p1.next != null){
            if (p1.val == p1.next.val){
                p1.next = p1.next.next;// 如果相邻两个元素相同，则删除第二个元素，指向第三个元素
            } else {
                p1 = p1.next;
            }
        }
        return head;
    }
    /**
     * 最小子数组
     */
    public int minSubArrayLen(List<Integer> nums) {
        // 循环遍历两次
        int n = nums.size();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                int sum = 0;
                for(int k=i;k<j;k++){
                    sum += nums.get(k);
                }
                if(sum == 0){
                    return j-i;
                }
            }
        }
        return 0;
    }
    /**
     * 主元素，找到数组中出现次数最多的元素，并且这个元素在数组中出现的次数大于数组长度的一半
     */
    public int majorityNumber(List<Integer> nums) {
        // write your code here 统计hashmap value大于count的数字
        int count =nums.size()/2;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int i:nums){
            hashMap.put(i,hashMap.getOrDefault(i,0)+1);
        }
        int result =0;
        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet() ){
            if(entry.getValue()>count){
                result = entry.getKey();
            }
        }
        return result;
    }
    /**
     * 使用lambda表达式排序
     */
    public void sortByLengh(String[] strArr){
        Arrays.stream(strArr)
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);
    }
    public class Person {
        String name;
        int age;
        int number;
        public Person(String name, int age, int number) {
            this.name = name;
            this.age = age;
            this.number = number;
        }
        public String toString() {
            return "number = " + number + ", name = " + name + ", age = " + age;
        }

    }
    /**
     * 乘车按序就坐 ，正常情况下乘客按序列号 number 的升序顺序排队就座，当乘客中有年龄 age 小于 5 岁（不含 5 岁）的幼儿乘客或是年龄 age 大于 60 岁（不含 60 岁）的老年人乘客时，优先按序就座。现在给你一个乘客列表
     */
    public void carPooling(List<Person> list) {
        // write your code here  两个列表分离优先级高 和正常人员
        List<Person> newList = new ArrayList<>();
        List<Person> normalList = new ArrayList<>();
        for(Person person:list){
            if(person.age<5 || person.age>60){
                newList.add(person);
            }else{
                normalList.add(person);
            }
        }
        // 对list按照number排序
        Collections.sort(normalList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.number - o2.number;
            }
        });
        // 然后把list添加到resultList中
        List<Person> resultList = new ArrayList<>(newList);
        resultList.addAll(normalList);
        for(Person p:resultList){
            System.out.println(p);
        }
    }
}
