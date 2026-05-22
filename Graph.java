import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    // --- Edge class with weight ---
    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private final int vertices;
    private final List<List<Edge>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add a directed weighted edge
    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // undirected
    }

    // Dijkstra's Algorithm
    public void dijkstra(int start) {
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            // Pick the unvisited vertex with the smallest distance
            int u = minDistance(dist, visited);
            visited[u] = true;

            // Relax all neighbors of u
            for (Edge edge : adjList.get(u)) {
                int v = edge.destination;
                int w = edge.weight;

                if (!visited[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        printResults(start, dist);
    }

    // Helper: find vertex with minimum distance that hasn't been visited
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Print shortest distances from source
    private void printResults(int start, int[] dist) {
        System.out.println("Dijkstra's Shortest Paths from vertex " + start + ":");
        System.out.println("------------------------------------------");
        System.out.printf("%-10s %-15s%n", "Vertex", "Distance");
        System.out.println("------------------------------------------");
        for (int i = 0; i < vertices; i++) {
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(dist[i]);
            System.out.printf("%-10d %-15s%n", i, distStr);
        }
        System.out.println("------------------------------------------");
    }

    // Main method — demo
    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 1, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 5);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 5, 2);
        g.addEdge(3, 5, 6);

        g.dijkstra(0);
    }
}
