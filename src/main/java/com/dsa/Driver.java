package com.dsa;

import com.dsa.graphs.AdjacencyListGraph;
import com.dsa.graphs.AdjacencyMatrixGraph;
import com.dsa.graphs.DisjointSet;
import com.dsa.graphs.WeightedGraph;
import com.dsa.graphs.algorithms.MinimumSpanningTree;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    WeightedGraph g = new AdjacencyListGraph(6, false);
    g.addEdge(0, 1, 7);
    g.addEdge(0, 2, 6);
    g.addEdge(0, 3, 1);
    g.addEdge(1, 2, 8);
    g.addEdge(2, 3, 5);
    g.addEdge(2, 4, 3);
    g.addEdge(3, 5, 5);
    g.addEdge(4, 5, 2);
    g.addEdge(4, 3, 4);
    MinimumSpanningTree.kruskalsMST(g).forEach(System.out::println);
  }
}


