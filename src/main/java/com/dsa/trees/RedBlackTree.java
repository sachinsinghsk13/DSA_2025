package com.dsa.trees;

enum Color {
  RED, BLACK
}

class RedBlackNode {

  public int data;
  public RedBlackNode left;
  public RedBlackNode right;
  public RedBlackNode parent;
  public Color color = Color.RED;

  public RedBlackNode(int data) {
    this.data = data;
  }

  public RedBlackNode(int data, RedBlackNode parent) {
    this.data = data;
    this.parent = parent;
  }

  public RedBlackNode parent() {
    return parent;
  }

  public RedBlackNode grandparent() {
    if (parent != null) return parent.parent;
    return null;
  }

  public RedBlackNode uncle() {
    RedBlackNode grandparent = grandparent();
    if (grandparent == null) return null;
    if (parent == grandparent.left)
      return grandparent.right;
    else
      return grandparent.left;
  }

}

public class RedBlackTree {
  private RedBlackNode root;

  public void insert(int data) {
    if (root != null) {
      insertInternal(root, data);
    }
    else {
      // Because root is always black in RB Tree
      root = new RedBlackNode(data);
      root.color = Color.BLACK;
    }
  }

  private void insertInternal(RedBlackNode node, int data) {
    if (data < node.data) {
      if (node.left != null) {
        insertInternal(node.left, data);
      } else {
        node.left = new RedBlackNode(data, node);
        ensureRedBlackProperties(node.left);
      }
    } else {
      if (node.right != null) {
        insertInternal(node.right, data);
      } else {
        node.right = new RedBlackNode(data, node);
        ensureRedBlackProperties(node.right);
      }
    }
  }

  private void rotateRight(RedBlackNode node) {
    RedBlackNode newRoot = node.left;
    newRoot.parent = node.parent;
    if (node.parent.left == node) {
      node.parent.left = newRoot;
    } else {
      node.parent.right = newRoot;
    }
    RedBlackNode rightChildOfNewRoot = newRoot.right;
    if (rightChildOfNewRoot != null) {
      rightChildOfNewRoot.parent = node;
    }
    newRoot.right = node;
    node.parent = newRoot;
    node.left = rightChildOfNewRoot;
  }

  private void rotateLeft(RedBlackNode node) {
    RedBlackNode newRoot = node.right;
    newRoot.parent = node.parent;
    if (node.parent.left == node) {
      node.parent.left = newRoot;
    } else {
      node.parent.right = newRoot;
    }
    RedBlackNode leftChildOfNewRoot = newRoot.left;
    if (leftChildOfNewRoot != null) {
      leftChildOfNewRoot.parent = node;
    }
    newRoot.left = node;
    node.parent = newRoot;
    node.right = leftChildOfNewRoot;
  }

  private void ensureRedBlackProperties(RedBlackNode node) {
    if (node.parent == null) {
      // it is the root node.
      node.color = Color.BLACK;
    } else {
      RedBlackNode grandparent = node.grandparent();
      if (node.parent.color == Color.RED && (node.uncle() == null || node.uncle().color == Color.BLACK)) {
        if (node.data < grandparent.data && node.data < node.parent.data) {
            // LL case
          rotateRight(grandparent);
          grandparent.color = Color.RED;
          node.parent.color = Color.BLACK;
        } else if (node.data < grandparent.data && node.data > node.parent.data) {
          // LR
          rotateLeft(node.parent);
          rotateRight(grandparent);
          node.color = Color.BLACK;
          grandparent.color = Color.RED;
        } else if (node.data > grandparent.data && node.data < node.parent.data) {
          // RL
          rotateRight(node.parent);
          rotateLeft(grandparent);
          node.color = Color.BLACK;
          grandparent.color = Color.RED;
        } else {
          // RR
          rotateLeft(grandparent);
          grandparent.color = Color.RED;
          node.parent.color = Color.BLACK;
        }
      } else if (node.parent.color == Color.RED && node.uncle().color == Color.RED) {
        node.parent.color = Color.BLACK;
        node.uncle().color = Color.BLACK;
        grandparent.color = Color.RED;
        ensureRedBlackProperties(grandparent);
      }
    }
  }
}
