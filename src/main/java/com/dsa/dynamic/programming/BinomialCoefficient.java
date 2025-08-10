package com.dsa.dynamic.programming;

import java.util.Arrays;

public class BinomialCoefficient {
  public static void main(String[] args) {
    int n = 8;
    int k = 4;
    int[][] table = new int[n + 1][k + 1];
    for (int[] row : table) {
      Arrays.fill(row, -1);
    }
    System.out.println(combination(n, k, table));
  }


  public static int factorial(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return n * factorial(n - 1);
  }

  public static int combination(int n, int k, int[][] table) {
    if (n == 0 || k == n || k == 0) {
      return 1;
    }
    if (table[n][k] == -1) {
      table[n][k] = combination(n - 1, k - 1, table) + combination(n - 1, k, table);
    }
    return table[n][k];
  }

}
