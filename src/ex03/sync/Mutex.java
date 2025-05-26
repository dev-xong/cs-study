package ex03.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mutex {

    static int sharedData = 0; // 공유 데이터
    static Lock lock = new ReentrantLock(); // 락 선언

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Increment());
        Thread thread2 = new Thread(new Decrement());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //공유 데이터 최종 값
        System.out.println("Final value of sharedData : " + sharedData);
    }

    static class Increment implements Runnable {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lock.lock(); //lock 획득
                try {
                    sharedData++; // 공유데이터 증가
                } finally {
                    lock.unlock(); //lock 해제
                }
            }
        }
    }

    static class Decrement implements Runnable {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lock.lock(); //lock 획득
                try {
                    sharedData--; // 공유데이터 감소
                } finally {
                    lock.unlock(); //lock 해제
                }
            }
        }
    }
}