package com.dsa.trees;

public class AVLTree {
  private Node root;

  public void insert(int data) {
    root = insertInternal(root, data);
  }

  private static int getHeight(Node node) {
    return node == null ? 0 : node.height;
  }

  private Node rotateRight(Node node) {
    var newRoot = node.left;
    var t2 = newRoot.right;
    newRoot.right = node;
    node.left = t2;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
  }

  private Node rotateLeft(Node node) {
    var newRoot = node.right;
    var t2 = newRoot.left;
    newRoot.left = node;
    node.right = t2;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
  }

  private void updateHeight(Node node) {
    int leftHeight = getHeight(node.left);
    int rightHeight = getHeight(node.right);
    node.height = Math.max(leftHeight, rightHeight) + 1;
    node.balanceFactor = leftHeight - rightHeight;
  }

  private Node insertInternal(Node node, int data) {
    if (node == null) return Node.builder().data(data).height(1).balanceFactor(0).build();
    if (data < node.data) {
      node.left = insertInternal(node.left, data);
    } else {
      node.right = insertInternal(node.right, data);
    }
     // update the height and balance factor of this node.
    updateHeight(node);

    // check if any rotations are required
    if (node.balanceFactor > 1 && data < node.left.data) {
      // do LL rotation
      return rotateRight(node);
    } else if (node.balanceFactor > 1 && data > node.left.data) {
      // do LR rotation
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    } else if (node.balanceFactor < -1 && data < node.right.data) {
      // do RL rotation
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    } else if (node.balanceFactor < -1 && data > node.right.data) {
      // do RR rotation
      return rotateRight(node);
    }

    return node;
  }

  public void delete(int data) {
    root = deleteInternal(root, data);
  }

  private Node findLargest(Node node) {
    if (node != null && node.right != null) return findLargest(node.right);
    return node;
  }

  private Node deleteInternal(Node node, int data) {
      if (node == null) return null;
      if (node.data == data) {
        // target node found.
        if (node.isLeafNode()) {
          return null;
        } else if (node.hasLeftChild() && !node.hasRightChild()) {
          return node.left;
        } else if (node.hasRightChild() && !node.hasLeftChild()) {
          return node.right;
        } else {
          Node inorderPredesessor = findLargest(node.left);
          node.data = inorderPredesessor.data;
          node.left = deleteInternal(node.left, node.data);
        }
      } else if (data < node.data) {
        node.left = deleteInternal(node.left, data);
      } else {
        node.right = deleteInternal(node.right, data);
      }
      updateHeight(node);
      if (node.balanceFactor > 1) {
        switch (node.left.balanceFactor) {
          case 0:
          case 1:
            return rotateRight(node);
          case - 1:
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
      } else if (node.balanceFactor < -1) {
        switch (node.right.balanceFactor) {
          case 0:
          case 1:
            rotateLeft(node);
          case -1:
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
      }
      return node;
  }
}
