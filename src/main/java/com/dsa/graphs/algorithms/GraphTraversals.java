package com.dsa.graphs.algorithms;

import com.dsa.graphs.Graph;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.dsa.graphs.algorithms.NodeColor.*;

public class GraphTraversals {

  public static int[] breadthFirstSearch(Graph g, int start) {
    // make all vertices white.
    int n = g.totalVertices();
    final int[] visited = new int[n];
    Arrays.fill(visited, WHITE);

    int[] result = new int[n];

    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    visited[start] = GREY;
    int i = 0;
    while (!q.isEmpty()) {
      Integer vertex = q.poll();
      result[i] = vertex;
      i++;
      for (int neighbour : g.getNeighbourVertices(vertex)) {
        if (visited[neighbour] == WHITE) {
          q.add(neighbour);
          visited[neighbour] = GREY;
        }
      }
      visited[vertex] = BLACK;
    }
    return result;
  }

  public static int[] depthFirstSearch(Graph g, int start) {
    // make all vertices white.
    int n = g.totalVertices();
    final int[] visited = new int[n];
    Arrays.fill(visited, WHITE);

    int[] result = new int[n];

    Stack<Integer> s = new Stack<>();
    s.push(start);
    visited[start] = GREY;
    int i = 0;
    while (!s.isEmpty()) {
      Integer vertex = s.pop();
      result[i] = vertex;
      i++;
      for (int neighbour : g.getNeighbourVertices(vertex)) {
        if (visited[neighbour] == WHITE) {
          s.push(neighbour);
          visited[neighbour] = GREY;
        }
      }
      visited[vertex] = BLACK;
    }
    return result;
  }
}
