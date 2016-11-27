import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

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
	
	int tick = 1;
	int N;
	int[] color;
	int[][] graph = new int [20][20];
	
	int WHITE = 0;
	int GREY = 1;
	int BLACK = 2;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 0; t < T; tick++, t++) {
			N = nextInt();
			
			for (int m = nextInt(), i = 0; i < m; i++) {
				int u = nextInt() - 1;
				int v = nextInt() - 1;
				graph[u][v] = tick;
			}
			
			color = new int [20];
			boolean ok = true;
			for (int i = 0; i < N; i++)
				if (color[i] == WHITE)
					if (!dfs(i)) {
						ok = false;
						break;
					}
			out.println(ok ? 1 : 0);	
		}
		
		out.close();
	}
	
	boolean dfs(int v) {
		color[v] = GREY;
		for (int i = 0; i < N; i++)
			if (graph[v][i] == tick) {
				if (color[i] == GREY)
					return false;
				if (color[i] == WHITE)
					if (!dfs(i))
						return false;
			}
		color[v] = BLACK;
		return true;
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
