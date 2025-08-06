package com.dsa.brute.force.geometry;

import com.dsa.utils.Point;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ComputationalGeometry {

  public static double distance(Point p, Point q) {
    return Math.sqrt(Math.pow(q.x() - p.x(), 2) + Math.pow(q.y() - p.y(), 2));
  }

  public static Pair<Point, Point> closestPair(List<Point> points) {
    if (points.size() < 2) {
      throw new IllegalStateException("At least two points are required");
    }
    double minDistance = distance(points.get(0), points.get(1));
    Pair<Point, Point> result = Pair.of(points.get(0), points.get(1));

    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        double d = distance(points.get(i), points.get(j));
        if (d < minDistance) {
          minDistance = d;
          result = Pair.of(points.get(i), points.get(j));
        }
      }
    }
    return result;
  }


}
