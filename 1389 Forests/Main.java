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

	int T;
	int P;
	Int128[] opinion;
	Set<Int128> set = new HashSet<Int128>();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		P = nextInt();
		T = nextInt();
		opinion = new Int128 [P];
		for (int i = 0; i < P; i++)
			opinion[i] = new Int128();
		while (in.ready())
			opinion[nextInt() - 1].setBit(nextInt() - 1);
		for (Int128 x : opinion)
			set.add(x);
		System.out.println(set.size());
	}
	
	class Int128 {
		long l;
		long h;
		
		void setBit(int bit) {
			if (bit < 64)
				l |= 1L << bit;
			else
				h |= 1L << (bit - 64);
		}
		
		@Override
		public int hashCode() {
			return (int) (h ^ (l >> 31) ^ l);
		}
		
		@Override
		public boolean equals(Object obj) {
			Int128 x = (Int128) obj;
			return l == x.l && h == x.h;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
