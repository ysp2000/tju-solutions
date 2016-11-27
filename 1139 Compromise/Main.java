import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		String[] t = new String [100 + 1];
		int[] ans = new int [100];
		int[] a = new int [100 + 1];
		int[] b = new int [100 + 1];
		int[][] dp = new int [100 + 1][100 + 1];
		int[][] p = new int [100 + 1][100 + 1];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer tok;
		while (in.ready()) {
			int l = 0;
			int n = 0;
			int m = 0;
			for (String s = in.readLine(); s.charAt(0) != '#'; s = in.readLine()) {
				tok = new StringTokenizer(s);
				while (tok.hasMoreTokens())
					a[n++] = (t[l++] = tok.nextToken()).hashCode();
			}
			for (String s = in.readLine(); s.charAt(0) != '#'; s = in.readLine()) {
				tok = new StringTokenizer(s);
				while (tok.hasMoreTokens())
					b[m++] = tok.nextToken().hashCode();
			}
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= m; j++)
					if (a[i - 1] == b[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
						p[i][j] = 0;
					} else if (dp[i - 1][j] > dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						p[i][j] = 1;
					} else {
						dp[i][j] = dp[i][j - 1];
						p[i][j] = 2;
					}
			int asz = 0;
			for (int i = n, j = m; i > 0 && j > 0;)
				if (p[i][j] == 0) {
					ans[asz++] = i - 1;
					i--;
					j--;
				} else if (p[i][j] == 1)
					i--;
				else
					j--;
			out.print(t[ans[asz - 1]]);
			for (int i = asz - 2; i >= 0; i--) {
				out.print(' ');
				out.print(t[ans[i]]);
			}
			out.println();
		}
		out.close();
	}
}
