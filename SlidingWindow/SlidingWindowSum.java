import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.ArrayDeque;

public class SlidingWindowSum {

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
    Deque<Long> d = new ArrayDeque<>();

    long sum = 0;
    long xor = 0;

    for (int i = 0; i < n; i++) {
      // curr is x_i
      // use curr here

      // add current element
      d.addLast(curr);
      sum += curr;

      // when window size == k
      if (d.size() == k) {
        xor ^= sum;

        // remove oldest
        long removed = d.pollFirst();
        sum -= removed;
      }

      curr = (a * curr + b) % c;

    }

    System.out.print(xor);
  }
}
