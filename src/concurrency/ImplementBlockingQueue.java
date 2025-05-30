package concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/*
Your Goal: Implement this class
public class BoundedBlockingQueue<T> {
    public BoundedBlockingQueue(int capacity) { }

    public void enqueue(T item) throws InterruptedException { }

    public T dequeue() throws InterruptedException { }

    public int size() { }
}
 */
public class ImplementBlockingQueue {
    public static void main(String[] args) {
        var bq = new BoundedBlockingQueue<Integer>(2);
        Runnable producer = ()->{
            for(int i = 0; i<10;i++) {
                try {
                    bq.enqueue(1);
                    System.out.println("Added 1");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = ()->{
            for(int i = 0; i<10;i++) {
                try {
                    System.out.println("Consumed = "+bq.dequeue());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

            new Thread(producer).start();

            new Thread(consumer).start();

    }
}

class BoundedBlockingQueue<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        try {
            lock.lock();
            if (queue.size() == capacity) {
                notFull.await();
            }
            queue.offer(item);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        try {
            lock.lock();
            if (queue.isEmpty()) {
                notEmpty.await();
            }
            var item = queue.poll();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        try {
            lock.lock();
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}