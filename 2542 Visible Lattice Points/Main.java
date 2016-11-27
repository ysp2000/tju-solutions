import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] ans = new int [1000 + 1];
		ans[1] = 3;
		for (int x = 2; x <= 1000; x++)
			for (int y = 1; y < x; y++)
				if (gcd(x, y) == 1)
					ans[x]++;
		for (int x = 2; x <= 1000; x++)
			ans[x] = (ans[x] << 1) + ans[x - 1];
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(), t = 1, n; T --> 0;)
			out.println(t++ + " " + (n = nextInt()) + " " + ans[n]);
		out.close();
	}

	static int gcd(int x, int y) {
		while (x > 0 && y > 0)
			if (x > y)
				x %= y;
			else
				y %= x;
		return x + y;
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
