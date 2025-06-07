package com.dsa.brute.force.npuzzle;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class Board {

  private final int[][] board;
  private final Pair<Integer, Integer> blankIndex;
  private final String[] path;
  private final int order;

  public Board(int[][] board, Pair<Integer, Integer> blankIndex, String[] path, int order) {
    this.board = board;
    this.blankIndex = blankIndex;
    this.path = path;
    this.order = order;
  }

  /**
   * performs the given move if applicable and returns the new state of the board
   * @param move move to be performed
   * @return New state if applicable else blank.
   */
  public Optional<Board> performMove(TileMove move) {
    if (!canPerformMove(move)) return Optional.empty();
    int[][] temp = copyBoard();

    Integer i = blankIndex.getLeft();
    Integer j = blankIndex.getRight();

    Pair<Integer, Integer> targetIndex = switch (move) {
      case UP -> Pair.of(i - 1, j);
      case DOWN -> Pair.of(i + 1, j);
      case LEFT -> Pair.of(i, j - 1);
      case RIGHT -> Pair.of(i, j + 1);
    };

    int m = targetIndex.getLeft();
    int n = targetIndex.getRight();

    String[] pathTemp = new String[path.length + 1];
    System.arraycopy(path, 0, pathTemp, 0, path.length);
    pathTemp[path.length] = move.name();

    temp[i][j] = board[m][n];
    temp[m][n] = 0;
    return Optional.of(new Board(temp, targetIndex, pathTemp, order));
  }

  private boolean canPerformMove(TileMove move) {
    Integer i = blankIndex.getLeft();
    Integer j = blankIndex.getRight();
    return switch (move) {
      case UP -> i != 0;
      case DOWN -> i != (order - 1);
      case LEFT -> j != 0;
      case RIGHT -> j != (order - 1);
    };
  }

  private int[][] copyBoard() {
    int[][] temp = new int[order][order];
    for (int i = 0; i < order; i++) {
      System.arraycopy(board[i], 0, temp[i], 0, order);
    }
    return temp;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Board other = (Board) obj;
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        if (this.board[i][j] != other.board[i][j]) return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        result = 31 * result + board[i][j];
      }
    }
    return result;
  }


  public String toBoardKey() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        sb.append(board[i][j]).append(",");
      }
    }
    return sb.toString();
  }

  public boolean isSolvable() {
    int[] flattened = new int[order * order];
    int index = 0;
    int blankRowFromBottom = order - blankIndex.getLeft(); // 1-based from bottom

    // Flatten the board and ignore 0 for inversion count
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        flattened[index++] = board[i][j];
      }
    }

    int inversions = 0;
    for (int i = 0; i < flattened.length; i++) {
      if (flattened[i] == 0) continue; // skip blank
      for (int j = i + 1; j < flattened.length; j++) {
        if (flattened[j] == 0) continue;
        if (flattened[i] > flattened[j]) inversions++;
      }
    }

    // Check based on odd or even board order
    if (order % 2 == 1) {
      // Odd grid: solvable if inversions are even
      return inversions % 2 == 0;
    } else {
      // Even grid: solvable if (inversions + blank row from bottom) is even
      return (inversions + blankRowFromBottom) % 2 == 0;
    }
  }


}
