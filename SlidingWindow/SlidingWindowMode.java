import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
* Approach:
  1. req:
        FreqMap: <Elem, Freq>
        Inv FreqMap: <freq, Set<elem>>

  2. first handle removing element freq in both maps.
  3. then handle upcoming element freq in both maps.
  4. Ans => Last freq's first element.

*/

public class SlidingWindowMode {

  /*
   * 1 2 3 2 5 2 4 4
   */

  public static void distArr(int n, int k, int nums[], int ans[]) {

    Map<Integer, Integer> freq = new HashMap<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> {
          if (a[0] != b[0])
            return b[0] - a[0]; // max freq first
          return a[1] - b[1]; // smaller element first
        });

    // first window
    for (int i = 0; i < k; i++) {
      freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
    }

    // push all into heap
    for (int key : freq.keySet()) {
      pq.add(new int[] { freq.get(key), key });
    }

    ans[0] = getMode(pq, freq);

    // sliding window
    for (int i = k; i < n; i++) {

      int out = nums[i - k];
      freq.put(out, freq.get(out) - 1);
      if (freq.get(out) == 0)
        freq.remove(out);
      else
        pq.add(new int[] { freq.get(out), out });

      int in = nums[i];
      freq.put(in, freq.getOrDefault(in, 0) + 1);
      pq.add(new int[] { freq.get(in), in });

      ans[i - k + 1] = getMode(pq, freq);
    }
  }

  // lazy removal
  private static int getMode(PriorityQueue<int[]> pq, Map<Integer, Integer> freq) {
    while (true) {
      int[] top = pq.peek();
      int f = top[0];
      int val = top[1];

      if (freq.getOrDefault(val, 0) == f) {
        return val;
      }
      pq.poll(); // remove stale
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
