package com.dsa;

import com.dsa.trees.AVLTree;
import java.util.List;

public class Driver {
  public static void main(String[] args) {
    AVLTree t = new AVLTree();
    for (Integer i : List.of(50, 30, 60, 20, 35)) {
      t.insert(i);
    }
    System.out.println(t);
    t.delete(60);
  }
}
