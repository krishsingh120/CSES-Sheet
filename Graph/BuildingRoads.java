import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BuildingRoads {

  // Fast I/O Class to replace Scanner
  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static void main(String[] args) {
    FastScanner sc = new FastScanner();
    int n = sc.nextInt();
    int m = sc.nextInt();

    // 1. Initialize Adjacency List directly to save memory/time
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    // 2. Read edges and build undirected graph
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    ArrayList<Integer> components = new ArrayList<>();
    boolean[] vis = new boolean[n + 1];

    // 3. Find connected components using your BFS
    for (int i = 1; i <= n; i++) {
      if (!vis[i]) {
        components.add(i);
        bfs(i, adj, vis);
      }
    }

    // 4. Fast Output using StringBuilder
    StringBuilder out = new StringBuilder();
    int k = components.size();

    out.append(k - 1).append("\n"); // Number of roads required

    for (int i = 1; i < k; i++) {
      out.append(components.get(i - 1)).append(" ").append(components.get(i)).append("\n");
    }

    // Print everything at once
    System.out.print(out);
  }

  public static void bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    vis[start] = true;

    while (!q.isEmpty()) {
      int node = q.poll();

      for (int it : adj.get(node)) {
        if (!vis[it]) {
          vis[it] = true;
          q.add(it);
        }
      }
    }
  }
}