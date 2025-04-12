package com.dsa.heaps;

public class ArrayBinaryHeap implements BinaryHeap {
  private final int[] heap;
  private int size = 0;
  private final int MAX;
  public ArrayBinaryHeap(int size) {
    heap = new int[size];
    MAX = size;
  }

  @Override
  public void insert(int data) {
    if (size < MAX) {
      heap[size] = data;
      int curr = size++;
      int parent = (curr - 1) / 2;
      while (curr != 0) {
          if (heap[curr] > heap[parent]) {
            int temp = heap[curr];
            heap[curr] = heap[parent];
            heap[parent] = temp;
          }
          curr = parent;
          parent = curr / 2;
      }
    } else  {
      System.out.println("Heap is full");
    }
  }

  @Override
  public int remove() {
    if (size != 0) {
      int element = heap[0];
      heap[0] = heap[size - 1];
      size = size - 1;
      int curr = 0;
      boolean flag = true;
      while (flag) {
        int l = 2 * curr + 1;
        int r = 2 * curr + 2;
        int temp = heap[curr];
        if (r < size) {
          if (heap[r] > heap[l]) {
            heap[curr] = heap[r];
            heap[r] = temp;
            curr = r;
          } else {
            heap[curr] = heap[l];
            heap[l] = temp;
            curr = l;
          }
        } else if (l < size && heap[l] > heap[curr])  {
          heap[curr] = heap[l];
          heap[l] = temp;
        } else {
          flag = false;
        }
      }
      return element;
    }
    return -1;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}
