package com.dsa;

import com.dsa.heaps.ArrayBinaryHeap;
import com.dsa.heaps.BinaryHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Driver {

  public static List<Integer> generateRandomNumbers(int count) {
    List<Integer> randomNumbers = new ArrayList<>();
    Random random = new Random();

    for (int i = 0; i < count; i++) {
      int num = random.nextInt(1000) + 1; // Generates number between 1 and 1000
      randomNumbers.add(num);
    }

    return randomNumbers;
  }
  public static void main(String[] args) {
    BinaryHeap heap = new ArrayBinaryHeap(200);
    for (Integer i : generateRandomNumbers(200)) {
      heap.insert(i);
    }

    while (!heap.isEmpty()) {
      System.out.print(heap.remove() + " ");
    }
  }
}
