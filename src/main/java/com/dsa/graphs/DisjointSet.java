package com.dsa.graphs;

public class DisjointSet {
  private final int[] parent;
  private final int[] rank;

  public DisjointSet(int n) {
    parent = new int[n];
    rank = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i;
      rank[i] = 1;
    }
  }


  public void union(int x, int y) {
      int xRoot = find(x);
      int yRoot = find(y);

      if (xRoot == yRoot) return;

      if (rank[xRoot] == rank[yRoot]) {
        parent[yRoot] = xRoot;
        rank[xRoot]++;
      } else if (rank[xRoot] < rank[yRoot]) {
        parent[xRoot] = yRoot;
      } else {
        parent[yRoot] = xRoot;
      }
  }

  public int find(int x) {
    if (parent[x] != x)
        parent[x] = find(parent[x]);
    return parent[x];
  }

  public boolean belongsToSameSet(int x, int y) {
    return find(x) == find(y);
  }
}
