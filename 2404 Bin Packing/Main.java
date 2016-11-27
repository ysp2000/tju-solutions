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
	
	int N;
	int L;
	int[] l;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		N = nextInt();
		L = nextInt();
		l = new int [N];
		for (int i = 0; i < N; i++)
			l[i] = nextInt();
		Arrays.sort(l);
		int ans = N;
		int i = 0;
		int j = N - 1;
		while (i < j) {
			if (l[i] + l[j] <= L) {
				ans--;
				i++;
			}
			j--;
		}
		System.out.println(ans);
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
