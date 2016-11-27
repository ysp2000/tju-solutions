import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int pos;
	char[] word;
	Trie dict = new Trie();
	int[] sz = new int [26];
	int[][] val = new int [26][10];
	int ans;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int wNum = nextInt(), w = 0; w < wNum; w++) {
			word = nextToken().toCharArray();
			pos = 0;
			dict.add();
		}
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			fill(sz, 0);
			for (int pNum = nextInt(), p = 0; p < pNum; p++) {
				int ind = nextToken().charAt(0) - 'a';
				val[ind][sz[ind]++] = nextInt();
			}
			
			for (int i = 0; i < 26; i++)
				sort(val[i], 0, sz[i]);
			
			ans = 0;
			dfs(dict, 0);
			out.println(ans);
		}
		
		out.close();
	}
	
	void dfs(Trie v, int score) {
		if (v.term)
			ans = max(ans, score);
		for (int i = 0; i < 26; i++) {
			if (v.next[i] != null && sz[i] > 0) {
				sz[i]--;
				dfs(v.next[i], score + val[i][sz[i]]);
				sz[i]++;
			}
		}
	}
	
	class Trie {
		boolean term = false;
		Trie[] next = new Trie [26];
		
		void add() {
			if (pos == word.length) {
				term = true;
			} else {
				int ind = word[pos++] - 'a';
				if (next[ind] == null)
					next[ind] = new Trie();
				next[ind].add();
			}
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
