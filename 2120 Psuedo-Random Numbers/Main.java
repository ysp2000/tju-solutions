import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		
		int[] used = new int [10000];
		int[] nums = new int [10000];
		PrintWriter out = new PrintWriter(System.out);
		for (int tick = 1;;) {
			int Z = nextInt(), I = nextInt(), M = nextInt(), L = nextInt(), ans = 0, k = 0;
			if (M == 0)
				break;
			for (k = 0; used[L] != tick; nums[L] = k++, used[L] = tick, L = (Z * L + I) % M, ans++);
			out.print("Case "); out.print(tick++); out.print(": "); out.println(ans - nums[L]);
		}
		out.close();
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
