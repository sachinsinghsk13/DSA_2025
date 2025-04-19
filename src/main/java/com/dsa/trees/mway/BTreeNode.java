package com.dsa.trees.mway;

public class BTreeNode {

  int[] keys;
  BTreeNode[] children;
  BTreeNode parent;
  boolean isLeafNode;
  int totalKeys;
  final int order;

  public BTreeNode(int order) {
    this.order = order;
    keys = new int[this.order];
    children = new BTreeNode[this.order + 1];
    isLeafNode = true; // new node is always leaf
  }


}
