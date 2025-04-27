package com.dsa.trees.mway;

public class BTreeNode {
  public int[] keys;
  public BTreeNode[] children;
  public BTreeNode parent;
  public boolean leaf = true;
  public int size;

  public BTreeNode(int order) {
    this.children = new BTreeNode[order];
    this.keys = new int[order - 1];
    this.size = 0;
  }

  public boolean isFull() {
    return size == keys.length;
  }

}
