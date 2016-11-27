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
		
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), "-+=", true);
			int sgn = 1;
			int a = 0;
			int b = 0;
			int cs = 1;
			while (tok.hasMoreTokens()) {
				String ct = tok.nextToken();
				if (ct.equals("+"))
					cs = 1;
				else if (ct.equals("-"))
					cs = -1;
				else if (ct.equals("=")) {
					cs = 1;
					sgn = -1;
				} else {
					if (ct.charAt(ct.length() - 1) == 'x')
						a += sgn * cs * (ct.length() == 1 ? 1 : Integer.parseInt(ct.substring(0, ct.length() - 1)));
					else
						b += sgn * cs * Integer.parseInt(ct);
				}
			}
			out.println(a == 0 ? (b == 0 ? "IDENTITY" : "IMPOSSIBLE") : ((int) Math.floor(-b / (double) a)));
		}
		
		out.close();
	}
}
