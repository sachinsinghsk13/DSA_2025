package com.dsa.dynamic.programming;

import java.util.Arrays;

public class FibonacciNumber {

  public static void main(String[] args) {
    int n = 10;
    long[] table = new long[n + 1];
    Arrays.fill(table, -1);
    long result = fib(n, table);
    System.out.println(result);
  }


  public static long fib(int n, long[] table) {
    if (n == 0 || n == 1) return n;
    if (table[n] == -1) {
      table[n] = fib(n - 1, table) + fib(n - 2, table);
    }
    return table[n];
  }


}
