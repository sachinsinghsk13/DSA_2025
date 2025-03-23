package com.dsa;

import com.dsa.trees.BinarySearchTree;

public class Driver {
  public static void main(String[] args) {
    int[] numbers = new int[]{45, 39, 56, 12, 34, 78, 32, 10, 89, 54, 67, 81};
    BinarySearchTree tree = new BinarySearchTree();
    for (int num : numbers) {
      tree.insert(num);
    }
    System.out.println("created");
    tree.printInorder();
    tree.delete(89);
    System.out.println();
    tree.printInorder();
  }
}
