package com.dsa.graphs;

import java.util.List;
import java.util.Optional;

public interface WeightedGraph extends Graph {
  void addEdge(int src, int dest, int weight);
  List<WeightedEdge> getNeighboursWithWeight(int vertex);
  Optional<WeightedEdge> getWeightedEdge(int src, int dest);
}
