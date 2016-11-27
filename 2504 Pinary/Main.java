import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] F = new int [39];
		F[0] = F[1] = 1;
		for (int i = 2; i < 39; i++)
			F[i] = F[i - 2] + F[i - 1];
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			for (int N = nextInt(), k = 38, p = 0; k > 0; k--)
				if (N >= F[k]) {
					p = 1;
					out.print('1');
					N -= F[k];
				} else if (p == 1)
					out.print('0');
			out.println();
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
