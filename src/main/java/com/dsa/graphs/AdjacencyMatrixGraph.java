package com.dsa.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdjacencyMatrixGraph extends AbstractGraph implements WeightedGraph {
  private final int[][] weightMatrix;
  private final boolean[][] edgeExistenceMatrix;
  private static final int DEFAULT_WEIGHT = 1;
  public AdjacencyMatrixGraph(int totalNodes, boolean isDirected) {
    super(totalNodes, isDirected);
    weightMatrix = new int[totalNodes][totalNodes];
    edgeExistenceMatrix = new boolean[totalNodes][totalNodes];
  }

  @Override
  public void addEdge(int src, int dest) {
    addEdge(src, dest, DEFAULT_WEIGHT);
  }

  @Override
  public boolean hasEdge(int src, int dest) {
    validateInput(src, dest);
    if (directed)
      return edgeExistenceMatrix[src][dest];
    return edgeExistenceMatrix[src][dest] && edgeExistenceMatrix[dest][src];
  }

  @Override
  public void removeEdge(int src, int dest) {
    validateInput(src, dest);
    if (hasEdge(src, dest)) {
      edgeExistenceMatrix[src][dest] = false;
      if (!directed) {
        edgeExistenceMatrix[dest][src] = false;
      }
    }
  }

  @Override
  public int[] getNeighbourNodes(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Invalid Vertex");
    List<Integer> neighbours = new ArrayList<>();
    for (int i = 0; i < totalNodes; i++) {
      if (edgeExistenceMatrix[vertex][i])
        neighbours.add(i);
    }
    return neighbours.stream().mapToInt(Integer::intValue).toArray();
  }

  @Override
  public int[] getInEdges(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Invalid Vertex");
    List<Integer> incomingEdges = new ArrayList<>();
    for (int i = 0; i < totalNodes; i++) {
      if (edgeExistenceMatrix[i][vertex]) {
        incomingEdges.add(i);
      }
    }
    return incomingEdges.stream().mapToInt(Integer::intValue).toArray();
  }

  @Override
  public int[] getOutEdges(int vertex) {
    return getNeighbourNodes(vertex);
  }

  @Override
  public void addEdge(int src, int dest, int weight) {
    validateInput(src, dest);
    weightMatrix[src][dest] = weight;
    edgeExistenceMatrix[src][dest] = true;
    if (!directed) {
      weightMatrix[dest][src] = weight;
      edgeExistenceMatrix[dest][src] = true;
    }
  }

  @Override
  public List<WeightedEdge> getNeighboursWithWeight(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Invalid Vertex");
    List<WeightedEdge> edges = new ArrayList<>();
    for (int i = 0; i < totalNodes; i++) {
      if (edgeExistenceMatrix[vertex][i]) {
        edges.add(new WeightedEdge(vertex, i, weightMatrix[vertex][i]));
      }
    }
    return edges;
  }

  @Override
  public Optional<WeightedEdge> getWeightedEdge(int src, int dest) {
    if (hasEdge(src, dest))
        return Optional.of(new WeightedEdge(src, dest, weightMatrix[src][dest]));
    return Optional.empty();
  }
}
