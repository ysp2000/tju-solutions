import java.io.*;
import java.util.StringTokenizer;
import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int L;
	int R;
	int C;
	
	char[][][] map = new char [30][30][];
	boolean[] used = new boolean [30 * 30 * 30];
	int[] queue = new int [30 * 30 * 30];
	int[] qdist = new int [30 * 30 * 30];
	
	int[] dl = { -1,  0,  0,  0,  0,  1 };
	int[] dr = {  0,  0, -1,  0,  1,  0 };
	int[] dc = {  0, -1,  0,  1,  0,  0 };
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (;;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			L = Integer.parseInt(tok.nextToken());
			R = Integer.parseInt(tok.nextToken());
			C = Integer.parseInt(tok.nextToken());
			if (L == 0)
				break;
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++)
					map[l][r] = in.readLine().toCharArray();
				in.readLine();
			}
			int qH = 0;
			int qT = 0;
			int end = -1;
			int M1 = R * C;
			int M2 = C;
			for (int l = 0; l < L; l++)
				for (int r = 0; r < R; r++)
					for (int c = 0; c < C; c++) {
						if (map[l][r][c] == 'S')
							queue[qT++] = M1 * l + M2 * r + c;
						if (map[l][r][c] == 'E')
							end = M1 * l + M2 * r + c;
					}
			fill(used, 0, L * R * C, false);
			used[queue[0]] = true;
			int ans = -1;
			while (qH < qT) {
				int cd = qdist[qH];
				int cs = queue[qH++];
				int l = cs / M1;
				int r = cs % M1 / M2;
				int c = cs % M2;
//				System.out.println(l + " " + r + " " + c);
				if (cs == end) {
					ans = cd;
					break;
				}
				for (int d = 0; d < dl.length; d++) {
					int nl = l + dl[d];
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (free(nl, nr, nc)) {
						int ns = nl * M1 + nr * M2 + nc;
						if (!used[ns]) {
							used[ns] = true;
							qdist[qT] = cd + 1;
							queue[qT++] = ns;
						}
					}
				}
			}
			if (ans == -1)
				out.println("Trapped!");
			else {
				out.print("Escaped in ");
				out.print(ans);
				out.println(" minute(s).");
			}
		}
		
		out.close();
	}
	
	boolean free(int l, int r, int c) {
		return 0 <= l && l < L && 0 <= r && r < R && 0 <= c && c < C && map[l][r][c] != '#';
	}
}
