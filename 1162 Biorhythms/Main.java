import java.io.*;

import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (int P, E, I, D, x, t = 1;;) {
			P = nextInt() % 23;
			E = nextInt() % 28;
			I = nextInt() % 33;
			D = nextInt();
			if (P < 0)
				break;
			while (P != E || P != I) {
				x = max(P, max(E, max(I, D + 1)));
				while (P < x)
					P += 23;
				while (E < x)
					E += 28;
				while (I < x)
					I += 33;
			}
			if (P <= D)
				P += 21252;
			out.print("Case ");
			out.print(t++);
			out.print(": the next triple peak occurs in ");
			out.print(P - D);
			out.println(" days.");
		}
		out.close();
	}

	static int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
