package com.dsa;

import com.dsa.heaps.ArrayBinaryHeap;
import com.dsa.heaps.BinaryHeap;
import com.dsa.heaps.BinomialHeap;
import com.dsa.trees.mway.BTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Driver {

  public static void main(String[] args) {
    BTree h = new BTree(4);
    for (Integer i : List.of(30, 23, 45, 67, 12, 34, 75, 59, 25, 64, 69, 93, 2, 1)) {
      h.insert(i);
    }

  }
}
