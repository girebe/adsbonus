# Bonus Task — Dijkstra's Algorithm (Shortest Path)

## Overview

This bonus task extends the existing graph implementation to support **weighted edges** and implements **Dijkstra's Algorithm** to find the shortest path from a starting vertex to all other vertices in the graph.

---

## What Was Added / Changed

| Component | Change |
|-----------|--------|
| `Edge` class | Added a `weight` field to store edge cost |
| `Graph` class | Updated adjacency list to store `Edge` objects (with weight) |
| `addEdge(src, dest, weight)` | Now accepts a weight parameter |
| `dijkstra(int start)` | **New method** — computes shortest paths from `start` |
| `minDistance(dist, visited)` | Helper — picks the nearest unvisited vertex |
| `printResults(start, dist)` | Prints a formatted distance table |

---

## How Dijkstra's Algorithm Works

1. **Initialise** — Set the distance to the start vertex as `0` and all others as `∞`.
2. **Repeat** (for every vertex):
   - Pick the **unvisited** vertex `u` with the **smallest known distance**.
   - Mark `u` as visited.
   - For each neighbour `v` of `u`: if `dist[u] + weight(u,v) < dist[v]`, update `dist[v]` (**relaxation**).
3. **Output** — After all vertices are processed, `dist[i]` holds the shortest distance from `start` to vertex `i`.

> No priority queue is used — the minimum vertex is found with a simple linear scan (`minDistance` helper), as allowed by the task requirements.

---

## Data Structures Used

- **Adjacency List** — `List<List<Edge>>` where each `Edge` stores `(destination, weight)`.
- **`int[] dist`** — Tracks the shortest known distance to each vertex.
- **`boolean[] visited`** — Tracks which vertices have been finalised.

---

## Example Graph

```
0 --4-- 1
|       |
1       1
|       |
2 --2-- 1
 \      |
  5     3
   \    |
    3   4
     \  |
      \ |
       3--6--5
        \   /
         3-2
```

Edges used in the demo:

| From | To | Weight |
|------|----|--------|
| 0 | 1 | 4 |
| 0 | 2 | 1 |
| 2 | 1 | 2 |
| 1 | 3 | 1 |
| 2 | 3 | 5 |
| 3 | 4 | 3 |
| 4 | 5 | 2 |
| 3 | 5 | 6 |

---

## Sample Output

```
Dijkstra's Shortest Paths from vertex 0:
------------------------------------------
Vertex     Distance       
------------------------------------------
0          0              
1          3              
2          1              
3          4              
4          7              
5          9              
------------------------------------------
```

---

## How to Run

```bash
# Compile
javac Graph.java

# Run
java Graph
```

No external libraries or build tools required — pure Java.

---

## Complexity

| Measure | Value |
|---------|-------|
| Time | O(V²) — linear scan for minimum |
| Space | O(V + E) — adjacency list |

A priority queue would improve time to O((V + E) log V), but a simple loop is used as specified in the task.