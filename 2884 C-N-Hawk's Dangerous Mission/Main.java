import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

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
	
	int MAXSZ = 50;
	
	int R;
	int C;
	char[][] map = new char [MAXSZ][];
	char[][] cmap = new char [MAXSZ][];
	int[][] used = new int [MAXSZ][MAXSZ];
	int tick = 1;
	
	int[] dr = { -1, 0, 1, 0 };
	int[] dc = { 0, -1, 0, 1 };
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			R = nextInt();
			C = nextInt();
			if (R == 0 && C == 0)
				break;
			for (int i = 0; i < R; i++)
				map[i] = nextToken().toCharArray();
			double left = 0.0;
			double right = R * R + C * C;
			double ans = 0.0;
			for (int it = 0; it < 48; it++) {
				double mid = (left + right) * 0.5;
				if (can(mid)) {
					ans = max(ans, mid);
					left = mid;
				} else {
					right = mid;
				}
			}
			out.printf(Locale.US, "%.4f%n", sqrt(ans));
		}

		out.close();
	}
	
	boolean can(double md) {
		tick++;
		buildCurMap(md);
		for (int c = 0; c < C; c++)
			if (ok(0, c) && dfs(0, c))
				return true;
		return false;
	}

	void buildCurMap(double md) {
		for (int r = 0; r < R; r++) {
			cmap[r] = new char [C];
			fill(cmap[r], '.');
		}
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if (map[r][c] == '@')
					mark(r, c, md);
	}

	void mark(int cr, int cc, double md) {
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if (cmap[r][c] == '.' && (r - cr) * (r - cr) + (c - cc) * (c - cc) < md)
					cmap[r][c] = '@';
	}

	boolean dfs(int r, int c) {
		if (!ok(r, c))
			return false;
		if (r == R - 1)
			return true;
		used[r][c] = tick;
		for (int d = 0; d < 4; d++)
			if (dfs(r + dr[d], c + dc[d]))
				return true;
		return false;
	}

	boolean ok(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C && cmap[r][c] == '.' && used[r][c] != tick;
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
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
