import java.util.*;

public class countingRoom {

  public static int counting(char grid[][], int n, int m) {
    boolean vis[][] = new boolean[n][m];
    int count = 0;

    // Bfs approach
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!vis[i][j] && grid[i][j] == '.') {
          count++;
          bfs(i, j, grid, vis);
        }
      }
    }

    // // Dfs approach -> stackOverflow
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // if (!vis[i][j] && grid[i][j] == '.') {
    // count++;
    // dfs(i, j, grid, vis);
    // }
    // }
    // }

    return count;
  }

  public static void bfs(int r, int c, char grid[][], boolean vis[][]) {
    int n = grid.length;
    int m = grid[0].length;

    int drow[] = { -1, 0, 1, 0 };
    int dcol[] = { 0, 1, 0, -1 };

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { r, c });
    vis[r][c] = true;

    while (!q.isEmpty()) {
      int row = q.peek()[0];
      int col = q.peek()[1];

      q.poll();

      for (int k = 0; k < 4; k++) {
        int nr = row + drow[k];
        int nc = col + dcol[k];

        if (nr >= 0 && nr <= n - 1 && nc >= 0 && nc <= m - 1 && grid[nr][nc] == '.' && !vis[nr][nc]) {
          vis[nr][nc] = true;
          q.add(new int[] { nr, nc });
        }
      }

    }

  }

  public static void dfs(int r, int c, char grid[][], boolean vis[][]) {
    int n = grid.length;
    int m = grid[0].length;
    vis[r][c] = true;

    int drow[] = { 0, 1, 0, -1 };
    int dcol[] = { -1, 0, 1, 0 };

    for (int k = 0; k < 4; k++) {
      int nr = r + drow[k];
      int nc = c + dcol[k];

      if (nr >= 0 && nr <= n - 1 && nc >= 0 && nc <= m - 1 && grid[nr][nc] == '.' && !vis[nr][nc]) {
        dfs(nr, nc, grid, vis);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    sc.nextLine();

    char[][] grid = new char[n][m];

    for (int i = 0; i < n; i++) {
      String line = sc.nextLine(); // read full row like "#..#...#"
      for (int j = 0; j < m; j++) {
        grid[i][j] = line.charAt(j); // convert string → char
      }
    }

    int ans = counting(grid, n, m);
    System.out.println(ans);

  }
}
