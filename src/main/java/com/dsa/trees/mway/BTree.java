package com.dsa.trees.mway;

public class BTree {

  private BTreeNode root;
  private final int order;

  public BTree(int order) {
    this.order = order;
    this.root = new BTreeNode(order);
  }

  public void insert(int key) {
    insertInternal(root, key);
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private void insertInternal(BTreeNode node, int key) {
    if (node.isLeafNode && node.totalKeys < node.order) { // is this node a leaf node and has space available for new key
      int i = node.totalKeys;
      while (i > 0 && key < node.keys[i - 1]) {
        swap(node.keys, i, i - 1);
        i--;
      }
      node.keys[i] = key;
      node.totalKeys++;
    } else if (node.isLeafNode) { // this is a leaf node but no space is available for new key so split this node.
      // split the node.
      splitNode(node, key);
    } else {
      int i = 0;
      while (i < node.totalKeys) {
        if (key < node.keys[i]) {
          break;
        }
        i++;
      }
      insertInternal(node.children[i], key);
    }
  }


  // splits the given full node and merges with parent
  private BTreeNode splitNode(BTreeNode node, int key) {
    // allocate a temporary array for holding the extra key
    int[] temp = new int[node.order + 1];

    // copy all keys from node to temp array
    if (node.totalKeys >= 0) System.arraycopy(node.keys, 0, temp, 0, node.totalKeys);

    // place the extra key in its correct position in sorted order
    int i = node.totalKeys;
    while (i > 0 && key < temp[i - 1]) {
      swap(temp, i , i - 1);
      i--;
    }
    temp[i] = key;

    // Find the middle key
    int mid = temp.length / 2; // take the floor value of division
    int midKey = temp[mid];

    /*
    * Now split the node from mid the index.
    * Left Node:
    *    total keys = value of mid.
    *     index range = 0 to mid - 1
    * Right:
    *     total keys = order of node - (mid + 1)
    *     index range = mid + 1 to order - 1.
    *
    * */

    // for left Node
    node.totalKeys = mid;
    System.arraycopy(temp, 0, node.keys, 0, mid);
    // for right node
    BTreeNode rightNode = new BTreeNode(order);
    rightNode.totalKeys = order - (mid + 1);

    // move elements to right node
    for(int p = mid + 1, q = 0; p < order; p++, q++) {
      rightNode.keys[q] = temp[p];
    }

    /*
     * Now we have node (left node), rightNode and midKey which we have to push to the parent node.
     */

    if (node.parent == null) { // if this is the root node.
      BTreeNode newRoot = new BTreeNode(order);
      newRoot.keys[0] = midKey;
      newRoot.children[0] = node;
      newRoot.children[1] = rightNode;
      newRoot.totalKeys = 1;
      newRoot.isLeafNode = false;

      node.parent = newRoot;
      rightNode.parent = newRoot;

      this.root = newRoot;
      return newRoot;
    } else if (node.parent.totalKeys < order) {
      int j = node.parent.totalKeys;
      while (j > 0 && midKey < node.parent.keys[j - 1]) {
        swap(node.parent.keys, i , i - 1);
        j--;
      }
      node.parent.keys[j] = midKey;
      node.totalKeys++;
      node.children[j] = node;
      node.children[j + 1] = rightNode;
      rightNode.parent = node.parent;
      return key < midKey ? node : rightNode;
    } else {
      BTreeNode parent = splitNode(node.parent, midKey);
      // look for the midKey in parent.keys and insert left and right child to that key
      return key < midKey ? node : rightNode;
    }
  }
}
