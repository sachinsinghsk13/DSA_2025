package com.dsa.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetGeneration {

  public static void main(String[] args) {
    int[] array = new int[] {1, 2};
    List<Integer> subset = new ArrayList<>();
    List<Integer[]> powerSet = new ArrayList<>();
    powerSet(array, 0, subset, powerSet);

    for (Integer[] ss : powerSet) {
      System.out.println(Arrays.toString(ss));
    }
  }

  private static void powerSet(int[] array, int index, List<Integer> subset, List<Integer[]> powerSet) {
    if (index == array.length) {
      powerSet.add(subset.toArray(new Integer[]{}));
      return;
    }

    // pick
    subset.add(array[index]);
    powerSet(array, index + 1, subset, powerSet);

    // not pick
    subset.removeLast();
    powerSet(array, index + 1,subset, powerSet);

  }

}
