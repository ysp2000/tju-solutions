import java.io.*;
import java.util.*;
import static java.util.Arrays.fill;

public class Main {
	int INF = Integer.MAX_VALUE >> 1;

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
	
	int[][] graph = new int [26][26];
	int vNum;
	
	int[] prev = new int [26];
	boolean[] used = new boolean [26];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 1; t <= T; t++) {
			vNum = nextInt();
			for (int i = 0; i < vNum; i++)
				for (int j = 0, x; j < vNum; j++)
					graph[i][j] = (x = nextInt()) != 0 ? x : INF;
			prim();
			out.println("Case " + t + ":");
			for (int v = 1; v < vNum; v++)
				out.println(label(prev[v]) + "-" + label(v) + " " + graph[prev[v]][v]);
		}
		
		out.close();
	}
	
	char label(int v) {
		return (char) ('A' + v);
	}

	void prim() {
		fill(prev, -1);
		fill(used, false);
		
		for (int v = 0; v != -1;) {
			used[v] = true;
			for (int i = 0; i < vNum; i++)
				if (!used[i] && (prev[i] == -1 || graph[prev[i]][i] > graph[v][i]))
					prev[i] = v;
			v = -1;
			for (int i = 0; i < vNum; i++)
				if (!used[i] && prev[i] != -1 && (v == -1 || graph[prev[v]][v] > graph[prev[i]][i]))
					v = i;
		}
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine(), " ,");
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
