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

	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (int t = 1, T = Integer.parseInt(in.readLine()); T --> 0;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			double I = Double.NaN;
			double U = Double.NaN;
			double P = Double.NaN;
			while (tok.hasMoreTokens()) {
				String ct = tok.nextToken();
				if (ct.length() > 3) {
					if (I != I && ct.charAt(0) == 'I')
						I = getValue(ct, 'I', 'A');
					if (U != U && ct.charAt(0) == 'U')
						U = getValue(ct, 'U', 'V');
					if (P != P && ct.charAt(0) == 'P')
						P = getValue(ct, 'P', 'W');
				}
			}
			long ans = 0;
			char con = 0;
			char unt = 0;
			if (I != I) {
				ans = (long) ((I = P / U) * 100.0 + 0.5);
				con = 'I';
				unt = 'A';
			}
			if (U != U) {
				ans = (long) ((U = P / I) * 100.0 + 0.5);
				con = 'U';
				unt = 'V';
			}
			if (P != P) {
				ans = (long) ((P = U * I) * 100.0 + 0.5);
				con = 'P';
				unt = 'W';
			}
			if (con == 0)
				throw new RuntimeException("Error!");
			out.print("Problem #");
			out.println(t++);
			out.print(con);
			out.print('=');
			out.print(ans / 100);
			out.print('.');
			if (ans % 100 < 10)
				out.print('0');
			out.print(ans % 100);
			out.println(unt);
			out.println();
		}
		
		out.close();
	}

	double getValue(String s, char c, char u) {
		StringTokenizer tok = new StringTokenizer(s, "=mkMWVA", true);
		if (!tok.hasMoreTokens())
			return Double.NaN;
		tok.nextToken();
		if (!tok.hasMoreTokens() || !tok.nextToken().equals("="))
			return Double.NaN;
		double res;
		try {
			res = Double.parseDouble(tok.nextToken());
		} catch (Exception e) {
			return Double.NaN;
		}
		if (!tok.hasMoreTokens())
			return Double.NaN;
		String pu = tok.nextToken();
		if (pu.length() > 1)
			return Double.NaN;
		char p = pu.charAt(0);
		if (p == 'm')
			res *= 1e-3;
		else if (p == 'k')
			res *= 1e+3;
		else if (p == 'M')
			res *= 1e+6;
		else if (p != u)
			return Double.NaN;
		else
			return res;
		if (!tok.hasMoreTokens())
			return Double.NaN;
		pu = tok.nextToken();
		if (pu.length() > 1)
			return Double.NaN;
		p = pu.charAt(0);
		if (p != u)
			return Double.NaN;
		return res;
	}
}
