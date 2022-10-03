import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

class Edge {
    int source, dest, weight;

    public Edge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

class Solution {

    public static void bellmanFord(List<Edge> edges, int source, int n, int dest) {

        int distance[] = new int[n];
        int parent[] = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        Arrays.fill(parent, -1);

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {

                int u = edge.source;
                int v = edge.dest;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {

                    distance[v] = distance[u] + w;

                    parent[v] = u;
                }
            }
        }

        for (Edge edge : edges) {

            int u = edge.source;
            int v = edge.dest;
            int w = edge.weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Negative-weight cycle is found!!");
                return;
            }
        }

        List<Integer> path = new ArrayList<>();
        System.out.println("A shortest path from " + source + " to " + dest + " has length:" + distance[dest]);

    }

    public static void main(String[] args) throws FileNotFoundException {

        List<Edge> edges = new ArrayList<Edge>();
        Scanner scanner1 = new Scanner(new File("graphInput.txt"));
        while (scanner1.hasNext()) {
            String path[] = scanner1.nextLine().split(" ");
            int so = Integer.parseInt(path[0]);
            int des = Integer.parseInt(path[1]);
            int wei = Integer.parseInt(path[2]);
            Edge e = new Edge(so, des, wei);
            edges.add(e);

        }
        scanner1.close();

        Scanner scanner2 = new Scanner(new File("shortestPaths.txt"));
        while (scanner2.hasNext()) {
            String path2[] = scanner2.nextLine().split(" ");
            int so2 = Integer.parseInt(path2[0]);
            int des2 = Integer.parseInt(path2[1]);
            ;
            bellmanFord(edges, so2, 10, des2);

        }
        scanner2.close();

    }
}