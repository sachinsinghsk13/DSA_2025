package com.dsa.heaps;

import java.util.NoSuchElementException;

public class FibonacciHeap {
  private FibonacciNode min;
  private int n = 0;

  // O(1)
  public void insert(int key) {
    FibonacciNode node = new FibonacciNode(key);
    if (min == null)  {
      min = node;
    } else {
      node.left = min;
      node.right = min.right;
      min.right.left = node;
      min.right = node;
    }
    if (node.key < min.key) {
      min = node;
    }
    n++;
  }

  // O(1)
  public int findMin() {
    if (min == null) throw  new NoSuchElementException("Key not found");
    return min.key;
  }

  // O(1)
  public FibonacciHeap union(FibonacciHeap h1, FibonacciHeap h2) {
    FibonacciHeap h = new FibonacciHeap();
    if (h1.min == null) {
      h.min = h2.min;
      h.n = h2.n;
      return h;
    } else if (h2.min == null) {
      h.min = h1.min;
      h.n = h1.n;
      return h;
    }


    // concat the root list
    FibonacciNode h1Right = h1.min.right;
    FibonacciNode h2Left = h2.min.left;
    h1.min.right = h2.min;
    h2.min.left = h1.min;
    h1Right.left = h2Left;
    h2Left.right = h1Right;
    // update the min pointer
    if (h1.min.key < h2.min.key) {
      h.min = h1.min;
    } else {
      h.min = h2.min;
    }
    // update the total nodes
    h.n = h1.n + h2.n;

    return h;
  }
}
