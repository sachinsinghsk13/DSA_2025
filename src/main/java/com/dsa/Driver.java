package com.dsa;

import com.dsa.graphs.AdjacencyListGraph;
import com.dsa.graphs.AdjacencyMatrixGraph;
import com.dsa.graphs.Graph;
import com.dsa.graphs.algorithms.GraphTraversals;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    Graph g = new AdjacencyMatrixGraph(8, true);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(3, 5);
    g.addEdge(2, 4);
    g.addEdge(0, 1);
    g.addEdge(4, 7);
    g.addEdge(4, 6);
    g.addEdge(0, 7);
    g.addEdge(5, 1);
    g.addEdge(4, 3);

    int[] bfs = GraphTraversals.depthFirstSearch(g, 0);
    System.out.println(Arrays.toString(bfs));

  }
}


