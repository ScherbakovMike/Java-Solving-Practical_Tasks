package tasks.t2;

import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    var ob = new Main();
    var t1 = new Thread(()-> {
      try {
        ob.m1();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    var t2 = new Thread(()-> {
      try {
        ob.m1();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    t1.start();
    t2.start();
  }

  public synchronized void m1() throws InterruptedException {
    System.out.println("We are in m1.");
    Thread.sleep(10000);
  }

  public synchronized void m2() {
    System.out.println("We are in m2.");
  }
}

