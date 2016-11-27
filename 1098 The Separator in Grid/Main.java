import java.io.*;
import java.util.*;
import static java.lang.Math.*;

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
	
	int MAXSZ = 200;
	
	int R;
	int C;
	char[][] map = new char [MAXSZ][];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			R = nextInt();
			C = nextInt();
			if (R == 0 && C == 0)
				break;
			for (int r = 0; r < R; r++)
				map[r] = nextToken().toCharArray();
			int ans = 0;
			for (int r = 0; r < R; r++)
				for (int c = 0; c < C; c++)
					ans += map[r][c] == 'S' ? 1 : 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 'S' && map[r][c + 1] == 'S') {
						for (int nr = r + 1; nr < R; nr++) {
							if (map[nr][c] == 'S') {
								if (map[nr][c + 1] == 'S') {
									ans -= 2;
									break;
								}
							} else {
								break;
							}
						}
					}
				}
			}
			out.println(ans);
		}
		
		out.close();
	}
	
	int rms(char[] s) {
		for (int i = s.length - 1; i >= 0; i--)
			if (s[i] == 'S')
				return i;
		return -1;
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
