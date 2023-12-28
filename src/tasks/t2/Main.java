package tasks.t2;

import java.util.LinkedHashMap;

public class Main {
  public static void main(String[] args) {
    var arr = new String[10];
    System.out.println(arr.getClass());
    System.out.println(arr.getClass().componentType());
    System.out.println(arr.getClass().arrayType());
    switch ((Object)arr) {
      case String[] s -> System.out.println(1);
      case Integer i -> System.out.println(2);
      default -> System.out.println(3);
    }
  }
}