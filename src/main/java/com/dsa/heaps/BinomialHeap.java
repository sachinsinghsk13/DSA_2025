package com.dsa.heaps;

import java.util.HashMap;
import java.util.Map;

public class  BinomialHeap {

  private BinomialNode head;
  private Map<Integer, BinomialNode> nodeMap = new HashMap<>();

  public void insert(int data) {
    BinomialHeap heap = new BinomialHeap();
    heap.head = new BinomialNode(data);
    this.nodeMap.put(data, heap.head);
    this.head = union(this, heap).head;
  }

  private BinomialHeap union(BinomialHeap h1, BinomialHeap h2) {
    if (h1.head == null) return h2;
    if (h2.head == null) return h1;
    BinomialHeap h = merge(h1, h2);
    if (h.head == null) return h;

    BinomialNode prev = null, curr = h.head, next = curr.sibling;
    while (next != null) {
      if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
        prev = curr;
        curr = next;
      } else {
        // make next as child of curr
        if (curr.data < next.data) {
          curr.sibling = next.sibling;
          linkBinomialTrees(next, curr);
        } else {
          if (prev == null) {
            h.head = next;
          } else {
            prev.sibling = next;
          }
          linkBinomialTrees(curr, next);
          curr = next;
        }
      }
      next = curr.sibling;
    }
    return h;
  }

  /**
   * merges the root list of two binomial trees.
   * @param h1
   * @param h2
   * @return
   */
  private BinomialHeap merge(BinomialHeap h1, BinomialHeap h2) {
    BinomialHeap h = new BinomialHeap();
    BinomialNode first = h1.head;
    BinomialNode second = h2.head;
    BinomialNode head = null, tail = null;

    while (first != null && second != null) {
      BinomialNode x;
      if (first.degree <= second.degree) {
        x = first;
        first = first.sibling;
      } else {
        x = second;
        second = second.sibling;
      }

      if (head == null) {
        head = x;
      } else {
        tail.sibling = x;
      }
      tail = x;
    }

    if (first != null || second != null) {
      BinomialNode remaining = first != null ? first : second;
      if (head == null) {
        head = remaining;
      } else {
        tail.sibling = remaining;
      }
    }
    h.head = head;
    return h;
  }

  /**
   * Makes binomial tree y as the child of binomial tree z.
   * @param y
   * @param z
   */
  private void linkBinomialTrees(BinomialNode y, BinomialNode z) {
    BinomialNode child = z.child;
    z.child = y;
    y.parent = z;
    y.sibling = child;
    z.degree = z.degree + 1;
  }


  public int findMin() {
    if (head == null) return - 1;
    BinomialNode curr = head;
    int min = Integer.MAX_VALUE;
    while (curr != null) {
      if (curr.data < min) {
        min = curr.data;
      }
      curr = curr.sibling;
    }
    return min;
  }

  public int extractMin() {
    if (head == null) return -1;
    //
    BinomialNode prev = null, curr = head, minNode = head, minPrev = null;
    int min = Integer.MAX_VALUE;
    while (curr != null) {
      if (curr.data < min) {
        minNode = curr;
        minPrev = prev;
        min = minNode.data;
      }
      prev = curr;
      curr = curr.sibling;
    }

    // remove node from root list
    if (minPrev == null) {
      this.head = minNode.sibling;
    } else {
      minPrev.sibling = minNode.sibling;
    }
    BinomialNode c = minNode.child;
    BinomialNode reversedList = null;
    while (c != null) {
      BinomialNode next = c.sibling;
      c.sibling = reversedList;
      c.parent = null;
      reversedList = c;
      c = next;
    }

    BinomialHeap newHeap = new BinomialHeap();
    newHeap.head = reversedList;

    this.head = union(this, newHeap).head;
    minNode.child = null;
    minNode.sibling = null;
    this.nodeMap.remove(minNode.data);
    return minNode.data;
  }

  public void decreaseKey(int current, int newValue) {
    if (newValue > current) throw new IllegalArgumentException("newValue can not greater than current value");
    if (!nodeMap.containsKey(current)) throw new IllegalArgumentException("Value not present in the heap");
    BinomialNode node = nodeMap.get(current);
    node.data = newValue;
    while (node.parent != null) {
      if (node.data < node.parent.data) {
        int temp = node.data;
        node.data = node.parent.data;
        node.parent.data = temp;
      }
      node = node.parent;
    }
  }

  public void deleteKey(int data) {
    decreaseKey(data, Integer.MIN_VALUE);
    extractMin();
  }
}
