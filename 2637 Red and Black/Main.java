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
	
	int R, C;
	char[][] map = new char [22][];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
	
		fill(map[0] = new char [22], '#');
		
		for (;;) {
			C = nextInt();
			R = nextInt();
			if (R == 0)
				break;
			for (int i = 1; i <= R; i++)
				map[i] = ("#" + nextToken() + "#").toCharArray();
			map[R + 1] = new char [C + 2];
			fill(map[R + 1], '#');
			lp: for (int i = 1; i <= R; i++)
				for (int j = 1; j <= C; j++)
					if (map[i][j] == '@') {
						out.println(dfs(i, j));
						break lp;
					}
		}
		
		out.close();
	}
	
	int[] dr = { -1, 0, 1, 0 };
	int[] dc = { 0 , 1, 0, -1 };
	
	int dfs(int r, int c) {
		int ret = 1;
		map[r][c] = '#';
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (map[nr][nc] == '.')
				ret += dfs(nr, nc);
		}
		return ret;
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
