import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            if (current.dist > dist[u]) continue;

            for (Edge edge : adj[u]) {
                int v = edge.to;
                long weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        if (dist[n] == Long.MAX_VALUE) {
            System.out.println("-1");
        } else {
            List<Integer> path = new ArrayList<>();
            for (int curr = n; curr != -1; curr = parent[curr]) {
                path.add(curr);
            }

            Collections.reverse(path);

            StringBuilder sb = new StringBuilder();
            for (int node : path) {
                sb.append(node).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}