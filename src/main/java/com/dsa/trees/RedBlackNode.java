package com.dsa.trees;

public class RedBlackNode {

  public int data;
  public RedBlackNode left;
  public RedBlackNode right;
  public RedBlackNode parent;
  public Color color = Color.RED;

  public RedBlackNode(int data) {
    this.data = data;
  }

  public RedBlackNode(int data, RedBlackNode parent) {
    this.data = data;
    this.parent = parent;
  }

  public RedBlackNode parent() {
    return parent;
  }

  public RedBlackNode grandparent() {
    if (parent != null) return parent.parent;
    return null;
  }

  public RedBlackNode uncle() {
    RedBlackNode grandparent = grandparent();
    if (grandparent == null) return null;
    if (parent == grandparent.left)
      return grandparent.right;
    else
      return grandparent.left;
  }

  public RedBlackNode sibling() {
    if (parent == null) return null;
    if (parent.left == this) return parent.right;
    return parent.left;
  }

  public boolean hasLeftChild() {
    return left != null;
  }

  public boolean hasRightChild() {
    return right != null;
  }

  public boolean hasBothChild() {
    return left != null && right != null;
  }

  public boolean isLeftChild() {
    return parent != null && this == parent.left;
  }

}
