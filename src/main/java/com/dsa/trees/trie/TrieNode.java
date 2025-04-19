package com.dsa.trees.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  char ch;
  Map<Character, TrieNode> children = new HashMap<>();
  boolean endOfWord;

  public TrieNode(char ch) {
    this.ch = ch;
  }
}
