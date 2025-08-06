package com.dsa.brute.force.nqueen;

import java.util.ArrayList;
import java.util.List;

public class NQueenBoard {
  private final int[][] board;
  private int n;
  private List<Integer[]> placedQueens = new ArrayList<>();
  public NQueenBoard(int n) {
    // initialize the board
    this.board = new int[n][n];
    this.n = n;
  }

  public void placeQueens() {
     for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
         if (isSafe(i, j)) {
           board[i][j] = 1;
           placedQueens.add(new Integer[]{i, j});
         }
       }
     }
     printBoardState();
  }

  private boolean isSafe(int i, int j) {
    boolean isSafe = true;
    for (Integer[] coordinate : placedQueens) {
       if (coordinate[0] == i || coordinate[1] == j || (Math.abs(coordinate[0] - i) == Math.abs(coordinate[1] - j))) {
         isSafe = false;
         break;
       }
    }
    return isSafe;
  }


  public void printBoardState() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(board[i][j] + "\t");
      }
      System.out.println();
    }
  }

}
