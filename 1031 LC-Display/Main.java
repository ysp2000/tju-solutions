import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int S, N;
	PrintWriter out;
	
	int[] digs = new int [10];
	char[][] digit = {
			// 0    1    2    3    4    5    6
			{ '-', '|', '|', ' ', '|', '|', '-' }, // 0
			{ ' ', ' ', '|', ' ', ' ', '|', ' ' }, // 1
			{ '-', ' ', '|', '-', '|', ' ', '-' }, // 2
			{ '-', ' ', '|', '-', ' ', '|', '-' }, // 3
			{ ' ', '|', '|', '-', ' ', '|', ' ' }, // 4
			{ '-', '|', ' ', '-', ' ', '|', '-' }, // 5
			{ '-', '|', ' ', '-', '|', '|', '-' }, // 6
			{ '-', ' ', '|', ' ', ' ', '|', ' ' }, // 7
			{ '-', '|', '|', '-', '|', '|', '-' }, // 8
			{ '-', '|', '|', '-', ' ', '|', '-' }, // 9
	};
	
	void run() throws IOException {
		out = new PrintWriter(System.out);
		for (S = readInt(); S > 0; S = readInt()) {
			N = readArray(digs);
			for (int d = 0; d < N; d++)
				line1(digit[digs[d]][0], d < N - 1);
			for (int i = 0; i < S; i++)
				for (int d = 0; d < N; d++)
					line2(digit[digs[d]][1], digit[digs[d]][2], d < N - 1);
			for (int d = 0; d < N; d++)
				line1(digit[digs[d]][3], d < N - 1);
			for (int i = 0; i < S; i++)
				for (int d = 0; d < N; d++)
					line2(digit[digs[d]][4], digit[digs[d]][5], d < N - 1);
			for (int d = 0; d < N; d++)
				line1(digit[digs[d]][6], d < N - 1);
			out.println();
		}
		out.close();
	}
	
	void line1(char c, boolean blank) {
		out.print(' ');
		for (int i = 0; i < S; i++)
			out.print(c);
		out.print(' ');
		if (blank)
			out.print(' ');
		else
			out.println();
	}
	
	void line2(char c1, char c2, boolean blank) {
		out.print(c1);
		for (int i = 0; i < S; i++)
			out.print(' ');
		out.print(c2);
		if (blank)
			out.print(' ');
		else
			out.println();
	}
	
	static int readArray(int[] a) throws IOException {
		int c, sz = 0;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (; '0' <= c && c <= '9'; c = System.in.read())
			a[sz++] = c - '0';
		return sz;
	}
	
	static int readInt() throws IOException {
		int n = 0, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (; '0' <= c && c <= '9'; c = System.in.read())
			n = 10 * n + c - '0';
		return n;
	}
}
