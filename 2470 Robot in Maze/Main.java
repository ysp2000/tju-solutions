import java.io.*;
import java.util.*;

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

	int N;
	int M;
	char[][] map = new char [102][];
	int[] queue = new int [4 * 100 * 100];
	int[] dist = new int [4 * 100 * 100];
	int[] used = new int [((((100 << 7) | 100) << 2) | 3) + 1];
	int tick = 1;
	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, 1, 0, -1 };
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);

		map[0] = map[101] = "######################################################################################################".toCharArray();
		
		for (int T = nextInt(); T --> 0;) {
			N = nextInt();
			M = nextInt();
			for (int i = 1; i <= N; i++)
				map[i] = ("#" + nextToken() + "#").toCharArray();
			int sx = -1;
			int sy = -1;
			int ex = -1;
			int ey = -1;
			lp: for (int x = 1; x <= N; x++)
				for (int y = 1; y <= M; y++) {
					if (map[x][y] == 'S') {
						sx = x;
						sy = y;
					}
					if (map[x][y] == 'T') {
						ex = x;
						ey = y;
					}
					if (sx != -1 && ex != -1)
						break lp;
				}
			int qH = 0;
			int qT = 0;
			queue[qT++] = ((sx << 7) | sy) << 2;
			int ans = -1;
			tick++;
			while (qH < qT) {
				int cd = dist[qH];
				int cs = queue[qH++];
				int cdir = cs & 3;
				cs >>= 2;
				int cy = cs & 127;
				int cx = cs >> 7;
			
				if (cx == ex && cy == ey) {
					ans = cd;
					break;
				}
				
				int nx = cx + dx[cdir];
				int ny = cy + dy[cdir];
				if (map[nx][ny] != '#') {
					int ns = (((nx << 7) | ny) << 2) | cdir;
					if (used[ns] != tick) {
						used[ns] = tick;
						dist[qT] = cd + 1;
						queue[qT++] = ns;
					}
				}
				
				int ndir = (cdir + 1) & 3;
				int ns = (((cx << 7) | cy) << 2) | ndir;
				if (used[ns] != tick) {
					used[ns] = tick;
					dist[qT] = cd + 1;
					queue[qT++] = ns;
				}
				
				ndir = (cdir + 3) & 3;
				ns = (((cx << 7) | cy) << 2) | ndir;
				if (used[ns] != tick) {
					used[ns] = tick;
					dist[qT] = cd + 1;
					queue[qT++] = ns;
				}
			}
			
			out.println(ans);
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
