import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	double[][] p = new double [5][5];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				p[i][j] = nextDouble();

		int[] seq = new int [10];
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			for (int i = 0; i < n; i++)
				seq[i] = nextInt();
			double m = nextDouble();
			int prv = 0;
			for (int i = 0; i < n; i++) {
				int nxt = seq[i] - 1;
				m = Math.round(100.0 * m * p[prv][nxt]) / 100.0;
				prv = nxt;
			}
			m *= p[prv][0];
			out.printf(Locale.US, "%.2f%n", m);
		}
		
		out.close();
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
