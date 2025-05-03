package com.dsa;

import com.dsa.graphs.AdjacencyListGraph;
import com.dsa.graphs.AdjacencyMatrixGraph;
import com.dsa.graphs.Graph;
import com.dsa.graphs.algorithms.DetectCycle;
import com.dsa.graphs.algorithms.GraphTraversals;
import com.dsa.graphs.algorithms.TopologicalSort;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    Graph g = new AdjacencyMatrixGraph(5, true);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    System.out.println(Arrays.toString(TopologicalSort.topologicalSort(g)));

  }
}


