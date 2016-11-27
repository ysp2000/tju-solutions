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

	int N;
	int M;
	char[][] map;
	
	int[] dr = { -1, 0, 1, 0 };
	int[] dc = { 0, 1, 0, -1 };
	char[] dir = "NESW".toCharArray();
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		StringTokenizer tok = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tok.nextToken());
		M = Integer.parseInt(tok.nextToken());
		
		map = new char [N][];
		for (int i = 0; i < N; i++)
			map[i] = in.readLine().toCharArray();

		tok = new StringTokenizer(in.readLine());
		int cr = Integer.parseInt(tok.nextToken()) - 1;
		int cc = Integer.parseInt(tok.nextToken()) - 1;
		int cd = 0;
		
		while (true) {
			char ch = (char) in.read();
			
			if (ch == 'Q')
				break;
			if (ch == 'R')
				cd = (cd + 1) % 4;
			if (ch == 'L')
				cd = (cd + 3) % 4;
			if (ch == 'F') {
				int nr = cr + dr[cd];
				int nc = cc + dc[cd];
				if (nr < N && nc < M && map[nr][nc] == ' ') {
					cr = nr;
					cc = nc;
				}
			}
		}
		
		out.println((cr + 1) + " " + (cc + 1) + " " + dir[cd]);
		out.close();
	}
}
