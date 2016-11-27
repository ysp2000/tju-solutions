import java.io.*;

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
	
	int MAXN = 100;
	
	int N;
	int W;
	int H;
	int S;
	
	int[] xMin = new int [MAXN];
	int[] xMax = new int [MAXN];
	int[] yMin = new int [MAXN];
	int[] yMax = new int [MAXN];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0; ) {
			W = nextInt();
			H = nextInt();
			N = nextInt();
			for (int i = 0; i < N; i++) {
				xMin[i] = nextInt();
				yMin[i] = nextInt();
				xMax[i] = nextInt();
				yMax[i] = nextInt();
			}
			if (!disjoint()) {
				out.println("NONDISJOINT");
			} else if (!contained()) {
				out.println("NONCONTAINED");
			} else if (!covering()) {
				out.println("NONCOVERING");
			} else {
				out.println("OK");
			}
		}
		
		out.close();
	}
	
	boolean disjoint() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < i; j++)
				if (its(i, j))
					return false;
		return true;
	}
	
	boolean its(int i, int j) {
		return min(xMax[i], xMax[j]) > max(xMin[i], xMin[j]) && min(yMax[i], yMax[j]) > max(yMin[i], yMin[j]);
	}

	boolean contained() {
		S = 0;
		for (int i = 0; i < N; i++) {
			if (xMin[i] < 0 || xMax[i] > W || yMin[i] < 0 || yMax[i] > H)
				return false;
			S += (xMax[i] - xMin[i]) * (yMax[i] - yMin[i]);
		}
		return true;
	}

	boolean covering() {
		return S == W * H;
	}

	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
}
