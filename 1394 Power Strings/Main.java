import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("D.0.dat"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int[] s = new int [2000010];
		int[] pi = new int [2000010];
		for (int n, c;;) {
			for (n = 0, c = in.read(); c != 10; s[n++] = c, c = in.read());
			if (n <= 1 && s[0] == '.')
				break;
			for (int i = 1, j; i < n; i++) {
				for (j = pi[i - 1]; j > 0 && s[i] != s[j]; j = pi[j - 1]);
				pi[i] = j + (s[i] == s[j] ? 1 : 0);
			}
			int k = n - pi[n - 1];
			out.println(n % k == 0 ? n / k : 1);
		}
		out.close();
	}
}
