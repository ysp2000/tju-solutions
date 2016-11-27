import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	int X, Y;
	boolean[][] map = new boolean [100][100];
	int[][] used = new int [100][100];
	int tick = 1;
	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, 1, 0, -1 };
	int[] queue = new int [10000];
	int[] dist = new int [10000];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			tick++;
			X = nextInt();
			Y = nextInt();
			for (int y = 0; y < Y; y++)
				for (int x = 0; x < X; x++)
					map[x][y] = nextInt() == 0;
			int qH = 0, qT = 0;
			dist[qT] = 1;
			queue[qT++] = (nextInt() << 7) | nextInt();
			int ex = nextInt(), ey = nextInt();
			while (qH < qT) {
				int code = queue[qH];
				int cd = dist[qH++];
				int cx = code >> 7;
				int cy = code & 127;
				if (cx == ex && cy == ey) {
					out.println(cd);
					break;
				}
				for (int k = 0; k < 4; k++) {
					int nx = cx + dx[k];
					int ny = cy + dy[k];
					if (0 <= nx && nx < X && 0 <= ny && ny < Y  && map[nx][ny])
						if (used[nx][ny] != tick) {
							used[nx][ny] = tick;
							dist[qT] = cd + 1;
							queue[qT++] = (nx << 7) | ny; 
						}
				}
			}
			
		}
		out.close();
	}
	
	BufferedReader in;
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
