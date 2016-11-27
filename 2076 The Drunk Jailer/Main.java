import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			System.out.println(N < 9 ? 2 : (N < 16 ? 3 : (N < 25 ? 4 : (N < 36 ? 5 : (N < 49 ? 6 : (N < 64 ? 7 : (N < 81 ? 8 : (N < 100 ? 9 : 10))))))));
		}
	}
}
