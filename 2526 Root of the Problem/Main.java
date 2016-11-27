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
		
		int DT = 10;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for (int b = Integer.parseInt(st.nextToken()); b > 0; b = Integer.parseInt(st.nextToken())) {
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			
			if (n == 1) {
				System.out.println(b);
			} else {
				int root = (int) pow(b, 1.0 / n);
				int ans = -1;
				int delta = -1;
				
				for (int dt = -DT; dt <= DT; dt++) {
					if (root + dt >= 0) {
						int x = iPow(root + dt, n);
						
						if (ans == -1 || delta > abs(b - x)) {
							ans = root + dt;
							delta = abs(b - x);
						}
					}
				}
				
				System.out.println(ans);
			}
		}
	}

	private static int iPow(int b, int p) {
		int res = b;
		for (int i = 1; i < p; i++)
			res *= b;
		return res;
	}
}
