import java.io.*;

import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int xNum;
	int yNum;
	int eNum;
	int[][] g = new int [100][100];
	int[] used = new int [100];
	int[] xm = new int [100];
	int[] ym = new int [100];
	int gtick = 0;
	int utick = 0;
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			xNum = nextInt();
			if (xNum == 0)
				break;
			yNum = nextInt();
			eNum = nextInt();
			gtick++;
			for (int e = 0; e < eNum; e++) {
				nextInt();
				int u = nextInt();
				int v = nextInt();
				if (u == 0 || v == 0)
					continue;
				g[u][v] = gtick;
			}
			out.println(maxMatching());
		}
		out.close();
	}
	
	int maxMatching() {
		fill(xm, 0, xNum, -1);
		fill(ym, 0, yNum, -1);
		for (int x = 0; x < xNum; x++)
			if (xm[x] == -1) {
				utick++;
				dfs(x);
			}
		int ret = 0;
		for (int x = 0; x < xNum; x++)
			if (xm[x] != -1)
				ret++;
		return ret;
	}

	boolean dfs(int x) {
		if (used[x] == utick)
			return false;
		used[x] = utick;
		for (int y = 0; y < yNum; y++)
			if (g[x][y] == gtick && (ym[y] == -1 || dfs(ym[y]))) {
				xm[x] = y;
				ym[y] = x;
				return true;
			}
		return false;
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
