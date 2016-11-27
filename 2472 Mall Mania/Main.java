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

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int[][] map = new int [2001][2001];
	int tick = 1;
	int[] queue = new int [2001 * 2001];
	int[] length = new int [2001 * 2001];
	int qH;
	int qT;
	
	int[] dx = { -1,  0,  1,  0 };
	int[] dy = {  0,  1,  0, -1 };
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			tick += 2;
			qH = 0;
			qT = 0;
			
			for (int i = 0; i < n; i++) {
				int x = nextInt();
				int y = nextInt();
				map[x][y] = tick;
				queue[qT] = x * 2001 + y;
				length[qT] = 0;
				qT++;
			}
			
			int m = nextInt();
			boolean found = false;
			
			for (int i = 0; i < m; i++) {
				int x = nextInt();
				int y = nextInt();
				
				if (map[x][y] == tick) {
					found = true;
				}
				
				map[x][y] = tick + 1;
			}
			
			if (found) {
				out.println(0);
			} else {
				BFS: while (qH < qT) {
					int cx = queue[qH] / 2001;
					int cy = queue[qH] % 2001;
					int cl = length[qH];
					qH++;
					
					for (int d = 0; d < 4; d++) {
						int nx = cx + dx[d];
						int ny = cy + dy[d];
						
						if (valid(nx) && valid(ny)) {
							if (map[nx][ny] == tick + 1) {
								out.println(cl + 1);
								break BFS;
							} else if (map[nx][ny] < tick) {
								map[nx][ny] = tick;
								queue[qT] = 2001 * nx + ny;
								length[qT] = cl + 1;
								qT++;
							}
						}
					}
				}
			}
		}
		
		out.close();
	}
	
	boolean valid(int c) {
		return 0 <= c && c <= 2000;
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
