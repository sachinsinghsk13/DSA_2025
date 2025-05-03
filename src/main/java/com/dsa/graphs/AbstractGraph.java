package com.dsa.graphs;

public abstract class AbstractGraph implements Graph {
  protected final boolean directed;
  protected final int totalNodes;

  public AbstractGraph(int totalNodes, boolean isDirected) {
    this.directed = isDirected;
    this.totalNodes = totalNodes;
  }

  @Override
  public int totalVertices() {
    return totalNodes;
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
    return v < 0 || v >= totalNodes;
  }
}
