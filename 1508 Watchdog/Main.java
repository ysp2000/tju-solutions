import java.io.*;
import java.util.*;
import static java.lang.Math.*;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			int S = nextInt();
			int H = nextInt();
			int[] x = new int [H];
			int[] y = new int [H];
			
			for (int i = 0; i < H; i++) {
				x[i] = nextInt();
				y[i] = nextInt();
			}
			
			boolean found = false;
			
			xLoop: for (int cx = 1; cx < S; cx++) {
				int mx = min(sqr(cx), sqr(S - cx));
				
				yLoop: for (int cy = 1; cy < S; cy++) {
					int my = min(sqr(cy), sqr(S - cy));
					int max = 0;
					
					for (int k = 0; k < H; k++) {
						int cd = sqr(x[k] - cx) + sqr(y[k] - cy);
						
						if (cd == 0) {
							continue yLoop;
						}
						
						max = max(max, cd);
					}
					
					if (max <= min(mx, my)) {
						found = true;
						out.println(cx + " " + cy);
						break xLoop;
					}
				}
			}
			
			if (!found) {
				out.println("poodle");
			}
		}
		
		out.close();
	}
	
	int sqr(int x) {
		return x * x;
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
