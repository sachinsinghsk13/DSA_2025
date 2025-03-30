package com.dsa.trees;

public class TreeUtils {
  public static int height(Node root) {
    if (root == null) return 0;
    return Math.max(height(root.left), height(root.right)) + 1;
  }

  public static int totalNodes(Node root) {
    if (root == null) return 0;
    return 1 + totalNodes(root.left) + totalNodes(root.right);
  }


}
