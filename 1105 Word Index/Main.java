import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int cnt = 1;
		for (int i = 0; i <= 26; i++) {
			int a = i;
			for (int j = i == 0 ? 0 : i + 1; j <= 26; j++) {
				int b = a * 27 + j;
				for (int k = j == 0 ? 0 : j + 1; k <= 26; k++) {
					int c = b * 27 + k;
					for (int l = k == 0 ? 0 : k + 1; l <= 26; l++) {
						int d = c * 27 + l;
						for (int m = l + 1; m <= 26; m++) {
							map.put(d * 27 + m, cnt++);
						}
					}
				}
			}
		}
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			int ch, code = 0;
			for (ch = System.in.read(); ch != -1 && (ch < 'a' || ch > 'z'); ch = System.in.read());
			if (ch == -1)
				break;
			for (; 'a' <= ch && ch <= 'z'; ch = System.in.read())
				code = 27 * code + ch - 'a' + 1;
			out.println(map.containsKey(code) ? map.get(code) : 0);
		}
		out.close();
	}
}
