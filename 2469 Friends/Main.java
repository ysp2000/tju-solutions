import java.io.*;
import static java.util.Arrays.fill;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		DSF dsf = new DSF();
		for (int T = nextInt(); T --> 0;) {
			dsf.clear(nextInt());
			for (int M = nextInt(); M --> 0;)
				dsf.union(nextInt() - 1, nextInt() - 1);
			out.println(dsf.cNum());
		}
		out.close();
	}

	class DSF {
		int vNum;
		int[] set = new int [100];
		int[] rnk = new int [100];
		
		void clear(int vNum) {
			this.vNum = vNum;
			fill(rnk, 0, vNum, 0);
			for (int i = 0; i < vNum; i++)
				set[i] = i;
		}
		
		int setOf(int x) {
			return x == set[x] ? x : (set[x] = setOf(set[x]));
		}
		
		void union(int i, int j) {
			if ((i = setOf(i)) == (j = setOf(j))) return;
			if (rnk[i] > rnk[j]) {
				set[j] = i;
			} else {
				set[i] = j;
				if (rnk[i] == rnk[j])
					rnk[j]++;
			}
		}
		
		int cNum() {
			int ret = 0;
			for (int i = 0; i < vNum; i++)
				if (rnk[setOf(i)] != -1) {
					rnk[setOf(i)] = -1;
					ret++;
				}
			return ret;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
