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

	char[] key = "the quick brown fox jumps over the lazy dog".toCharArray();
	char[] t = new char [26];
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		char[][] lines = new char [100][];
		int lNum = 0;
		while (in.ready())
			lines[lNum++] = in.readLine().toCharArray();
		boolean ok = false;
		for (int i = 0; i < lNum; i++)
			if (lines[i].length == key.length && transposition(lines[i])) {
				if (ok)
					throw new RuntimeException();
				ok = true;
				break;
			}
		if (ok) {
			for (int i = 0; i < lNum; out.println(), i++)
				for (char c : lines[i])
					out.print(c(c));
		} else
			out.println("No solution.");
		out.close();
	}
	
	char c(char c) {
		return c == ' ' ? ' ' : t[c - 'a'];
	}

	boolean transposition(char[] s) {
		Arrays.fill(t, (char) 0);
		for (int i = 0; i < key.length; i++) {
			if (s[i] == ' ')
				if (key[i] == ' ')
					continue;
				else
					return false;
			if (t[s[i] - 'a'] == 0)
				t[s[i] - 'a'] = key[i];
			else if (t[s[i] - 'a'] != key[i])
				return false;
		}
		for (int i = 0; i < 26; i++)
			if (t[i] == 0)
				return false;
		return true;
	}
}
