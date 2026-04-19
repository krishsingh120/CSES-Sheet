import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowMode {

  /*
   * 1 2 3 2 5 2 4 4
   */

  public static void distArr(int n, int k, int nums[], int ans[]) {
    

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] first = br.readLine().split(" ");

    int n = Integer.parseInt(first[0]);
    int k = Integer.parseInt(first[1]);

    int arr[] = new int[n];
    String[] nums = br.readLine().split(" ");

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(nums[i]);
    }

    int ans[] = new int[n - k + 1];

    distArr(n, k, arr, ans);

    StringBuilder sb = new StringBuilder();
    for (int x : ans) {
      sb.append(x).append(" ");
    }

    System.out.println(sb);
  }
}
