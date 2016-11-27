import java.io.*;

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

	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			char[] s = in.readLine().toCharArray();
			int add = 0;
			int score = 0;
			
			for (char c : s) {
				if (c == 'O') {
					score += ++add;
				} else {
					add = 0;
				}
			}
			
			out.println(score);
		}
		
		out.close();
	}
}
