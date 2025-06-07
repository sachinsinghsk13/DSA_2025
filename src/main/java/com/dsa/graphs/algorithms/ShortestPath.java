package com.dsa.graphs.algorithms;

import com.dsa.graphs.WeightedEdge;
import com.dsa.graphs.WeightedGraph;
import org.apache.commons.lang3.tuple.Pair;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestPath {
  public static int[] dijkastra(WeightedGraph g, int source) {
    int n = g.totalVertices();
    final int[] dist = new int[n];
    final boolean[] visited = new boolean[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;
    PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(java.util.Map.Entry.comparingByValue());
    pq.add(Pair.of(source, 0));

    while (!pq.isEmpty()) {
      Pair<Integer, Integer> vertex = pq.poll();

      if (visited[vertex.getKey()]) continue;

      visited[vertex.getKey()] = true;
      for (WeightedEdge edge : g.getNeighboursWithWeight(vertex.getKey())) {
        int neighbourDistance = edge.getWeight() + vertex.getValue();
        if (neighbourDistance < dist[edge.dest]) {
          dist[edge.dest] = neighbourDistance;
          pq.add(Pair.of(edge.dest, neighbourDistance));
        }
      }
    }

    return dist;
  }
}
