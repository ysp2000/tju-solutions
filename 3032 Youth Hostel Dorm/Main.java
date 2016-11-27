import java.io.*;
import java.util.*;

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

	int N = 8;
	int M = 8;
	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };
	
	int bx = 0, by = 0;
	long bmap = 0;
	long best = 0;
	PrintWriter out;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			String s = in.readLine();
			N = s.charAt(0) - '0';
			M = s.charAt(2) - '0';
			
			if (N == 1 && M == 1) {
				out.println('E');
			} else {
				best = 0;
				if (M > 1)
					bfs(0, 1);
				if (N > 1)
					bfs(1, 0);
				print(bx, by, best);
			}
		}
		
		out.close();
	}
	
	void print(int ex, int ey, long map) {
		out.println(Long.bitCount(best));
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++)
				if (x == ex && y == ey)
					out.print('E');
				else if ((map & (1L << ((x << 3) + y))) != 0)
					out.print('B');
				else
					out.print('.');
			out.println();
		}
	}

	int MAXN = 100000;
	long[] qmap = new long [MAXN];
	long[] qbeds = new long [MAXN];
	int[] qstate = new int [MAXN];
	Set<Long> used = new HashSet<Long>();
	
	void bfs(int x, int y) {
		int ex = x;
		int ey = y;
		used.clear();
		int qT = 0;
		int qH = 0;
		qmap[qT] = map(x, y, -1);
		used.add(qmap[qT]);
		qbeds[qT] = beds(x, y, 0, qmap[qT]);
		qstate[qT] = (x << 3) + y;
		qT++;
		
		while (qH != qT) {
			long map = qmap[qH];
			long beds = qbeds[qH];
			
			if (Long.bitCount(beds) > Long.bitCount(best)) {
				bx = ex;
				by = ey;
				best = beds;
				bmap = map;
			}
			int state = qstate[qH];
			if (++qH == MAXN)
				qH = 0;
			x = state >> 3;
			y = state & 7;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					long nmap = map(nx, ny, map);
					if (!used.contains(nmap)) {
						long nbeds = beds(nx, ny, beds, nmap);
						int cnt = Long.bitCount(nbeds);
						if (cnt > Long.bitCount(beds)) {
							qmap[qT] = nmap;
							used.add(nmap);
							qbeds[qT] = nbeds;
							qstate[qT] = (nx << 3) + ny;
							if (++qT == MAXN)
								qT = 0;
						}
					}
				}
			}
		}
	}

	long map(int x, int y, long code) {
		return code & (~(1L << ((x << 3) + y)));
	}
	
	long beds(int x, int y, long code, long map) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (0 <= nx && nx < N && 0 <= ny && ny < M)
				code |= (1L << ((nx << 3) + ny));
		}
		return code & map;
	}

}
