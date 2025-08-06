package com.dsa.backtracking;

import java.util.Arrays;

public class SubsetSum {

  public static void main(String... args) {
    int[] array = new int[]{5, 10, 15, 20, 25};
    int sum = 2;
    boolean[] choosen = new boolean[array.length];
    Arrays.fill(choosen, false);
    int target = 0;
    subsetSum(array, 0, target, sum, choosen);
  }

  public static void subsetSum(int[] array, int index, int target, int sum, boolean[] choosen) {
    if (sum == target) {
      System.out.print("Solution: [");
      for (int i = 0; i < choosen.length; i++) {
        if (choosen[i])
          System.out.print(array[i] + ", ");
      }
      System.out.println("]");
      return;
    }
    if (index == array.length || sum > target) return;

    // by including this item
    choosen[index] = true;
    subsetSum(array, index + 1, target, sum + array[index], choosen);

    // by excluding this item
    choosen[index] = false;
    subsetSum(array, index + 1, target, sum, choosen);
  }

}
