import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] used = new int [676];
		int tick = 1;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		lp: for (;;) {
			String st = in.readLine();
			char[] s = st.toCharArray();
			if (s[0] == '*')
				break;
			out.print(st);
			for (int L = s.length, D = 1; D < L; D++) {
				tick++;
				for (int i = 0; i + D < L; i++) {
					int hash = (s[i] - 'A') * 26 + s[i + D] - 'A';
					if (used[hash] == tick) {
						out.println(" is NOT surprising.");
						continue lp;
					}
					used[hash] = tick;
				}
			}
			out.println(" is surprising.");
		}
		out.close();
	}
}
