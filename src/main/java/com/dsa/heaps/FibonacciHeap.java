package com.dsa.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class FibonacciHeap {
  private FibonacciNode min;
  private int n = 0;
  private final Map<Integer, FibonacciNode> nodeLocator = new HashMap<>();

  // O(1)
  public void insert(int key) {
    FibonacciNode node = new FibonacciNode(key);
    if (min == null) {
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
    // Add to map
    this.nodeLocator.put(key, node);
  }

  // O(1)
  public int findMin() {
    if (min == null) {
      throw new NoSuchElementException("Key not found");
    }
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

  public int extractMin() {
    // Empty heap
    if (min == null) {
      throw new NoSuchElementException("Empty Heap");
    }

    FibonacciNode temp = min;
    // Move children to the main root list.
    if (temp.child != null) {
      FibonacciNode ptr = temp.child;
      do {
        FibonacciNode next = ptr.right;

        // remove ptr from child list.
        ptr.left.right = ptr.right;
        ptr.right.left = ptr.left;

        // add node to the main list.
        min.right.left = ptr;
        ptr.right = min.right;
        min.right = ptr;
        ptr.left = min;
        ptr.parent = null;

        ptr = next;
      } while (ptr != temp.child);
    }

    // Remove min node from main root list.
    min.left.right = min.right;
    min.right.left = min.left;

    if (min.left == min) {
      min = null;
    } else {
      // let min point the adjacent node.
      min = min.left;
      consolidateHeap();
    }

    n = n - 1;
    temp.parent = temp.child = temp.left = temp.right = null;
    nodeLocator.remove(temp.key); // remove from map
    return temp.key;
  }

  private void consolidateHeap() {
    int dMax = (int) ((Math.log(n) / Math.log(2)) + 1);

    FibonacciNode[] degreeNodeList = new FibonacciNode[dMax];
    // collect root list node into an array list.
    FibonacciNode start = min;
    List<FibonacciNode> nodes = new ArrayList<>();

    do {
      nodes.add(start);
      start = start.right;
    } while (start != min);

    for (FibonacciNode node : nodes) {
      int d = node.degree;
      while (degreeNodeList[d] != null) {
        FibonacciNode x = degreeNodeList[d];
        degreeNodeList[d] = null;

        if (x.key < node.key) {
          link(node, x);
          node = x;
        } else {
          link(x, node);
        }
        d++;
      }
      degreeNodeList[d] = node;
    }
    min = null;
    for (FibonacciNode fibonacciNode : degreeNodeList) {
      if (fibonacciNode != null) {
        if (min == null) {
          min = fibonacciNode;
          fibonacciNode.left = fibonacciNode;
          fibonacciNode.right = fibonacciNode;
        } else {
          // place new node after the min node
          min.right.left = fibonacciNode;
          fibonacciNode.right = min.right;
          fibonacciNode.left = min;
          min.right = fibonacciNode;

          if (fibonacciNode.key < min.key) {
            min = fibonacciNode;
          }
        }
      }
    }

  }

  // Make y as x's child
  private void link(FibonacciNode y, FibonacciNode x) {
    // remove y from main root list.
    y.left.right = y.right;
    y.right.left = y.left;

    // if x has no child then add this node as child
    if (x.child == null) {
      x.child = y;
      y.left = y;
      y.right = y;
    } else {
      x.child.left.right = y;
      y.right = x.child.right;
      x.child.right = y;
      y.left = x.child;
    }
    y.parent = x;
    x.degree = x.degree + 1;
  }

  public void decreaseKey(int key, int newValue) {
    if (newValue > key) {
      throw new RuntimeException("Key is greater than current key");
    }
    if (!nodeLocator.containsKey(key)) {
      throw new RuntimeException("Key not found");
    }
    FibonacciNode ptr = nodeLocator.get(key);
    ptr.key = newValue;
    FibonacciNode parent = ptr.parent;
    if (parent != null && ptr.key < parent.key) {
      cut(ptr, parent);
      cascadingCut(parent);
    } if (min == null || newValue < min.key) {
      min = ptr;
    }
    // update map
    nodeLocator.remove(key);
    nodeLocator.put(newValue, ptr);
  }

  private void cascadingCut(FibonacciNode parent) {
    FibonacciNode g = parent.parent;
    if (g != null) {
      if (!parent.mark) {
        parent.mark = true;
      } else {
        cut(parent, g);
        cascadingCut(g);
      }
    }
  }

  private void cut(FibonacciNode ptr, FibonacciNode parent) {
    // if it's single child
    if (ptr.left == ptr) {
      parent.child = null;
    } else {
      // remove this node from child root list.
      ptr.left.right = ptr.right;
      ptr.right.left = ptr.left;
      if (parent.child == ptr) { // let parent point to a sibling child.
        parent.child = ptr.left;
      }
    }

    parent.degree = parent.degree - 1;
    ptr.parent = null;

    // insert into root list.
    min.right.left = ptr;
    ptr.right = min.right;
    min.right = ptr;
    ptr.left = min;

    // mark node as false.
    ptr.mark = false;
  }



}
