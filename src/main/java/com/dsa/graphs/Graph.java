package com.dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
  private final int[][] matrix;
  private final int totalVertices;
  private final boolean isDirected;

  public Graph(int vertices, boolean isDirected) {
    this.matrix = new int[vertices][vertices];
    this.totalVertices = vertices;
    this.isDirected = isDirected;
  }

  public void addEdge(int src, int dest) {
    matrix[src][dest] = 1;
    if (!isDirected) {
      matrix[dest][src] = 1;
    }
  }

  public void removeEdge(int src, int dest) {
    matrix[src][dest] = 0;
    if (!isDirected) {
      matrix[dest][src] = 0;
    }
  }

  public boolean hasEdge(int src, int dest) {
    return matrix[src][dest] == 1;
  }

  public void display() {
    System.out.print("\t");
    for (int i = 0; i < matrix.length; i++) {
      System.out.print(i + "\t");
    }
    System.out.println();
    System.out.println("-----------------------------------");
    for (int i = 0; i < matrix.length; i++) {
      System.out.print(i + " | ");
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j] + "\t");
      }
      System.out.println();
    }
  }

  public List<Integer> getNeighbours(int src) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < totalVertices; i++) {
      if (matrix[src][i] == 1) {
        list.add(i);
      }
    }
    return list;
  }

  public void breadthFirstSearch(int src) {
    boolean[] visited = new boolean[totalVertices];
    Queue<Integer> queue = new LinkedList<>();
    visited[src] = true; // mark starting node as visited.
    queue.add(src);
    while (!queue.isEmpty()) {
      Integer vertex = queue.poll();
      List<Integer> neighbours = getNeighbours(vertex);
      System.out.println("Visited vertex " + vertex + " with neighbours " + neighbours);
      for (Integer neighbour : neighbours) {
        if (!visited[neighbour]) {
          queue.add(neighbour);
          visited[neighbour] = true;
        }
      }
    }
  }

  public void depthFirstSearch(int src) {
    boolean[] visited = new boolean[totalVertices];
    Stack<Integer> stack = new Stack<>();
    visited[src] = true;
    stack.push(src);
    while (!stack.isEmpty()) {
      Integer vertex = stack.pop();
      List<Integer> neighbours = getNeighbours(vertex);
      System.out.println("Visited vertex " + vertex + " having neighbours " + neighbours);
      for (Integer neighbour : neighbours) {
        if (!visited[neighbour]) {
          stack.push(neighbour);
          visited[neighbour] = true;
        }
      }
    }
  }

  public List<Integer> findShortestPath(int src, int dest) {
    boolean[] visited = new boolean[totalVertices];
    int[] parent = new int[totalVertices];
    Arrays.fill(parent, -1);

    Queue<Integer> queue = new LinkedList<>();
    queue.add(src);
    visited[src] = true;
    while (!queue.isEmpty()) {
      Integer vertex = queue.poll();
      if (vertex == dest) break;
      for (Integer neighbour : getNeighbours(vertex)) {
          if (!visited[neighbour]) {
            visited[neighbour] = true;
            parent[neighbour] = vertex;
            queue.add(neighbour);
          }
      }
    }
    List<Integer> path = new ArrayList<>();
    for (int at = dest; at != -1; at = parent[at]) {
      path.add(at);
    }

    Collections.reverse(path);
    if (path.get(0) != src) {
      System.out.println("Path not found");
      return Collections.emptyList();
    }
    return path;
  }

}
