import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

public class Main {
	double INF = 1e50;

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
	
	double[][] g = new double [100][100];
	double[] q = new double [100];
	int[] u = new int [100];
	int tick = 0;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int N = nextInt(); N > 0; N = nextInt()) {
			int M = nextInt();
			for (int i = 0; i < N; i++) 
				fill(g[i], 0, N, 1.0);
			for (int i = 0; i < M; i++) {
				int u = nextInt() - 1;
				int v = nextInt() - 1;
				g[u][v] = g[v][u] = 1.0 - (nextInt() / 100.0);
			}
			tick++;
			fill(q, 1.0);
			q[0] = 0.0;
			for (;;) {
				int v = -1;
				for (int i = 0; i < N; i++)
					if (u[i] != tick && q[i] < 1.0 && (v == -1 || q[i] < q[v]))
						v = i;
				if (v == -1 || v == N - 1)
					break;
				u[v] = tick;
				for (int i = 0; i < N; i++)
					if (u[i] != tick)
						q[i] = min(q[i], q[v] + (1.0 - q[v]) * g[v][i]);
			}
			out.printf(Locale.US, "%.6f percent%n", (1.0 - q[N - 1]) * 100.0);
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
}
