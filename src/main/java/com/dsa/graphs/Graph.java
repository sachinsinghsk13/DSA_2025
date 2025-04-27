package com.dsa.graphs;

public interface Graph {
    int totalVertices();
    void addEdge(int src, int dest);
    boolean hasEdge(int src, int dest);
    void removeEdge(int src, int dest);
    boolean isDirected();
    int[] getNeighbourVertices(int vertex);
}
