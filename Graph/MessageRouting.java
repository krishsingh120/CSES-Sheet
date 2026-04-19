import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MessageRouting {

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
        
        // OPTIMIZATION 3: Use PrintWriter for fast bulk output
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // OPTIMIZATION 1: Use Array of ArrayLists instead of ArrayList<ArrayList<Integer>>
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] vis = new boolean[n + 1];
        int[] pathVis = new int[n + 1];

        int start = 1;
        int end = n;

        // ORIGINAL QUEUE: As requested, kept LinkedList without optimization
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        boolean isFound = false;

        while (!q.isEmpty() && !isFound) {
            int node = q.poll();

            for (int it : adj[node]) {
                if (!vis[it]) {
                    vis[it] = true;
                    pathVis[it] = node;
                    q.add(it);

                    if (it == end) {
                        isFound = true;
                        break;
                    }
                }
            }
        }

        if (!isFound) {
            out.println("IMPOSSIBLE");
            out.flush();
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();
        int curr = end;

        while (curr != 0) {
            path.add(curr);
            curr = pathVis[curr];
        }

        out.println(path.size());
        Collections.reverse(path);

        // Buffer all outputs into the PrintWriter
        for (int x : path) {
            out.print(x + " ");
        }

        // Flush the output stream at the very end to print everything at once
        out.println();
        out.flush();
    }
}