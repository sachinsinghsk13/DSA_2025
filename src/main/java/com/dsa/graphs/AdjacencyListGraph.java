package com.dsa.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class AdjacencyListGraph extends AbstractGraph implements WeightedGraph {
  private final List<List<WeightedEdge>> adjacencyList = new ArrayList<>();
  private final static int DEFAULT_WEIGHT = 1;

  public AdjacencyListGraph(int totalNodes, boolean isDirected) {
    super(totalNodes, isDirected);
    for (int i = 0; i < totalNodes; i++) {
      adjacencyList.add(new ArrayList<>());
    }
  }


  @Override
  public void addEdge(int src, int dest) {
    validateInput(src, dest);
    addEdge(src, dest, DEFAULT_WEIGHT);
  }

  @Override
  public boolean hasEdge(int src, int dest) {
    validateInput(src, dest);
    if (directed)
      return findEdge(src, dest).isPresent();
    return findEdge(src, dest).isPresent() && findEdge(dest, src).isPresent();
  }

  private Optional<WeightedEdge> findEdge(int src, int dest) {
    return adjacencyList.get(src).stream().filter(edge -> edge.dest == dest).findFirst();
  }

  @Override
  public void removeEdge(int src, int dest) {
    validateInput(src, dest);
    Optional<WeightedEdge> edge = findEdge(src, dest);
    edge.ifPresent(weightedEdge -> adjacencyList.get(src).remove(weightedEdge));
    if (!directed) {
      Optional<WeightedEdge> reverseEdge = findEdge(dest, src);
      reverseEdge.ifPresent(weightedEdge -> adjacencyList.get(dest).remove(reverseEdge.get()));
    }
  }


  @Override
  public int[] getNeighbourNodes(int vertex) {
    if (isInvalidVertex(vertex)) throw new IllegalArgumentException("Vertex not found in graph.");
    return adjacencyList.get(vertex).stream().mapToInt(WeightedEdge::getDest).toArray();
  }

  @Override
  public int[] getInEdges(int vertex) {
    return IntStream.range(0, totalNodes).filter(i -> findEdge(i, vertex).isPresent()).toArray();
  }

  @Override
  public int[] getOutEdges(int vertex) {
    return getNeighbourNodes(vertex);
  }

  @Override
  public void addEdge(int src, int dest, int weight) {
    adjacencyList.get(src).add(new WeightedEdge(src, dest, weight));
    if (!directed) {
      adjacencyList.get(dest).add(new WeightedEdge(dest, src, weight));
    }
  }

  @Override
  public List<WeightedEdge> getNeighboursWithWeight(int vertex) {
    return adjacencyList.get(vertex);
  }

  @Override
  public Optional<WeightedEdge> getWeightedEdge(int src, int dest) {
    return findEdge(src, dest);
  }
}
