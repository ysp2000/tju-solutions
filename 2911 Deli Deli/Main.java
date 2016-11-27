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

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int dSize = nextInt();
		int qNum = nextInt();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < dSize; i++)
			map.put(nextToken(), nextToken());
		for (int i = 0; i < qNum; i++) {
			String s = nextToken();
			if (map.containsKey(s))
				out.println(map.get(s));
			else if (case1(s))
				out.println(s.substring(0, s.length() - 1) + "ies");
			else if (case2(s))
				out.println(s + "es");
			else
				out.println(s + "s");
		}
		out.close();
	}
	
	boolean case1(String s) {
		return s.length() > 1 && s.charAt(s.length() - 1) == 'y' && isConsonant(s.charAt(s.length() - 2));
	}
	
	boolean case2(String s) {
		return s.endsWith("o") || s.endsWith("s") || s.endsWith("x") || s.endsWith("ch") || s.endsWith("sh");
	}
	
	boolean isConsonant(char c) {
		return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'y';
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
