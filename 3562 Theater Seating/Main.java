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
	
	int W;
	int R;
	Seat[] s;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		W = nextInt();
		R = nextInt();
		s = new Seat [W * R];
		int sz = 0;
		for (int row = 0; row < R; row++)
			for (int col = 0; col < W; col++)
				s[sz++] = new Seat(row, col);
		Arrays.sort(s);
		int[][] ans = new int [R][W];
		sz = 0;
		for (Seat x : s)
			ans[x.row][x.col] = ++sz;
		for (int row = R - 1; row >= 0; row--) {
			for (int col = 0; col < W; col++) {
				if (col > 0) out.print(' ');
				out.print(ans[row][col]);
			}
			out.println();
		}
		out.close();
	}
	
	int sqr(int x) {
		return x * x;
	}
	
	class Seat implements Comparable<Seat> {
		int row;
		int col;
		int dist2;
		
		Seat(int row, int col) {
			this.row = row;
			this.col = col;
			this.dist2 = sqr(col - (W >> 1)) + sqr(row);
		}

		@Override
		public int compareTo(Seat s) {
			if (dist2 == s.dist2) {
				if (row == s.row)
					return col - s.col;
				return row - s.row;
			}
			return dist2 - s.dist2;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
