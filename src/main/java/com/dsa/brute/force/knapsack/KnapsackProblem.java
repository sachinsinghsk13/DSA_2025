package com.dsa.brute.force.knapsack;

import org.apache.commons.lang3.tuple.Pair;
import java.util.Arrays;
import java.util.List;

public class KnapsackProblem {

  public static int knapsack(int[] weight, int[] profit, int capacity) {
    int n = weight.length;
    int subsets = 1 << n;
    int maxProfit = Integer.MIN_VALUE;
    boolean[] pickedItems = new boolean[n];
    for (int mask = 0; mask < subsets; mask++) {
      int totalProfit = 0;
      int totalWeight = 0;
      boolean[] chosenItems = new boolean[n];
      for (int itemIndex = 0; itemIndex < n; itemIndex++) {
        if ((mask & (1 << itemIndex)) != 0) {
          totalProfit += profit[itemIndex];
          totalWeight += weight[itemIndex];
            chosenItems[itemIndex] = true;
        }
      }

      if (totalWeight > capacity)
        continue;
      if (totalProfit > maxProfit) {
        maxProfit = totalProfit;
        pickedItems = chosenItems;
      }
    }
    System.out.println(Arrays.toString(pickedItems));
    return maxProfit;
  }

}
