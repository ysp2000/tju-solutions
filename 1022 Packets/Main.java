import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("d.in"));
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
		
		int[] a = new int [7];
		int[] d13 = { 0, 7, 6, 5 };
		int[] d23 = { 0, 5, 3, 1 };
		
		while (true) {
			int sum = 0;
			for (int i = 1; i <= 6; i++)
				sum += a[i] = nextInt();
			if (sum == 0)
				break;
			int ans = a[6] + a[5] + a[4] + (a[3] + 3) / 4;
			int d1 = 11 * a[5] + d13[a[3] % 4];
			int d2 = 5 * a[4] + d23[a[3] % 4];
			
			if (d2 > a[2]) {
				d1 += 4 * (d2 - a[2]);
				d2 = a[2];
			}
			
			d1 = min(a[1], d1);
			
			a[2] -= d2;
			a[1] -= d1;
		
			ans += a[2] / 9;
			a[2] %= 9;
			
			if (a[2] > 0) {
				ans++;
				a[1] = max(0, a[1] - 4 * (9 - a[2]));
			}
			
			ans += (a[1] + 35) / 36;
			out.println(ans);
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
