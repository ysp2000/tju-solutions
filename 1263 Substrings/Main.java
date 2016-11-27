import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] s = new String [100];
		mlp: for (int N, T = Integer.parseInt(in.readLine()); T --> 0;) {
			if ((N = Integer.parseInt(in.readLine())) == 1)
				out.println(in.readLine().length());
			else {
				int min = 0;
				for (int i = 0; i < N; i++)
					if ((s[i] = in.readLine()).length() < s[min].length())
						min = i;
				String sm = s[min];
				for (int len = sm.length(), l = len; l > 0; l--)
					lp: for (int i = 0; i + l <= len; i++) {
						String ss = sm.substring(i, i + l);
						String is = (new StringBuffer(ss)).reverse().toString();
						boolean eq = ss.equals(is);
						for (int j = 0; j < N; j++)
							if (j != min)
								if (!(eq ? s[j].contains(ss) : (s[j].contains(ss) || s[j].contains(is))))
									continue lp;
						out.println(l);
						continue mlp;
					}
				out.println(0);
			}
		}
		out.close();
	}
}
