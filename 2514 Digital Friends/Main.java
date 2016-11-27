import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] ca = new int [10];
		int[] cb = new int [10];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = Integer.parseInt(in.readLine()); T --> 0;) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), " ");
			char[] a = tok.nextToken().toCharArray();
			char[] b = tok.nextToken().toCharArray();
			Arrays.fill(ca, 0);
			Arrays.fill(cb, 0);
			for (char c : a) ca[c - '0']++;
			for (char c : b) cb[c - '0']++;
			out.println(friend(ca, cb) ? "friends" : (almost(a, ca, cb) || almost(b, cb, ca) ? "almost friends" : "nothing"));
		}
		out.close();
	}

	static boolean almost(char[] x, int[] a, int[] b) {
		for (int i = 0, j = 1; j < x.length; i++, j++) {
			boolean ok = false;
			if (x[i] > '1' - Math.min(i, 1) && x[j] < '9') {
				a[x[i] - '0']--;
				a[x[j] - '0']--;
				a[x[i] - '0' - 1]++;
				a[x[j] - '0' + 1]++;
				ok = friend(a, b);
				a[x[i] - '0']++;
				a[x[j] - '0']++;
				a[x[i] - '0' - 1]--;
				a[x[j] - '0' + 1]--;
			}
			if (ok) return ok;
			if (x[i] < '9' && x[j] > '0') {
				a[x[i] - '0']--;
				a[x[j] - '0']--;
				a[x[i] - '0' + 1]++;
				a[x[j] - '0' - 1]++;
				ok = friend(a, b);
				a[x[i] - '0']++;
				a[x[j] - '0']++;
				a[x[i] - '0' + 1]--;
				a[x[j] - '0' - 1]--;
			}
			if (ok) return ok;
		}
		return false;
	}
	
	static boolean friend(int[] a, int[] b) {
		for (int i = 0; i < 10; i++)
			if (a[i] > 0 && b[i] == 0 || a[i] == 0 && b[i] > 0)
				return false;
		return true;
	}
}
