package com.dsa.brute.force.npuzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class XPuzzleProblem {
  public static List<String> solveNPuzzleProblem(Board intialBoard, Board targetBoard) {
    Set<String> visited = new HashSet<>();
    Queue<Board> queue = new LinkedList<>();
    queue.add(intialBoard);
    visited.add(intialBoard.toBoardKey());
    while (!queue.isEmpty()) {
      Board board = queue.poll();
      if (board.equals(targetBoard)) {
        return List.of(board.getPath());
      }

      for (TileMove move : TileMove.values()) {
        Optional<Board> nextStateBoard = board.performMove(move);
        if (nextStateBoard.isPresent()) {
          Board b = nextStateBoard.get();
          String key = b.toBoardKey();
          if (!visited.contains(key)) {
            queue.add(b);
            visited.add(key);
          }

        }
      }
    }
    return List.of();
  }
}
