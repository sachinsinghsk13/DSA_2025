package com.dsa.trees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class Node {
  public int data;
  @ToString.Exclude
  public Node left;
  @ToString.Exclude
  public Node right;
  public int height;
  public int balanceFactor;

  public Node(int data) {
    this.data = data;
  }

  public boolean isLeafNode() {
    return left == null && right == null;
  }

  public boolean hasLeftChild() {
    return left != null;
  }

  public boolean hasRightChild() {
    return right != null;
  }

  public boolean hasBothChildren() {
    return hasLeftChild() && hasRightChild();
  }
}
