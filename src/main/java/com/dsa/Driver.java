package com.dsa;

import com.dsa.graphs.AdjacencyMatrixGraph;
import com.dsa.graphs.WeightedGraph;
import com.dsa.graphs.algorithms.MinimumSpanningTree;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    WeightedGraph g = new AdjacencyMatrixGraph(5, false);
    g.addEdge(0, 1, 7);
    g.addEdge(0, 2, 3);
    g.addEdge(1, 2, 4);
    g.addEdge(1, 3, 9);
    g.addEdge(1, 4, 11);
    g.addEdge(2, 3, 10);

    MinimumSpanningTree.primsMST(g).forEach(System.out::println);


  }
}


