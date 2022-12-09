package tasks.t2;

import java.util.LinkedHashMap;

public class Main {
  public static void main(String[] args) {
    var textToTest = "This is an example of a long text. Try to find here the first non-repeated character.";
    var map = new LinkedHashMap<Character, Integer>();
    textToTest.chars().forEach(value -> map.compute((char) value, (k, v) -> v == null ? 1 : ++v));
    map.entrySet().stream()
        .filter(entry-> entry.getValue().equals(1)).findFirst()
        .ifPresent((characterIntegerEntry -> System.out.println(characterIntegerEntry.getKey())));
  }
}