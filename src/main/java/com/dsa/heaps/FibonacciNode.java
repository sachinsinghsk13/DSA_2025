package com.dsa.heaps;

public class FibonacciNode {
  FibonacciNode parent, left, right, child;
  boolean mark;
  int degree;
  int key;

  public FibonacciNode(int key) {
    this.key = key;
    this.left = this;
    this.right = this;
    this.degree = 0;
    this.mark = false;
  }


}
