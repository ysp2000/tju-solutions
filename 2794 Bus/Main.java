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
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int MAXN = 1000000;
	
	int N;
	int[] a = new int [MAXN];
	int[] b = new int [MAXN];
	int[] dpa = new int [MAXN + 2];
	int[] dpb = new int [MAXN + 2];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0; ) {
			N = nextInt();
			for (int i = 0; i < N; i++)
				a[i] = nextInt();
			for (int i = 0; i < N; i++)
				b[i] = nextInt();
			fill(dpa, 0, N + 2, 0);
			fill(dpb, 0, N + 2, 0);
			for (int i = N - 1; i >= 0; i--) {
				dpa[i] = a[i] + max(dpa[i + 1], dpb[i + 2]);
				dpb[i] = b[i] + max(dpb[i + 1], dpa[i + 2]);
			}
			out.println(max(dpa[0], dpb[0]));
		}
		
		out.close();
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
