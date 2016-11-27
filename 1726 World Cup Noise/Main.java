import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] dp0 = new int [45 + 1];
		int[] dp1 = new int [45 + 1];
		dp0[0] = 1;
		for (int i = 1; i <= 45; i++)
			dp0[i] = (dp1[i] = dp0[i - 1]) + dp1[i - 1];
		PrintWriter out = new PrintWriter(System.out);
		for (int t = 1, T = nextInt(), n; T --> 0;)
			out.println("Scenario #" + t++ + ":\n" + (dp0[n = nextInt()] + dp1[n]) + "\n");
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
