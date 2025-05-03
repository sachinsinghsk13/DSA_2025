package com.dsa.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AdjacencyListGraph extends AbstractGraph {
  private final List<List<Integer>> adjacencyList = new ArrayList<>();


  public AdjacencyListGraph(int totalVertices, boolean isDirected) {
    super(totalVertices, isDirected);
    for (int i = 0; i < totalVertices; i++) {
      adjacencyList.add(new ArrayList<>());
    }
  }


  @Override
  public void addEdge(int src, int dest) {
    validateInput(src, dest);

    adjacencyList.get(src).add(dest);
    if (!directed) {
      adjacencyList.get(dest).add(src);
    }
  }

  @Override
  public boolean hasEdge(int src, int dest) {
    validateInput(src, dest);
    if (directed)
      return adjacencyList.get(src).contains(dest);
    return adjacencyList.get(src).contains(dest) && adjacencyList.get(dest).contains(src);
  }

  @Override
  public void removeEdge(int src, int dest) {
    validateInput(src, dest);
    if (hasEdge(src, dest)) {
      adjacencyList.get(src).remove(dest);
      if (!directed) {
        adjacencyList.get(dest).remove(src);
      }
    }
  }


  @Override
  public int[] getNeighbourVertices(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Vertex not found in graph.");
    return adjacencyList.get(vertex).stream().mapToInt(Integer::intValue).toArray();
  }

  @Override
  public int[] getInEdges(int vertex) {
    return IntStream.range(0, totalVertices).filter(i -> adjacencyList.get(i).contains(vertex)).toArray();
  }

  @Override
  public int[] getOutEdges(int vertex) {
    return getNeighbourVertices(vertex);
  }

}
