package com.dsa.graphs;

public class WeightedEdge {
  public int src;
  public int dest;
  public int weight;

  public WeightedEdge(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  public int getSrc() {
    return src;
  }

  public int getDest() {
    return dest;
  }

  public int getWeight() {
    return weight;
  }
}
