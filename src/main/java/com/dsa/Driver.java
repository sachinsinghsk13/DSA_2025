package com.dsa;

import com.dsa.trees.AVLTree;
import com.dsa.trees.RedBlackTree;
import java.util.List;

public class Driver {
  public static void main(String[] args) {
    RedBlackTree t = new RedBlackTree();
    for (Integer i : List.of(30, 20, 40)) {
      t.insert(i);
    }
    System.out.println(t);
    t.delete(20);
  }
}
