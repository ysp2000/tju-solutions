import java.io.*;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int N = nextInt();
		int[] a = new int [N];
		for (int i = 0; i < N; i++)
			a[N - i - 1] = nextInt();
		long[] sum1 = new long [N];
		long[] sum2 = new long [N];
		sum1[0] = a[0];
		sum2[0] = 0;
		int best = 0;
		
		for (int i = 1; i < N; i++) {
			sum1[i] = sum1[best];
			sum2[i] = sum2[best];
			long x = a[i] + sum2[best];
			if (sum1[i] < x || sum1[i] == x && sum2[i] < sum1[best]) {
				sum1[i] = x;
				sum2[i] = sum1[best];
			}
			if (sum1[best] < sum1[i] || sum1[best] == sum1[i] && sum2[best] < sum2[i])
				best = i;
		}
		out.println(sum1[best] + " " + sum2[best]);
		out.close();
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
