package concurrency;

import static java.lang.Thread.sleep;

public class Deadlock {

    private static Integer resource1 = 0;
    private static Integer resource2 = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                synchronized (resource2) {
                    System.out.println("Thread 1 has acquired locks.");
                    try {
                        sleep(100000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (resource2) {
                synchronized (resource1) {
                    System.out.println("Thread 2 has acquired locks.");
                    try {
                        sleep(100000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
