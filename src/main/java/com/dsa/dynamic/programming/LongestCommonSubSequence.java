package com.dsa.dynamic.programming;

import java.util.Arrays;

public class LongestCommonSubSequence {

  public static void main(String[] args) {
    String x = "AGCTAGCTAGCTAGCTAGCTA";
    String y = "GCTAGCTAGCTAGCTAGCTAG";

    int i = x.length() - 1;
    int j = y.length() - 1;

    int[][] dp = new int[x.length()][y.length()];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    int result = solve(x, y, i, j, dp);
    System.out.println("Result : " + result);
  }

  private static int solve(String x, String y, int i, int j, int[][] dp) {
    if (i < 0 || j < 0) {
      return 0;
    }

    if (dp[i][j] != -1) {
      return dp[i][j];
    }

    int result;
    // match
    if (x.charAt(i) == y.charAt(j)) {
      result = 1 + solve(x, y, i - 1, j - 1, dp);
    } else {
      // Not Match
      result = Math.max(solve(x, y, i - 1, j, dp), solve(x, y, i, j - 1, dp));
    }
    dp[i][j] = result;
    return result;
  }


}
