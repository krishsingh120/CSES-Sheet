import java.io.*;

public class SlidingWindowOr {

  static class AggQueue {
    long[] inVal, inAgg;
    long[] outVal, outAgg;
    int inSize = 0, outSize = 0;

    AggQueue(int n) {
      inVal = new long[n];
      inAgg = new long[n];
      outVal = new long[n];
      outAgg = new long[n];
    }

    void push(long x) {
      inVal[inSize] = x;
      inAgg[inSize] = (inSize == 0) ? x : (inAgg[inSize - 1] | x);
      inSize++;
    }

    void transfer() {
      while (inSize > 0) {
        long val = inVal[--inSize];
        outVal[outSize] = val;
        outAgg[outSize] = (outSize == 0) ? val : (outAgg[outSize - 1] | val);
        outSize++;
      }
    }

    void pop() {
      if (outSize == 0)
        transfer();
      outSize--;
    }

    long query() {
      long res = 0;
      if (inSize > 0)
        res |= inAgg[inSize - 1];
      if (outSize > 0)
        res |= outAgg[outSize - 1];
      return res;
    }
  }

  // Fast input
  static class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0, len = 0;

    private int read() throws IOException {
      if (ptr >= len) {
        len = in.read(buffer);
        ptr = 0;
        if (len <= 0)
          return -1;
      }
      return buffer[ptr++];
    }

    long nextLong() throws IOException {
      int c;
      while ((c = read()) <= ' ')
        ;
      long sign = 1;
      if (c == '-') {
        sign = -1;
        c = read();
      }
      long val = c - '0';
      while ((c = read()) > ' ') {
        val = val * 10 + c - '0';
      }
      return val * sign;
    }
  }

  public static void main(String[] args) throws Exception {
    FastScanner sc = new FastScanner();

    int n = (int) sc.nextLong();
    int k = (int) sc.nextLong();

    long x = sc.nextLong();
    long a = sc.nextLong();
    long b = sc.nextLong();
    long c = sc.nextLong();

    AggQueue q = new AggQueue(k);

    long curr = x;
    long xor = 0;

    // first window
    for (int i = 0; i < k; i++) {
      q.push(curr);
      curr = (a * curr + b) % c;
    }

    xor ^= q.query();

    // sliding
    for (int i = k; i < n; i++) {
      q.pop();
      q.push(curr);
      xor ^= q.query();
      curr = (a * curr + b) % c;
    }

    System.out.println(xor);
  }
}