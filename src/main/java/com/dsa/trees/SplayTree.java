package com.dsa.trees;

public class SplayTree {
  private Node root;

  private void rotateRight(Node node) {
    Node newRoot = node.left;
    newRoot.parent = node.parent;

    if (node.parent == null) {
      this.root = newRoot;
    } else if (node.parent.left == node) {
      node.parent.left = newRoot;
    } else {
      node.parent.right = newRoot;
    }
    Node rightChildOfNewRoot = newRoot.right;
    if (rightChildOfNewRoot != null) {
      rightChildOfNewRoot.parent = node;
    }
    newRoot.right = node;
    node.parent = newRoot;
    node.left = rightChildOfNewRoot;
  }

  private void rotateLeft(Node node) {
    Node newRoot = node.right;
    newRoot.parent = node.parent;
    if (node.parent == null) {
      this.root = newRoot;
    } else if (node.parent.left == node) {
      node.parent.left = newRoot;
    } else {
      node.parent.right = newRoot;
    }
    Node leftChildOfNewRoot = newRoot.left;
    if (leftChildOfNewRoot != null) {
      leftChildOfNewRoot.parent = node;
    }
    newRoot.left = node;
    node.parent = newRoot;
    node.right = leftChildOfNewRoot;
  }

  private void splay(Node node) {
    if (node.parent == null) {
      this.root = node;
      return;
    }
    Node grandparent = node.parent.parent != null ? node.parent.parent : null;
    if (grandparent != null) {
      if (grandparent.left == node.parent && node.parent.left == node) {
        // LL
        rotateRight(grandparent);
        rotateRight(node.parent);
      } else if (grandparent.left == node.parent && node.parent.right == node) {
        // LR
        rotateLeft(node.parent);
        rotateRight(grandparent);
      } else if (grandparent.right == node.parent && node.parent.left == node) {
        // RL
        rotateRight(node.parent);
        rotateLeft(grandparent);
      } else {
        // RR
        rotateLeft(grandparent);
        rotateLeft(node.parent);
      }
    } else if (node.parent.left == node) {
      rotateRight(node.parent);
    } else {
      rotateLeft(node.parent);
    }
    splay(node);
  }

  public void insert(int data) {
    if (root == null) {
      root = new Node(data);
      return;
    }
    insertInternal(root, data);
  }

  private void insertInternal(Node node, int data) {
    if (data < node.data) {
      if (node.left == null) {
        node.left = new Node(data);
        node.left.parent = node;
        splay(node.left);
      } else {
        insertInternal(node.left, data);
      }
    } else if (data > node.data) {
      if (node.right == null) {
        node.right = new Node(data);
        node.right.parent = node;
        splay(node.right);
      } else {
        insertInternal(node.right, data);
      }
    }
  }

  private static class Node {
    int data;
    Node left, right, parent;

    Node(int data) {
      this.data = data;
    }
  }
}
