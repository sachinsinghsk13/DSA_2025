package com.dsa.graphs;

public abstract class AbstractGraph implements Graph {
  protected final boolean directed;
  protected final int totalVertices;

  public AbstractGraph(int totalVertices, boolean isDirected) {
    this.directed = isDirected;
    this.totalVertices = totalVertices;
  }

  @Override
  public int totalVertices() {
    return totalVertices;
  }

  @Override
  public boolean isDirected() {
    return directed;
  }

  protected void validateInput(int src, int dest) {
    if (isInvalidVertex(src) || isInvalidVertex(dest))
      throw new IllegalArgumentException("Source or destination not found in graph");
  }

  protected boolean isInvalidVertex(int v) {
    return v < 0 || v >= totalVertices;
  }
}
