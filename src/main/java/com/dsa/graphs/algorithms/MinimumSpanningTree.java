package com.dsa.graphs.algorithms;

import com.dsa.graphs.WeightedEdge;
import com.dsa.graphs.WeightedGraph;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

  public static List<WeightedEdge> primsMST(WeightedGraph g) {
    final int n = g.totalVertices();
    boolean[] visited = new boolean[n];
    List<WeightedEdge> mstEdges = new ArrayList<>();
    PriorityQueue<WeightedEdge> queue = new PriorityQueue<>();
    queue.add(new WeightedEdge(-1, 0, 0));
    while (!queue.isEmpty()) {
      WeightedEdge edge = queue.poll();
      if (visited[edge.dest]) continue;
      visited[edge.dest] = true;
      if (edge.src >= 0) {
        mstEdges.add(edge);
      }

      for (WeightedEdge e: g.getNeighboursWithWeight(edge.dest)) {
        if (!visited[e.dest]) {
          queue.add(e);
        }
      }
    }
    return mstEdges;
  }

}
