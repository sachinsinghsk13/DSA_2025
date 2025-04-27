package com.dsa.trees.mway;

public class BTree {
  private BTreeNode root;
  private final int ORDER; // maximum ORDER children and maximum ORDER - 1 keys except root node
  private final int MIN_ORDER; // minimum MIN_ORDER children and MIN_ORDER - 1 keys except the root node

  public BTree(int order) {
    this.ORDER = order;
    this.MIN_ORDER = Math.ceilDiv(ORDER, 2);
    this.root = new BTreeNode(ORDER);
  }

  public void insert(int key) {
    insertInternal(root, key);
  }

  private void insertInternal(BTreeNode node, int key) {

    if (node.leaf && node.size < ORDER - 1) { // target node is leaf and has available space for new key.
      int insertIndex = node.size;
      while (insertIndex > 0 && key < node.keys[insertIndex - 1]) {
        swapKey(node.keys, insertIndex, insertIndex - 1);
        insertIndex--;
      }
      node.keys[insertIndex] = key;
      node.size++;
    } else if (node.leaf && node.parent == null) { // root node became full.

      // add the new key along with all the keys of nodes in ascending order.
      int[] temp = new int[ORDER];
      System.arraycopy(node.keys, 0, temp, 0, node.size);
      int placeAt = node.size;
      while (placeAt > 0 && key < temp[placeAt - 1]) {
        swapKey(temp, placeAt, placeAt - 1);
        placeAt--;
      }
      temp[placeAt] = key;

      int mid = temp.length / 2;
      node.size = mid;
      BTreeNode rightNode = new BTreeNode(ORDER);

      for (int i = mid + 1, j = 0; i < temp.length; i++, j++) {
        rightNode.keys[j] = temp[i];
        rightNode.size++;
      }

      BTreeNode newRoot = new BTreeNode(ORDER);
      newRoot.keys[0] = temp[mid];
      newRoot.children[0] = node;
      newRoot.children[1] = rightNode;
      newRoot.leaf = false;
      node.parent = newRoot;
      rightNode.parent = newRoot;
      this.root = newRoot;
    } else if (node.leaf) {

    } else { // current node is an internal node. navigate to the appropriate leaf node.
      int i = 0;
      while (i < node.size) {
        if (key < node.keys[i])
          break;
        i++;
      }
      insertInternal(node.children[i], key);
    }
  }
  // target node is leaf but no space available for new key.
  // swaps the values present at two different indexes.
  private void swapKey(int[] array, int src, int dest) {
    if (src == dest) return;
    int temp = array[dest];
    array[dest]  = array[src];
    array[src] = temp;
  }


}
