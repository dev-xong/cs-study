package ex03.sync;

import java.util.concurrent.Semaphore;

public class Sem {

    // 공유 자원 3개
    static Semaphore semaphore = new Semaphore(3, true); //공정성 보장

    //반복문을 통해 PrintJob 스레드 5개 생성
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new PrintJob(i));
            thread.start();
        }
    }

    static class PrintJob implements Runnable {
        int userId;

        public PrintJob(int id) {
            this.userId = id;
        }

        public void run() {
            synchronized(System.out) {
                System.out.println("User " + userId + " is trying to acquire a printer...");
            }


            try {
                semaphore.acquire();
                synchronized(System.out) {
                    System.out.println("User " + userId + " acquired a printer.");
                }

                // 프린트 작업 (딜레이 모의)
                // 0~3초 사이 랜덤한 시간동안 프린트 중이라고 가정
                Thread.sleep((int) (Math.random() * 3000));

                synchronized(System.out) {
                    System.out.println("User " + userId + " finished printing.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); //자원 반납 -> 대기중인 스레드에게 자원 전달
                synchronized(System.out) {
                    System.out.println("User " + userId + " released a printer.");
                }
            }
        }

    }
}