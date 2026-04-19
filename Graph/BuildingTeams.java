import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BuildingTeams {

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
    // PrintWriter is much faster for large output
    PrintWriter out = new PrintWriter(System.out);

    int n = sc.nextInt();
    int m = sc.nextInt();

    ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    boolean vis[] = new boolean[n + 1];
    int colors[] = new int[n + 1];
    Arrays.fill(colors, -1);

    for (int i = 1; i <= n; i++) {
      if (!vis[i]) {
        if (bfs(i, adj, colors, vis)) {
          System.out.println("IMPOSSIBLE");
          return;
        }
      }
    }

    // Use a StringBuilder to construct the final output string
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= n; i++) {
      sb.append(colors[i]).append(" ");
    }
    out.println(sb.toString().trim());
    out.close(); // Don't forget to close or flush the PrintWriter
  }

  public static boolean bfs(int start, ArrayList<ArrayList<Integer>> adj, int colors[], boolean vis[]) {
    Queue<Integer> q = new ArrayDeque<>();
    vis[start] = true;
    colors[start] = 1;
    q.add(start);

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int it : adj.get(curr)) {
        if (!vis[it]) {
          vis[it] = true;
          // Alternates between 1 and 2
          colors[it] = 3 - colors[curr];
          q.add(it);
        } else if (colors[it] == colors[curr]) {
          return true; // Not Bipartite
        }
      }
    }
    return false;
  }
}