import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
			if (new File("gen.txt").exists())
				System.setIn(new FileInputStream("gen.txt"));
		} catch (SecurityException e) {}
		
//		new Main().gen();
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	
	int MAXB = 70;
	int INF = Integer.MAX_VALUE;
	int N;
	Book[] b = new Book [MAXB];
	int best;
	
	void run() throws IOException {
		for (int i = 0; i < MAXB; i++) b[i] = new Book();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0;) {
			N = nextInt();
			for (int i = 0; i < N; i++) {
				b[i].height = nextInt();
				b[i].width = nextInt();
			}
			sort(b, 0, N);
			best = INF;
			int w1 = b[0].width;
			for (int i = 1; i < N - 1; i++) {
				dfs(i + 1, w1, b[i].width, 0, b[0].height, b[i].height, 0);
				System.out.println(best);
				w1 += b[i].width;
			}
			out.println(best);
		}
		
		out.close();
	}
	
	Random rnd = new Random();
	
	void gen() throws IOException {
		int N = 70;
		PrintWriter tst = new PrintWriter("gen.txt");
		tst.println(1);
		tst.println(N);
		for (int i = 0; i < N; i++)
			tst.println(rint(150, 300) + " " + rint(5, 30));
		tst.close();
	}
	
	int rint(int min, int max) {
		return rnd.nextInt(max - min + 1) + min;
	}

	void dfs(int v, int w1, int w2, int w3, int h1, int h2, int h3) {
		int area = max(w1, max(w2, w3)) * (h1 + h2 + h3);
		if (area > best) return;
		if (v == N) {
			if (h3 != 0 && best > area) best = area;
			return;
		}
		dfs(v + 1, w1 + b[v].width, w2, w3, h1, h2, h3);
		dfs(v + 1, w1, w2 + b[v].width, w3, h1, h2, h3);
		dfs(v + 1, w1, w2, w3 + b[v].width, h1, h2, max(h3, b[v].height));
	}

	class Book implements Comparable<Book> {
		int height;
		int width;

		@Override
		public int compareTo(Book b) {
			if (height == b.height) return width - width;
			return b.height - height;
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
