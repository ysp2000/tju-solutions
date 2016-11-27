import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int[] cnt = new int [26];
		for (char ch : in.readLine().toCharArray())
			cnt[ch - 'a']++;

		int tnum = 0;
		int tshares = 0;
		int N = Integer.parseInt(in.readLine());
		
		int[] off = new int [26];
		int[] rgt = new int [26];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), " ,:");
			int c = tok.nextToken().charAt(0) - 'A';
			while (tok.hasMoreTokens()) {
				tnum++;
				int bean = tok.nextToken().charAt(0) - 'a';
				int count = Integer.parseInt(tok.nextToken());
				if (count == cnt[bean]) {
					tshares += 2;
					rgt[c]++;
				} else if (Math.abs(count - cnt[bean]) == 1) {
					tshares++;
					off[c]++;
				}
			}
		}
		
		double M = tnum * 2.0 / tshares;
		for (int i = 0; i < 26; i++)
			if (off[i] > 0 || rgt[i] > 0)
				out.println((char) ('A' + i) + " " + String.format(Locale.US, "%.2f", M * (off[i] + 2 * rgt[i])));
		out.close();
	}
}
