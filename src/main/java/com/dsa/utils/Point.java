package com.dsa.utils;

import lombok.Getter;

public record Point(int x, int y) {
  public double distanceTo(Point q) {
    return Math.sqrt(Math.pow(q.x - this.x, 2) + Math.pow(q.y - this.y, 2));
  }
}
