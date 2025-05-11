package com.dsa;

import com.dsa.graphs.AdjacencyMatrixGraph;
import com.dsa.graphs.DisjointSet;
import com.dsa.graphs.WeightedGraph;
import com.dsa.graphs.algorithms.MinimumSpanningTree;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    DisjointSet set = new DisjointSet(8);
    set.union(0, 3);
    set.union(4, 5);
    set.union(5, 0);
    System.out.println(set.belongsToSameSet(0, 4));
  }
}


