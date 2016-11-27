import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

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
	
	int MAXS = 46000;
	int S;
	int Y;
	int B;
	int[] bc = new int [10];
	int[] bi = new int [10];
	int[] dp = new int [MAXS];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			S = nextInt();
			Y = nextInt();
			B = nextInt();
			
			for (int i = 0; i < B; i++) {
				bc[i] = nextInt() / 1000;
				bi[i] = nextInt();
			}
			
			for (int y = 0; y < Y; y++) {
				int N = S / 1000;
				
				fill(dp, 0, N + 1, -1);
				dp[N] = 0;
				int max = 0;
				
				for (int i = N; i >= 0; i--) {
					max = max(max, dp[i]);
					
					for (int b = 0; b < B; b++) {
						int j = i - bc[b];
						
						if (j >= 0) {
							dp[j] = max(dp[j], dp[i] + bi[b]);
						}
					}
				}
				
				S += max;
			}
			
			out.println(S);
		}
		
		out.close();
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
