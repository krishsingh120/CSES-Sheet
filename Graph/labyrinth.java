import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class labyrinth {

  public static boolean path(char grid[][], int n, int m, StringBuilder sb) {

    // Bfs approach
    int A[] = new int[2];
    int B[] = new int[2];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 'A') {
          A[0] = i;
          A[1] = j;
        }
        if (grid[i][j] == 'B') {
          B[0] = i;
          B[1] = j;
        }
      }
    }

    if (bfs(A, B, grid, sb))
      return true;

    return false;
  }

  public static boolean bfs(int A[], int B[], char grid[][], StringBuilder sb) {
    int n = grid.length;
    int m = grid[0].length;

    boolean vis[][] = new boolean[n][m];
    char prevDir[][] = new char[n][m];

    int drow[] = { -1, 0, 1, 0 };
    int dcol[] = { 0, 1, 0, -1 };
    char pathDir[] = { 'U', 'R', 'D', 'L' };
    int r = A[0];
    int c = A[1];

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { r, c });
    vis[r][c] = true;
    boolean found = false;

    while (!q.isEmpty() && !found) {
      int row = q.peek()[0];
      int col = q.peek()[1];

      q.poll();

      for (int k = 0; k < 4; k++) {
        int nr = row + drow[k];
        int nc = col + dcol[k];

        if (nr >= 0 && nr <= n - 1 && nc >= 0 && nc <= m - 1 && grid[nr][nc] != '#' && !vis[nr][nc]) {

          vis[nr][nc] = true;
          prevDir[nr][nc] = pathDir[k];
          q.add(new int[] { nr, nc });

          if (nr == B[0] && nc == B[1]) {
            found = true;
            break;
          }
        }
      }

    }

    if (!found)
      return false;

    // Path reconstruct
    int curr[] = B;
    while (curr[0] != A[0] || curr[1] != A[1]) {
      char ch = prevDir[curr[0]][curr[1]];
      sb.append(ch);

      int idx = -1;
      for (int i = 0; i < 4; i++) {
        if (pathDir[i] == ch) {
          idx = i;
        }
      }

      curr[0] -= drow[idx];
      curr[1] -= dcol[idx];

    }

    sb.reverse();

    return true;

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
    StringBuilder sb = new StringBuilder("");

    boolean ans = path(grid, n, m, sb);

    if (ans) {
      System.out.println("YES");
      System.out.println(sb.length());
      System.out.println(sb.toString());
    } else {
      System.out.println("NO");
    }
  }
}
