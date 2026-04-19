import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SlidingWindowMin {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int k = sc.nextInt();

    long x = sc.nextLong();
    long a = sc.nextLong();
    long b = sc.nextLong();
    long c = sc.nextLong();

    // now you can use generator
    long curr = x;
    Deque<long[]> dq = new ArrayDeque<>();

    long xor = 0;

    for (int i = 0; i < n; i++) {
      // curr is x_i
      // use curr here

      // add current element => remove all > curr

      while (!dq.isEmpty() && dq.peekLast()[1] > curr) {
        dq.pollLast();
      }
      // 2. add current (index, value)
      dq.addLast(new long[] { i, curr });

      // 3. remove out-of-window elements
      if (!dq.isEmpty() && dq.peekFirst()[0] <= i - k) {
        dq.pollFirst();
      }

      // 4. window formed → take min
      if (i >= k - 1) {
        xor ^= dq.peekFirst()[1];
      }

      curr = (a * curr + b) % c;

    }

    System.out.print(xor);
  }
}
