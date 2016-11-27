import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		boolean[][] f = new boolean [2178][2178];
		int[] pow3 = { 1, 3, 9, 27, 81, 243, 729, 2178 };
		for (int i = 0; i < 2178; i++)
			for (int j = 0; j < 2178; j++) {
				boolean ok = true;
				for (int a = i, b = j, d = 729, k = 0; k < 7; a %= d, b %= d, d /= 3, k++)
					if (((a / d + b / d) & 1) == 1) {
						ok = false;
						break;
					}
				f[i][j] = ok;
			}
		PrintWriter out = new PrintWriter(System.out);
		for (int N = nextInt(); N >= 0; out.println('-'), N = nextInt())
			if (N > 0)
				for (int l = 0, L = pow3[N - 1]; l < L; l++) {
					int last = L - 1;
					for (; last >= 0 && !f[l][last]; last--);
					for (int i = 0; i <= last; i++)
						out.print(f[l][i] ? 'X' : ' ');
					out.println();
				}
		out.close();
	}
	
	static int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
