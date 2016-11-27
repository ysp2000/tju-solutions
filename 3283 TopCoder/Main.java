import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	double EPS = 1e-6;
	
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("Gen.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int MAXN = 30;
	int MAXV = 10;
	
	int N;
	int FULL_MASK;
	double[] X = new double [MAXN + 1];
	double[] Y = new double [MAXN + 1];
	double[] Z = new double [MAXN + 1];
	double[][] graph = new double [MAXN + 1][MAXN + 1];
	
	double needDist;
	double minEdge;
	Map<Integer, Double> map = new HashMap<Integer, Double>();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		while (!EOF()) {
			N = nextInt() + 1;
			FULL_MASK = (1 << N) - 1;
			for (int i = 1; i < N; i++) {
				X[i] = nextDouble();
				Y[i] = nextDouble();
				Z[i] = nextDouble();
				for (int j = 0; j < i; j++)
					graph[i][j] = graph[j][i] = sqrt(sqr(X[i] - X[j]) + sqr(Y[i] - Y[j]) + sqr(Z[i] - Z[j]));
			}
			for (int k = 0; k < N; k++)
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++)
						graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
			minEdge = 1e100;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					minEdge = min(minEdge, graph[i][j]);
			needDist = nextDouble();
			map.clear();
			out.println(dfs(0, 1, 0.0) ? "YES" : "NO");
		}
		
		out.close();
	}
	
	boolean dfs(int v, int mask, double curDist) {
		if (mask == FULL_MASK && curDist < needDist)
			return true;
		if (curDist + minEdge * N - Integer.bitCount(mask) > needDist * 0.97)
			return false;
		if (Integer.bitCount(mask) <= MAXV) {
			if (map.containsKey(mask) && map.get(mask) > curDist)
				return false;
			map.put(mask, curDist);
		}
		int used = mask;
		for (int k = 0; k < (N + 2) / 3; k++) {
			int min = -1;
			for (int nv = 2; nv < N; nv++)
				if ((used & (1 << nv)) == 0 && (min == -1 || graph[v][nv] < graph[v][min]))
					min = nv;
			if (min == -1)
				break;
			used |= (1 << min);
			if (dfs(min, mask | (1 << min), curDist + graph[v][min]))
				return true;
		}
		return false;
	}

	double sqr(double x) {
		return x * x;
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
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
}
