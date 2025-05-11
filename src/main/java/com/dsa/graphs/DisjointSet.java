package com.dsa.graphs;

public class DisjointSet {
  private final int[] parent;
  private final int[] rank;
  private final int[] size;
  private final int N;

  public DisjointSet(int n) {
    this.N = n;
    parent = new int[n];
    rank = new int[n];
    size = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i;
      rank[i] = 1;
      size[i] = 1;
    }
  }


  public void union(int x, int y) {
    if (x < 0 || x >= N || y < 0 || y >= N) {
      throw new IllegalArgumentException("Invalid member");
    }

    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return;
    }
    int sizeOfUnion = size[xRoot] + size[yRoot];

    if (rank[xRoot] == rank[yRoot]) {
      parent[yRoot] = xRoot;
      rank[xRoot]++;
      size[xRoot] = sizeOfUnion;
    } else if (rank[xRoot] < rank[yRoot]) {
      parent[xRoot] = yRoot;
      size[yRoot] = sizeOfUnion;
    } else {
      parent[yRoot] = xRoot;
      size[xRoot] = sizeOfUnion;
    }
  }

  public int find(int x) {
    if (x < 0 || x >= N) {
      throw new IllegalArgumentException("Invalid member");
    }
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public boolean belongsToSameSet(int x, int y) {
    if (x < 0 || x >= N || y < 0 || y >= N) {
      throw new IllegalArgumentException("Invalid member");
    }
    return find(x) == find(y);
  }

  public int getSetSize(int x) {
    if (x < 0 || x >= N) {
      throw new IllegalArgumentException("Invalid member");
    }
    return size[find(x)];
  }
}
