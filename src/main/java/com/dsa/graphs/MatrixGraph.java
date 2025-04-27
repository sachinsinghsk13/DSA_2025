package com.dsa.graphs;

import java.util.Arrays;

public class MatrixGraph extends AbstractGraph {
  private final int[][] adjacencyMatrix;

  public MatrixGraph(int totalVertices, boolean isDirected) {
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
    return Arrays.copyOf(adjacencyMatrix[vertex], totalVertices);
  }
}
