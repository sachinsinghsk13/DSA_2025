package com.dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdjacencyMatrixGraph extends AbstractGraph {
  private final int[][] adjacencyMatrix;

  public AdjacencyMatrixGraph(int totalVertices, boolean isDirected) {
    super(totalVertices, isDirected);
    adjacencyMatrix = new int[totalVertices][totalVertices];
  }

  @Override
  public void addEdge(int src, int dest) {
    validateInput(src, dest);
    adjacencyMatrix[src][dest] = 1;
    if (!directed) {
      adjacencyMatrix[dest][src] = 1;
    }
  }

  @Override
  public boolean hasEdge(int src, int dest) {
    validateInput(src, dest);
    if (directed)
      return adjacencyMatrix[src][dest] == 1;
    return adjacencyMatrix[src][dest] == 1 && adjacencyMatrix[dest][src] == 1;
  }

  @Override
  public void removeEdge(int src, int dest) {
    validateInput(src, dest);
    if (hasEdge(src, dest)) {
      adjacencyMatrix[src][dest] = 0;
      if (!directed) {
        adjacencyMatrix[dest][src] = 0;
      }
    }
  }

  @Override
  public int[] getNeighbourVertices(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Invalid Vertex");
    List<Integer> neighbours = new ArrayList<>();
    for (int i = 0; i < totalVertices; i++) {
      if (adjacencyMatrix[vertex][i] == 1)
        neighbours.add(i);
    }
    return neighbours.stream().mapToInt(Integer::intValue).toArray();
  }
}
