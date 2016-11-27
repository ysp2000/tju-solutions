import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		int[] cnt = new int [10];
		for (int i = nextInt(), M = nextInt(); i <= M; i++)
			for (int t = i; t > 0; t /= 10)
				cnt[t % 10]++;
		System.out.print(cnt[0]);
		for (int i = 1; i < 10; i++)
			System.out.print(" " + cnt[i]);
		System.out.println();
	}

	static int nextInt() throws IOException {
		int ch, res = 0;
		for (ch = System.in.read(); ch < '0' || '9' < ch; ch = System.in.read());
		for (; '0' <= ch && ch <= '9'; ch = System.in.read())
			res = 10 * res + ch - '0';
		return res;
	}
}
