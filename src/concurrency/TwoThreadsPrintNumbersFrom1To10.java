package concurrency;

import java.util.concurrent.*;

/*
Task:
Implement a multi-threaded program where two threads print numbers from 1 to 10 in an alternating fashion.

Thread-1 should print odd numbers.
Thread-2 should print even numbers.
Example Output:

makefile
Copy
Edit
Thread-1: 1
Thread-2: 2
Thread-1: 3
Thread-2: 4
...
Thread-1: 9
Thread-2: 10
 */
public class TwoThreadsPrintNumbersFrom1To10 {

    public static void main(String[] args) {
        new Thread((() -> {
            var i = 1;
            while (true) {
                System.out.printf("%s: %d%n",Thread.currentThread().getName(), i);
                i += 2;
                delay();
            }
        })).start();

        new Thread((() -> {
            var i = 2;
            while (true) {
                System.out.printf("%s: %d%n",Thread.currentThread().getName(), i);
                i += 2;
                delay();
            }
        })).start();
    }

    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
