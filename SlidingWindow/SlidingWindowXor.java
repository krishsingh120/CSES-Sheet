import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SlidingWindowXor {
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
    long[] window = new long[k]; // circular buffer
    int idx = 0;

    long xor = 0;
    long windowXor = 0;

    for (int i = 0; i < k; i++) {
      window[i] = curr;
      windowXor ^= curr;

      curr = (a * curr + b) % c;
    }

    xor ^= windowXor;

    for (int i = k; i < n; i++) {
      long outgoing = window[idx];
      windowXor ^= outgoing;

      window[idx] = curr;
      windowXor ^= curr;

      xor ^= windowXor;

      idx = (idx + 1) % k;

      curr = (a * curr + b) % c;
    }

    System.out.print(xor);
  }
}
