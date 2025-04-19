package com.dsa.trees.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
  private final TrieNode root;

  public Trie() {
    this.root = new TrieNode('\0'); // initialise with null character
  }

  public void insert(char[] word) {
    TrieNode ptr = root;
    boolean flag = true;
    for (char c : word) {
      ptr = ptr.children.computeIfAbsent(c, TrieNode::new);
    }
    ptr.endOfWord = true;
  }

  public List<String> getAllWords() {
    return getWordWithPrefix("");
  }

  public List<String> getWordWithPrefix(String prefix) {
    return null;
  }

  private List<String> getWord(TrieNode n) {
    List<String> words = new ArrayList<>();
    char prefix = n.ch;
    for (TrieNode node : n.children.values()) {

    }
    return null;
  }
}
