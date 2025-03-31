package com.dsa;

import com.dsa.trees.AVLTree;
import com.dsa.trees.RedBlackTree;
import java.util.List;

public class Driver {
  public static void main(String[] args) {
    RedBlackTree t = new RedBlackTree();
    for (Integer i : List.of(26, 25, 35, 18, 22, 61, 95)) {
      t.insert(i);
    }
    System.out.println(t);
  }
}
