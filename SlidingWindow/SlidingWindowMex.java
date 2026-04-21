import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/*
* Approach:
  1. req:
        Set<oderSet> => PriorityQueue to maintain min element
        FreqMap: <int, int>

  2. Keep track of unseen element of each window.
  3. Keep track of seen element of each window.
  4. Smallest element in the unseen set is the ans of that window.

*/

public class SlidingWindowMex {

  /*
   * arr: -> 1 2 1 0 5 1 1 0
   */

  public static void distArr(int n, int k, int nums[], int ans[]) {
    Map<Integer, Integer> map = new HashMap<>();
    TreeSet<Integer> unseen = new TreeSet<>();

    // MEX range → [0..k]
    for (int i = 0; i <= k; i++) {
      unseen.add(i);
    }

    // first window
    for (int i = 0; i < k; i++) {
      int val = nums[i];

      map.put(val, map.getOrDefault(val, 0) + 1);

      if (val <= k) {
        unseen.remove(val); // now it's present
      }
    }

    ans[0] = unseen.first();

    int i = 0, j = k;

    while (j < n) {

      // remove outgoing
      int out = nums[i];
      map.put(out, map.get(out) - 1);

      if (map.get(out) == 0) {
        map.remove(out);
        if (out <= k)
          unseen.add(out);
      }

      // add incoming
      int in = nums[j];
      map.put(in, map.getOrDefault(in, 0) + 1);

      if (in <= k) {
        unseen.remove(in);
      }

      ans[i + 1] = unseen.first();

      i++;
      j++;
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
