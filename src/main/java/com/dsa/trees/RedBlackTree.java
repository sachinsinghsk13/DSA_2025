package com.dsa.trees;


public class RedBlackTree {
  private RedBlackNode root;

  public void insert(int data) {
    if (root != null) {
      insertInternal(root, data);
    } else {
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
        fixRedBlackTreeViolations(node.left);
      }
    } else {
      if (node.right != null) {
        insertInternal(node.right, data);
      } else {
        node.right = new RedBlackNode(data, node);
        fixRedBlackTreeViolations(node.right);
      }
    }
  }

  private void rotateRight(RedBlackNode node) {
    RedBlackNode newRoot = node.left;
    newRoot.parent = node.parent;

    if (node.parent == null) {
      this.root = newRoot;
    } else if (node.parent.left == node) {
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
    if (node.parent == null) {
      this.root = newRoot;
    } else if (node.parent.left == node) {
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

  private void fixRedBlackTreeViolations(RedBlackNode node) {
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
        fixRedBlackTreeViolations(grandparent);
      }
    }
  }

  public void delete(int data) {
    deleteInternal(root, data);
  }

  private RedBlackNode findLargestNode(RedBlackNode node) {
    if (node != null && node.right != null) {
      return findLargestNode(node.right);
    }
    return node;
  }

  private void deleteInternal(RedBlackNode node, int data) {
    if (node == null) {
      return; // given data not found in tree
    }
    if (node.data == data) {
      if (node.hasBothChild()) {
        RedBlackNode inorderSuccessor = findLargestNode(node.left);
        node.data = inorderSuccessor.data;
        deleteInternal(node.left, node.data);
      } else {
        deleteNodeAndFixRedBlackViolations(node);
      }
    } else if (data < node.data) {
      deleteInternal(node.left, data);
    } else {
      deleteInternal(node.right, data);
    }
  }

  /**
   * @param node target node to be deleted. This node granted to have only one child or no child at all.
   */
  private void deleteNodeAndFixRedBlackViolations(RedBlackNode node) {
    if (node.parent == null) {
      root = null;
      return;
    }
    if (node.color == Color.RED || (node.hasLeftChild() && node.left.color == Color.RED) ||
        (node.hasRightChild() && node.right.color == Color.RED)) {
      if (node.hasLeftChild()) {
        node.color = Color.BLACK;
        node.data = node.left.data;
        node.left.parent = null;
        node.left = null;
      } else if (node.hasRightChild()) {
        node.color = Color.BLACK;
        node.data = node.right.data;
        node.right.parent = null;
        node.right = null;
      } else if (node.parent.left == node) {
        node.parent.left = null;
        node.parent = null;
      } else {
        node.parent.right = null;
        node.parent = null;
      }
    } else {
      fixDoubleBlackNode(node);
      if (node.hasLeftChild()) {
        // black node with a black left child
        node.data = node.left.data;
        node.left.parent = null;
        node.left = null;
      } else if (node.hasRightChild()) {
        // black node with a black right child
        node.data = node.right.data;
        node.right.parent = null;
        node.right = null;
      } else if (node.parent.left == node) { // black node which is left child of its parent
        node.parent.left = null;
        node.parent = null;
      } else {
        node.parent.right = null;
        node.parent = null;
      }
    }
  }

  private void fixDoubleBlackNode(RedBlackNode doubleBlackNode) {
    if (doubleBlackNode.color == Color.RED || doubleBlackNode.parent == null) {
      doubleBlackNode.color = Color.BLACK;
      return;
    }
    RedBlackNode sibling = doubleBlackNode.sibling();
    // we've a double black node at node's position.
    if (sibling.color == Color.BLACK && (sibling.hasLeftChild() && sibling.left.color == Color.RED || sibling.hasRightChild() && sibling.right.color == Color.RED)) {
      if (sibling.parent.left == sibling && sibling.hasLeftChild()) {
        // LL case
        rotateRight(sibling.parent);
        sibling.left.color = Color.BLACK;
      } else if (sibling.parent.left == sibling && sibling.hasRightChild()) {
        // LR case
      } else if (sibling.parent.right == sibling && sibling.hasRightChild()) {
        // RR case
        rotateLeft(sibling.parent);
        sibling.right.color = Color.BLACK;
      } else {
        // RL case
        rotateRight(sibling);
        sibling.color = Color.RED;
        sibling.parent.color = Color.BLACK;
        rotateLeft(sibling.parent.parent);
        sibling.color = Color.BLACK;
      }
    } else if (sibling.color == Color.BLACK) { // sibling is black having black children
      sibling.color = Color.RED;
      fixDoubleBlackNode(sibling.parent);
    } else if (sibling.isLeftChild()) {
      rotateRight(sibling.parent);
      sibling.right.color = Color.RED;
      fixDoubleBlackNode(doubleBlackNode);
    } else {
      rotateLeft(sibling.parent);
      sibling.color = Color.BLACK;
      sibling.left.color = Color.RED;
      fixDoubleBlackNode(doubleBlackNode);
    }
  }
}
