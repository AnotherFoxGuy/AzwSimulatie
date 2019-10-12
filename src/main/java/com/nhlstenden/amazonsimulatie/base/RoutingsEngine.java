package com.nhlstenden.amazonsimulatie.base;

import com.nhlstenden.amazonsimulatie.models.Grid;
import com.nhlstenden.amazonsimulatie.models.Node;

import java.util.*;

public class RoutingsEngine {
  private Grid grid;
  private Node start;
  private Node end;
  private Node current;
  private Queue<Node> frontier = new LinkedList<>();
  private Deque<Node> path = new LinkedList<>();
  private HashMap<Node, Node> cameFrom = new HashMap<>();

  public RoutingsEngine(Grid grid) {
    this.grid = grid;
  }

  public Deque<Node> generateRoute(Node start, Node end) {
    frontier.clear();
    path.clear();
    cameFrom.clear();
    this.start = start;
    this.end = end;
    frontier.add(this.start);
    cameFrom.put(this.start, null);
    scanGrid();
    return getPath();
  }

  private void scanGrid() {
    while (!frontier.isEmpty()) {
      current = frontier.remove();
      if (current == end) {
        break;
      }
      for (Node next : grid.getNeighbours(current)) {
        if (!cameFrom.containsKey(next)) {
          frontier.add(next);
          cameFrom.put(next, current);
        }
      }
    }
  }

  private Deque<Node> getPath() {
    current = end;
    while (current != start) {
      if(current == null)
        break;
      path.addFirst(current);
      current = cameFrom.get(current);
    }
    return path;
  }
}