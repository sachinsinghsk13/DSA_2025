package com.dsa.graphs.algorithms;

import com.dsa.graphs.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
  public static int[] topologicalSort(Graph g) {
    Queue<Integer> q = new LinkedList<>();
    int[] indegree = new int[g.totalVertices()];
    // find all the 0 indegree nodes
    for (int i = 0; i < g.totalVertices(); i++) {
      indegree[i] = g.getInEdges(i).length;
      if (indegree[i] == 0)
        q.add(i);

    }
    List<Integer> result = new ArrayList<>();
    while (!q.isEmpty()) {
      Integer vertex = q.poll();
      result.add(vertex);
      for (int n : g.getNeighbourVertices(vertex)) {
        indegree[n] = indegree[n] - 1;
        if (indegree[n] == 0)
          q.add(n);
      }
    }
    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
