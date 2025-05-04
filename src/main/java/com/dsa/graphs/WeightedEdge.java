package com.dsa.graphs;

import lombok.Getter;

@Getter
public class WeightedEdge implements Comparable<WeightedEdge> {
  public int src;
  public int dest;
  public int weight;

  public WeightedEdge(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  @Override
  public int compareTo(WeightedEdge o) {
    return weight - o.weight;
  }

  @Override
  public String toString() {
    return String.format("%d ---(%d)---> %d", src, weight, dest);
  }
}
