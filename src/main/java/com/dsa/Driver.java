package com.dsa;

import com.dsa.brute.force.knapsack.KnapsackProblem;
import com.dsa.brute.force.npuzzle.Board;
import com.dsa.brute.force.npuzzle.XPuzzleProblem;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Driver {
  public static void main(String[] args) {
    int[] weight = new int[]{2, 1, 3, 2, 5};
    int[] profit = new int[]{12, 10, 20, 15, 25};
    int max = KnapsackProblem.knapsack(weight, profit, 10);
    System.out.println("Max profit is " + max);
  }
}


