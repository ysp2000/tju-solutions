import java.io.*;
import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] a = new int [26];
		for (int T = Integer.parseInt(in.readLine()), t = 0; t < T; t++) {
			char[] s = in.readLine().toCharArray();
			int max = 0;
			fill(a, 0);
			for (char ch : s)
				max = Math.max(max, ++a[ch - 'a']);
			for (int i = 0; i < 26; i++)
				if (a[i] == max)
					System.out.print((char)('a' + i));
			System.out.println();
		}
	}
}
