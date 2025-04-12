package com.dsa.heaps;

import java.util.Objects;

public class BinomialNode {
  int data, degree;
  BinomialNode parent, child, sibling;

  public BinomialNode(int data) {
    this.data = data;
    this.degree = 0;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BinomialNode that = (BinomialNode) o;
    return data == that.data;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data);
  }
}
