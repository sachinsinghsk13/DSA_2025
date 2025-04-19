package com.dsa;

import com.dsa.graphs.Graph;
import com.dsa.heaps.ArrayBinaryHeap;
import com.dsa.heaps.BinaryHeap;
import com.dsa.heaps.BinomialHeap;
import com.dsa.trees.mway.BTree;
import com.dsa.trees.trie.Trie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Driver {

  public static void main(String[] args) {
    Graph g = new Graph(6, false);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 4);
    g.addEdge(3, 5);
    g.display();
    String path = g.findShortestPath(5, 4).stream().map(String::valueOf).collect(Collectors.joining("->"));
    System.out.println(path);

  }
}
