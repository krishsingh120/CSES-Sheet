import java.io.*;
import java.util.*;

public class SlidingWindowDist {

  /*
   * 1 2 3 2 5 2 2 2
   */

  public static void distArr(int n, int k, int nums[], int ans[]) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < k; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    ans[0] = map.size();
    int i = 0, j = k - 1;

    while (j < n - 1) {
      j++;

      map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
      map.put(nums[i], map.getOrDefault(nums[i], 0) - 1);

      if (map.get(nums[i]) == 0) {
        map.remove(nums[i]);
      }
      i++;

      ans[i] = map.size();

    }

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
