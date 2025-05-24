package ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ThreadSafe {

    public static void main(String[] args) throws InterruptedException {

        //ArrayList Vector 생성
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> vector = new Vector<>();

        Thread arrayListThread1 = new Thread(() -> addElements(arrayList, 0, 5000));
        Thread arrayListThread2 = new Thread(() -> addElements(arrayList, 5000 ,1000));

        Thread vectorThread1 = new Thread(() -> addElements(arrayList, 0 ,5000));
        Thread vectorThread2 = new Thread(() -> addElements(arrayList, 5000 ,1000));

        arrayListThread1.start();
        arrayListThread2.start();

        vectorThread1.start();
        vectorThread2.start();

        arrayListThread1.join();
        arrayListThread2.join();

        vectorThread1.join();
        vectorThread2.join();

        System.out.println("ArrayList size: " + arrayList.size()); //레이스 컨디션 발생
        System.out.println("Vector size : " + vector.size()); //결과 일정 유지 0
    }

    private static void addElements(List<Integer> list, int start, int end) {

        for(int i = start; i <= end; i++) {
            list.add(i);
        }
    }
}
