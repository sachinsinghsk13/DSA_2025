package com.dsa.graphs;

import java.util.List;

public interface WeightedGraph extends Graph {
  void addEdge(int src, int dest, int weight);
  List<WeightedEdge> getNeighboursWithWeight(int vertex);
  WeightedEdge getWeightedEdge(int src, int dest);
}
