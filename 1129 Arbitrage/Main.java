import java.io.*;
import java.util.*;

import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int vNum;
	Map<String, Integer> map = new HashMap<String, Integer>();
	double[][] g = new double [30][30];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int N = nextInt(), test = 1; N > 0; N = nextInt()) {
			vNum = 0;
			for (int i = 0; i < N; i++) {
				fill(g[i], 0, N, 0.0);
				g[i][i] = 1.0;
				map.put(nextToken(), vNum++);
			}
			boolean ok = false;
			for (int M = nextInt(), i = 0; i < M; i++) {
				int u = getVertex(nextToken());
				double w = nextDouble();
				int v = getVertex(nextToken());
				if (u == v && w > 1.0)
					ok = true;
				g[u][v] = Math.max(g[u][v], w);
			}
			if (!ok)
				for (int k = 0; k < N; k++)
					for (int i = 0; i < N; i++)
						for (int j = 0; j < N; j++)
							g[i][j] = Math.max(g[i][j], g[i][k] * g[k][j]);
			for (int i = 0; i < N; i++)
				if (g[i][i] > 1.0) {
					ok = true;
					break;
				}
			out.print("Case ");
			out.print(test++);
			out.println(ok ? ": Yes" : ": No");
		}
		out.close();
	}

	int getVertex(String s) {
		return map.get(s);
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
