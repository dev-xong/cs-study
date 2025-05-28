package ex04;

public class ArrayAccessTime {
    public static void main(String[] args) {
        int[] num = new int[1000];

        for(int i = 0; i < num.length; i++) {
            num[i] = i;
        }

        int index = 200; //특정값

        long startTime = System.nanoTime();
        int value = num[index];
        long endTime = System.nanoTime();

        System.out.println("접근한 값 : " + value);
        System.out.println("접근 시간(ns) : " + (endTime - startTime));
    }
}
