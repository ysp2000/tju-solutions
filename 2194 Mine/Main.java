import java.io.*;
import java.util.*;
import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "1", 1L << 24).start();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int N;
	int[][] a;
	boolean[][] u;
	
	int[] dx = { -1, -1, -1,  0,  0,  1,  1,  1 };
	int[] dy = { -1,  0,  1, -1,  1, -1,  0,  1};
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (N = nextInt(); N > 0; N = nextInt()) {
			int M = nextInt();
			a = new int [N + 2][N + 2];
			u = new boolean [N + 2][N + 2];
			for (int i = 0; i < M; i++)
				a[nextInt()][nextInt()] = -1;
			int x = nextInt();
			int y = nextInt();
			fill(u[0], true);
			for (int i = 1; i <= N; i++)
				u[i][0] = u[i][N + 1] = true;
			fill(u[N + 1], true);
			if (a[x][y] == -1)
				out.println("oops!");
			else {
				dfs(x, y);
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++)
						out.print(u[i][j] ? (a[i][j]) : ("."));
					out.println();
				}
			}
			out.println();
		}
		
		out.close();
	}
	
	void dfs(int x, int y) {
		if (u[x][y])
			return;
		u[x][y] = true;
		if ((a[x][y] = count(x, y)) == 0)
			for (int d = 0; d < dx.length; d++)
				dfs(x + dx[d], y + dy[d]);
	}

	int count(int x, int y) {
		int cnt = 0;
		for (int d = 0; d < dx.length; d++)
			if (a[x + dx[d]][y + dy[d]] == -1)
				cnt++;
		return cnt;
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
