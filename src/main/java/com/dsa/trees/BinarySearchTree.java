package com.dsa.trees;

import lombok.Getter;

@Getter
public class BinarySearchTree {
  private Node root;

  public void insert(int data) {
    root = insertInternal(root, data);
  }

  private Node insertInternal(Node node, int data) {
    if (node == null) {
      return new Node(data);
    }
    if (data < node.data) {
      node.left = insertInternal(node.left, data);
    } else {
      node.right = insertInternal(node.right, data);
    }
    return node;
  }

  public int find(int data) {
    return findInternal(root, data);
  }

  private int findInternal(Node node, int data) {
    if (node == null) {
      return -1;
    }
    if (node.data == data) {
      return data;
    }
    if (data < node.data) {
      return findInternal(node.left, data);
    } else {
      return findInternal(node.right, data);
    }
  }

  public void printInorder() {
    printInorderInternal(root);
  }

  private void printInorderInternal(Node node) {
    if (node != null) {
      printInorderInternal(node.left);
      System.out.print(node.data + " ");
      printInorderInternal(node.right);
    }
  }

  public void delete(int data) {
    root = deleteInternal(root, data);
  }

  private Node deleteInternal(Node node, int data) {
    if (node == null) {
      return null; // given data is not found in tree;
    }
    if (node.data == data) { // target node found
      if (node.isLeafNode()) {
        System.out.println("Leaf node deleted");
        return null;
      } else if (node.hasLeftChild() && !node.hasRightChild()) {
        return node.left;
      } else if (!node.hasLeftChild() && node.hasRightChild()) {
        return node.right;
      } else {
        Node inorderPredecessor = findLargest(node.left);
        node.data = inorderPredecessor.data;
        node.left = deleteInternal(node.left, node.data);
      }
    } else if (data < node.data) {
      node.left = deleteInternal(node.left, data);
    } else {
      node.right = deleteInternal(node.right, data);
    }
    return node;
  }

  public Node findLargest(Node node) {
    if (node == null) {
      return null;
    }
    if (node.hasRightChild()) {
      return findLargest(node.right);
    }
    return node;
  }
}
