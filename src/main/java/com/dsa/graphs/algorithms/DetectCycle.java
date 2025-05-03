package com.dsa.graphs.algorithms;

import com.dsa.graphs.Graph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DetectCycle {

  public static boolean hasCycleUndirectedBFS(Graph g) {
    if (g.isDirected()) throw new IllegalStateException("Graph is directed");
    boolean[] visited = new boolean[g.totalVertices()];
    Queue<Integer[]> q = new LinkedList<>();
    q.add(new Integer[]{0, -1});
    visited[0] = true;
    while (!q.isEmpty()) {
      Integer[] pair = q.poll();
      for (int n : g.getNeighbourVertices(pair[0])) {
        if (visited[n] && n != pair[1])
          return true;
        else if (!visited[n]) {
          visited[n] = true;
          q.add(new Integer[]{n, pair[0]});
        }
      }
    }
    return false;
  }

  public static boolean hasCycleUndirectedDFS(Graph g) {
    if (g.isDirected()) throw new IllegalStateException("Graph is directed");
    boolean[] visited = new boolean[g.totalVertices()];
    Stack<Integer[]> s = new Stack<>();
    s.push(new Integer[]{0, -1});
    visited[0] = true;
    while (!s.isEmpty()) {
      Integer[] pair = s.pop();
      for (int n : g.getNeighbourVertices(pair[0])) {
        if (visited[n] && n != pair[1])
          return true;
        else if (!visited[n]) {
          visited[n] = true;
          s.push(new Integer[]{n, pair[0]});
        }
      }
    }
    return false;
  }

  public static boolean hasCycleDirectedDFS(Graph g) {
    if (!g.isDirected()) throw new IllegalStateException("Graph is undirected");
    boolean[] visited = new boolean[g.totalVertices()];
    boolean[] recurStack = new boolean[g.totalVertices()];
    visited[0] = true;
    Stack<Integer> s = new Stack<>();
    s.push(0);
    recurStack[0] = true;
    while (!s.isEmpty()) {
      Integer vertex = s.pop();
      for (int i : g.getNeighbourVertices(vertex)) {
        if (visited[i] && recurStack[i]) return true;
        else if (!visited[i]) {
          s.push(i);
        }
      }
      recurStack[vertex] = false;
    }
    return false;
  }



}
