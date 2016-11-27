import java.io.*;
import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		for (;;) {
			int N = readInt();
			int D = readInt();
			
			if (N == 0)
				break;
			
			long a = readSeq(min(N, 64));
			long b = readSeq(max(0, N - 64));
			
			for (int i = 1; i < D; i++) {
				a &= readSeq(min(N, 64));
				b &= readSeq(max(0, N - 64));
			}
			
			System.out.println((a != 0L || b != 0L) ? "yes" : "no");
		}
	}
	
	static long readSeq(int k) throws IOException {
		long res = 0L;
		for (int i = 0; i < k; i++)
			res = (res << 1) | readBit();
		return res;
	}
	
	static long readBit() throws IOException {
		for (;;) {
			int ch = System.in.read();
			if (ch == '0')
				return 0L;
			if (ch == '1')
				return 1L;
		}
	}
	
	static int readInt() throws IOException {
		int ch, n = 0;
		for (ch = System.in.read(); ch < '0' || ch > '9'; ch = System.in.read());
		for (; '0' <= ch && ch <= '9'; ch = System.in.read())
			n = 10 * n + ch - '0';
		return n;
	}
}
