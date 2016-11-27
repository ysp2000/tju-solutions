import java.io.*;

import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	PrintWriter out;
	BufferedReader in;
	
	void run() throws IOException {
		int[] map = new int [10000000];
		int[] set = new int [100000];
		int sz = 0;
		
		out = new PrintWriter(System.out);
		in = new BufferedReader(new InputStreamReader(System.in));
		for (int N = Integer.parseInt(in.readLine()); N --> 0;) {
			int num = nextPhoneNumber();
			if (map[num] == 1)
				set[sz++] = num;
			map[num]++;
		}
		if (sz == 0)
			out.println("No duplicates.");
		else {
			sort(set, 0, sz);
			for (int i = 0; i < sz; i++) {
				int x = set[i];
				printNum(x / 10000, 3);
				out.print('-');
				printNum(x % 10000, 4);
				out.println(" " + map[x]);
			}
		}
		out.close();
	}
	
	void printNum(int n, int l) {
		String s = Integer.toString(n);
		for (int k = l - s.length(); k --> 0;)
			out.print('0');
		out.print(s);
	}

	int[] code = { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 0, 7, 7, 8, 8, 8, 9, 9, 9, 0 };
	
	int nextPhoneNumber() throws IOException {
		char[] s = in.readLine().toCharArray();
		int n, c, k, i = 0;
		for (n = 0, k = 0, i = 0; i < s.length && k < 7; i++) {
			c = s[i];
			k++;
			if ('0' <= c && c <= '9')
				n = 10 * n + c - '0';
			else if ('A' <= c && c <= 'Z')
				n = 10 * n + code[c - 'A'];
			else
				k--;
		}
		return n;
	}
}
