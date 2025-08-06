package com.dsa.divideconquer;

import com.dsa.utils.Point;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ClosestPair {
  public static Pair<Point, Point> findClosestPoint(List<Point> points) {
    if (points == null || points.size() < 2) {
      throw new IllegalStateException("At least two points are required");
    }

    // use brute force
    if (points.size() <= 3) {
      return closestByBruteForce(points);
    }
    points.sort(Comparator.comparing(Point::x)); // sort points on x coordinates O(n)
    return closestByRecursion(points, 0, points.size() - 1);
  }

  private static Pair<Point, Point> closestByRecursion(List<Point> points, int start, int end) {
    return null;
  }

  private static Pair<Point, Point> closestByBruteForce(List<Point> points) {
    Pair<Point, Point> result = Pair.of(points.get(0), points.get(1));
    double minDistance = Double.MAX_VALUE;
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        Point p = points.get(i);
        Point q = points.get(j);
        double d = p.distanceTo(q);
        if (d < minDistance) {
          minDistance = d;
          result = Pair.of(p, q);
        }
      }
    }
    return result;
  }
}
